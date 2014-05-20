class Card < Venue

  has_attached_file :card_image, styles: APP::PHOTO::CARD, url: "/system/venues/:attachment/:id_partition/:style/:basename.:extension"
  has_attached_file :card_on, styles: APP::PHOTO::PUNCH_HOLE, url: "/system/venues/:attachment/:id_partition/:style/:basename.:extension"
  has_attached_file :card_off, styles: APP::PHOTO::PUNCH_HOLE, url: "/system/venues/:attachment/:id_partition/:style/:basename.:extension"

  default_scope { select([:id, :ucode, :name, :address, :latitude, :longitude, :card_description, :card_image_file_name, :card_image_updated_at, :card_on_file_name, :card_on_updated_at, :card_off_file_name, :card_off_updated_at]).where( { has_card: true, state: :live } ) }

  def self.list(user_id=nil, just_mine=false, latitude=nil, longitude=nil)
    just_mine = [true, 1, 'true', '1'].include? just_mine
    if just_mine
      owners = self.includes(:loyalty).where(['user_id = ?', user_id]).references(:loyalty)
    else
      owners = self
    end
    unless latitude.blank? || longitude.blank?
      owners = owners.around_me latitude, longitude
    end
    return owners if just_mine
    owners = owners.all
    records = Loyalty.where(user_id: user_id, venue_id: owners.map(&:id))
    owners.each do |owner|
      record = records.select{ |r| r.venue_id == owner.id }.first
      association = owner.association(:loyalty)
      association.target = record.blank? ? nil : record
    end
  end

  def punch(user_id)
    loyalty = Loyalty.where(user_id: user_id, venue_id: id).first_or_initialize
    unless loyalty.new_record? || loyalty.updated_at < Time.now - APP::VENUE::PUNCHES::INTERVAL.minutes
      errors[:base] << "Recently punched, see you tomorrow"
      return false
    end
    loyalty.increment :points
    loyalty.save
  end

  def reset_card(user_id)
    loyalty = Loyalty.where(user_id: user_id, venue_id: id).first
    unless loyalty.updated_at < Time.now - APP::VENUE::PUNCHES::INTERVAL.minutes
      errors[:base] << "Can't use recently punched card, see you tomorrow"
      return false
    end
    loyalty.points = 0
    loyalty.save
  end

end

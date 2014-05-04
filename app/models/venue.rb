class Venue < ActiveRecord::Base
#ATTRIBUTES
  validates :name, presence: true
  validates :latitude, :longitude, presence: true, if: :live?
  has_attached_file :image, styles: APP::PHOTO::VENUE
  has_attached_file :card_image, styles: APP::PHOTO::CARD
  has_attached_file :card_on, styles: APP::PHOTO::PUNCH_HOLE
  has_attached_file :card_off, styles: APP::PHOTO::PUNCH_HOLE
  belongs_to :category, class_name: 'VenueCategory', foreign_key: :venue_category_id
  has_many :user_venues
  has_one  :loyalty
  has_many :checkins, through: :user_venues, source: :user
  reverse_geocoded_by :latitude, :longitude
  before_create :generate_ucode

  validates_attachment_content_type :image, content_type: APP::PHOTO::TYPES
  validates_attachment_content_type :card_image, content_type: APP::PHOTO::TYPES
  validates_attachment_content_type :card_on, content_type: APP::PHOTO::TYPES
  validates_attachment_content_type :card_off, content_type: APP::PHOTO::TYPES

  include AASM

  aasm :column => 'state' do
    state :new, :initial => true
    state :live
    state :paused

    event :approve do
      transitions :to => :live, guard: :is_located?
    end
    event :pause do
      transitions :to => :paused
    end
  end

  scope :as_cards, -> { select([:id, :ucode, :name, :address, :latitude, :longitude, :card_description, :card_image_file_name, :card_image_updated_at, :card_on_file_name, :card_on_updated_at, :card_off_file_name, :card_off_updated_at]).where( { has_card: true, state: :live } ) }

#METHODS

  def self.cards(user_id=nil, just_mine=false, latitude=nil, longitude=nil)
    just_mine = [true, 1, 'true', '1'].include? just_mine
    if just_mine
      owners = as_cards.includes(:loyalty).where(['user_id = ?', user_id]).references(:loyalty)
    else
      owners = as_cards
    end
    unless latitude.blank? || longitude.blank?
      owners = owners.around_me latitude, longitude
    end
    return owners if just_mine
    records = Loyalty.where(user_id: user_id, venue_id: owners.map(&:id))
    owners.each do |owner|
      record = records.select{ |r| r.venue_id == owner.id }.first
      association = owner.association(:loyalty)
      association.target = record.blank? ? nil : record
    end
  end

  def self.around_me( latitude, longitude )
    where(state: 'live').near([latitude, longitude], APP::VENUE::DISTANCE, :units => :km)
  end

  def checkin(user_id)
    UserVenue.create( { user_id: user_id, venue_id: id } )
  end

  def punch(user_id)
    loyalty = Loyalty.where(user_id: user_id, venue_id: id).first_or_create
    unless loyalty.updated_at < Time.now - 20.minutes
      errors[:base] << "Recently Punched, see you tomorrow"
      return false
    end
    loyalty.increment! :points
  end

private
  def generate_ucode
    begin
      ucode = SecureRandom.hex(4)
    end while Venue.where(ucode: ucode).exists?
    write_attribute :ucode, ucode
  end
 
  def is_located?
    !latitude.blank? && !longitude.blank?
  end

end

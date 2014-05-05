class User < ActiveRecord::Base

#ATTRIBUTES
  has_attached_file :image, styles: APP::PHOTO::AVATAR
  has_many :devices
  #reverse_geocoded_by :latitude, :longitude

  def location
    { lat: latitude, lon: longitude }
  end

  def full_name
    "#{first_name} #{last_name}"
  end
  
  def birthdate=(value)
    write_attribute :birthdate, value
    self.age = value.nil? ? nil: ((Date.today - value.to_date).to_i / 365.25).to_i
    self.birthdate
  end

  def gender=(value)
    write_attribute :gender, User.gender(value)
  end

  def self.gender(value)
    (['true', 'female', '1'].include?(value.to_s) ? 1 : 0 )
  end

  validates_attachment_content_type :image, content_type: APP::PHOTO::TYPES
  
#METHODS
  def self.me( device_or_number )
    if device_or_number.to_s.size < 13
      where( number: device_or_number ).first
    else
      me_by_device
    end
  end
  
  def self.me_by_device( device_id )
    Device.where( device_id: device_id ).first.try :user
  end

  def new_session!
    self.authentication_token = loop do
      token = SecureRandom.urlsafe_base64 60
      break token unless User.exists?(authentication_token: token)
    end
    save
  end

end

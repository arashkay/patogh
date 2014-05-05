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

  scope :live, -> { where( state: :live ) }

#METHODS
  def self.around_me( latitude, longitude )
    where(state: 'live').near([latitude, longitude], APP::VENUE::DISTANCE, :units => :km)
  end

  def checkin(user_id)
    UserVenue.create( { user_id: user_id, venue_id: id } )
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

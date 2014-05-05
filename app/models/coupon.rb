class Coupon < ActiveRecord::Base

  has_attached_file :image, styles: APP::PHOTO::COUPON
  belongs_to :venue
  has_many :user_coupons

  validates :description, presence: true
  validates_attachment_content_type :image, content_type: APP::PHOTO::TYPES

  include AASM

  aasm :column => 'state' do
    state :new, :initial => true
    state :live
    state :paused
    state :ended

    event :start do
      transitions to: :live, guard: :can_live?
    end
    event :pause do
      transitions to: :paused
    end
    event :end do
      transitions to: :ended
    end
  end

private
  
  def can_live?
    !start_date.blank? && !expires_at.blank? && start_date <= Date.today && expires_at >= Date.today 
  end

end

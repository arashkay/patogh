class AddAttachmentCardImageToVenues < ActiveRecord::Migration
  def self.up
    change_table :venues do |t|
      t.attachment :card_image
    end
  end

  def self.down
    drop_attached_file :venues, :card_image
  end
end

class AddAttachmentCardOffToVenues < ActiveRecord::Migration
  def self.up
    change_table :venues do |t|
      t.attachment :card_off
    end
  end

  def self.down
    drop_attached_file :venues, :card_off
  end
end

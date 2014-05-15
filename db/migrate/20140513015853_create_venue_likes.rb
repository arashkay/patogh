class CreateVenueLikes < ActiveRecord::Migration
  def change
    create_table :venue_likes do |t|
      t.integer :user_id
      t.integer :venue_id

      t.timestamps
    end
    
    add_foreign_key :venue_likes, :users
    add_foreign_key :venue_likes, :venues
  end
end

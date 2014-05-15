class CreateUserVenues < ActiveRecord::Migration
  def change
    create_table :user_venues do |t|
      t.integer :user_id
      t.integer :venue_id
      t.string :action
      t.string :uname

      t.timestamps
    end
    
    add_foreign_key :user_venues, :users
    add_foreign_key :user_venues, :venues
  end
end

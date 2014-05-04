class CreateLoyalties < ActiveRecord::Migration
  def change
    create_table :loyalties do |t|
      t.integer :user_id, null: false
      t.integer :venue_id, null: false
      t.integer :points

      t.timestamps
    end
    
    add_foreign_key :loyalties, :users
    add_foreign_key :loyalties, :venues
  end
end

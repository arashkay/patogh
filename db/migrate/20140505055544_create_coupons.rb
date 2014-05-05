class CreateCoupons < ActiveRecord::Migration
  def change
    create_table :coupons do |t|
      t.integer :admin_id
      t.integer :venue_id
      t.string :title
      t.text :description
      t.date :start_date
      t.date :expires_at
      t.string :state
      t.integer :impressions, default: 0
      t.integer :collections, default: 0

      t.timestamps
    end
    
    add_foreign_key :coupons, :admins
    add_foreign_key :coupons, :venues
  end
end

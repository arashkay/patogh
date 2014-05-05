class CreateUserCoupons < ActiveRecord::Migration
  def change
    create_table :user_coupons do |t|
      t.integer :user_id, null: false
      t.integer :coupon_id, null: false

      t.timestamps
    end
    
    add_foreign_key :user_coupons, :users
    add_foreign_key :user_coupons, :coupons
  end
end

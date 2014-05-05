class CreateLoyaltyHistories < ActiveRecord::Migration
  def change
    create_table :loyalty_histories do |t|
      t.integer :user_id, null: false
      t.integer :venue_id, null: false

      t.timestamps
    end

    add_foreign_key :loyalty_histories, :users
    add_foreign_key :loyalty_histories, :venues

  end
end

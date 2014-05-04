class CreateVenues < ActiveRecord::Migration
  def change
    create_table :venues do |t|
      t.string :name
      t.string :address
      t.string :phone
      t.boolean :has_card, default: false
      t.string :card_description
      t.float :latitude
      t.float :longitude
      t.string :state

      t.timestamps
    end
  end
end

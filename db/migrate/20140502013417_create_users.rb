class CreateUsers < ActiveRecord::Migration
  def change
    create_table :users do |t|
      t.string :number
      t.string :first_name
      t.string :last_name
      t.boolean :gender
      t.date :birthdate
      t.integer :age
      t.string :authentication_token
      t.string :city
      t.string :country
      t.float :latitude
      t.float :longitude

      t.timestamps
    end

    add_index :users, :number, unique: true
  end
end

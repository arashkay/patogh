class AddUcodeToVenues < ActiveRecord::Migration
  def change
    add_column :venues, :ucode, :string
    add_index :venues, :ucode, unique: true
  end
end

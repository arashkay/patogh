class AddLikesToVenues < ActiveRecord::Migration
  def change
    add_column :venues, :likes_count, :integer, default: 0
    add_column :venues, :checkins_count, :integer, default: 0
  end
end

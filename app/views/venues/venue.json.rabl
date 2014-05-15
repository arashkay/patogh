object @venue
attributes :id, :name, :address, :latitude, :longitude, :likes_count, :checkins_count
node(:small400){ |o| o.image.url(:small400) }

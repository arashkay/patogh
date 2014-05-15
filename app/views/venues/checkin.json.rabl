object @checkin
attributes :id, :venue_id, :action
glue(:venue) do 
  attributes :name, :likes_count, :checkins_count
  node(:small400){ |v| v.image.url(:small400) }
  node(:has_liked){ |v| v.has_liked }
end

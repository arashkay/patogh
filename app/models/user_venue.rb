class UserVenue < ActiveRecord::Base

  belongs_to :venue, counter_cache: :checkins_count

  def self.list(user_id, last_seen_id=0, limit=APP::LISTING::LIMIT)
    checkins = self.where(["user_venues.id > ?", last_seen_id]).includes(:venue).order('user_venues.id DESC').references(:venue)
    likes = VenueLike.where( venue_id: checkins.map(&:id), user_id: user_id ).select(:venue_id).map(&:venue_id)
    checkins.each{ |c| c.venue.has_liked = likes.include?(c.venue_id) }
  end

end

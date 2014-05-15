class VenueLike < ActiveRecord::Base

  belongs_to :venue, counter_cache: :likes_count

end

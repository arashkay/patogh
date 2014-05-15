class VenuesController < ApiController
  
  before_filter :detect_venue, except: [:index, :checkins]
  
  def index
    latitude  = params.fetch(:scope, {})[:latitude].blank?  ? nil : params[:scope][:latitude]
    longitude = params.fetch(:scope, {})[:longitude].blank? ? nil : params[:scope][:longitude]
    if latitude.blank? || longitude.blank?
      @venues = Venue.all.limit(APP::LISTING::BULK)
    else
      @venues = Venue.limit(APP::LISTING::LIMIT).around_me latitude, longitude
    end
    render :venues
  end
 
  def checkins
    @checkins = UserVenue.list(@current_user.id)
    render :checkins
  end

  def checkin
    @checkin = @venue.checkin @current_user.id, params[:checkin][:action]
    if @checkin
      render :checkin
    else
      render Error.failed @venue.errors
    end
  end

  def like
    if @venue.like @current_user.id
      @venue.reload
      render :venue
    else
      render Error.failed @venue.errors
    end
  end

private
  
  def detect_venue
    @venue = Venue.where(id: params[:id]).first
    return render Error.not_found if @venue.blank?
  end
  
end

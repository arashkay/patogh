class Panel::VenuesController < Panel::MainController

  before_filter :is_hobbit_or_redirect!, except: [:new, :create, :edit, :update]

  def index
    @venues = Venue.order('id DESC').page params[:page]
  end

  def new
    @venue = Venue.new
  end

  def create
    @venue = Venue.new venue_params
    if @venue.save
      redirect_to panel_venues_path
    else
      render :new
    end
  end

  def edit
    @venue = Venue.find params[:id]
    render :new
  end

  def update
    @venue = Venue.find params[:id] 
    if @venue.update_attributes venue_params
      redirect_to panel_venues_path
    else
      render :new
    end
  end

  def destroy
    Venue.destroy params[:id]
    render json: true
  end

  def approve
    @venue = Venue.find params[:id]
    if @venue.may_approve?
      @venue.approve 
      @venue.save
      render json: true
    else
      render Error.failed 'Cannot approve! In order to get approved make sure name and GEO location are set.'
    end
  end

  def pause
    @venue = Venue.find params[:id]
    @venue.pause!
    render json: true
  end

private

  def venue_params
    params.require(:venue).permit :name, :phone, :address, :latitude, :longitude, :image, :card_description, :has_card, :card_image, :card_on, :card_off
  end

end

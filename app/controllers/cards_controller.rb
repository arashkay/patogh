class CardsController < ApiController
  
  before_filter :detect_card, except: :index

  def index
    latitude  = params.fetch(:scope, {})[:latitude].blank?  ? nil : params[:scope][:latitude]
    longitude = params.fetch(:scope, {})[:longitude].blank? ? nil : params[:scope][:longitude]
    just_mine = params.fetch(:scope, {})[:just_mine].blank? ? nil :  params[:scope][:just_mine]
    @cards = Venue.cards @current_user.id, just_mine, latitude, longitude
    render :cards
  end

  def punch
    if @card.punch @current_user.id
      render :card
    else
      render Error.failed @card.errors
    end
  end

private
  
  def detect_card
    @card = Venue.as_cards.where(ucode: params[:card][:ucode]).first
    return render Error.not_found if @card.blank?
  end
  
end

class Panel::CouponsController < Panel::MainController

  before_filter :is_hobbit_or_redirect!, except: [:new, :create, :edit, :update]

  def index
    @coupons = Coupon.order('id DESC').page params[:page]
  end

  def new
    @coupon = Coupon.new
  end

  def create
    @coupon = Coupon.new coupon_params
    if @coupon.save
      redirect_to panel_coupons_path
    else
      render :new
    end
  end

  def edit
    @coupon = Coupon.find params[:id]
    render :new
  end

  def update
    @coupon = Coupon.find params[:id] 
    if @coupon.update_attributes coupon_params
      redirect_to panel_coupons_path
    else
      render :new
    end
  end

  def destroy
    Coupon.destroy params[:id]
    render json: true
  end

  def start
    @coupon = Coupon.find params[:id]
    if @coupon.may_start?
      @coupon.start
      @coupon.save
      render json: true
    else
      render Error.failed 'Cannot start! In order to start, make sure dates are set.'
    end
  end

  def pause
    @coupon = Coupon.find params[:id]
    @coupon.pause!
    render json: true
  end

private

  def coupon_params
    params.require(:coupon).permit :title, :description, :start_date, :expires_at, :image
  end

end

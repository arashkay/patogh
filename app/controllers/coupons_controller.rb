class CouponsController < ApiController
  
  def index
    @coupons = Coupon.live.all
    render :coupons
  end

  def me
    @coupons = Coupon.live.joins(:user_coupons).where( ['user_coupons.user_id = ?', @current_user.id] ).all
    render :coupons
  end

  def mark
    if [true, 'true'].include? params[:mark]
      @user_coupon = UserCoupon.where(user_id: @current_user.id, coupon_id: params[:coupon][:id]).first_or_create
    else
      @user_coupon = UserCoupon.where(user_id: @current_user.id, coupon_id: params[:coupon][:id]).first
      @user_coupon.destroy
    end
    render '/layouts/true'
  end

end

class CouponsController < ApiController
  
  def index
    @coupons = Coupon.live.hit_all
    render :coupons
  end

  def me
    @coupons = Coupon.live.joins(:user_coupons).where( ['user_coupons.user_id = ?', @current_user.id] ).hit_all
    render :coupons
  end

  def mark
    if [true, 'true'].include? params[:mark]
      @user_coupon = UserCoupon.where(user_id: @current_user.id, coupon_id: params[:coupon][:id]).first_or_initialize
      if @user_coupon.new_record?
        Coupon.increment_counter :collections, params[:coupon][:id]
        @user_coupon.save
      end
    else
      @user_coupon = UserCoupon.where(user_id: @current_user.id, coupon_id: params[:coupon][:id]).first
      unless @user_coupon.nil?
        @user_coupon.destroy
        Coupon.decrement_counter :collections, params[:coupon][:id]
      end
    end
    render '/layouts/true'
  end

end

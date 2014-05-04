class UsersController < ApiController
  
  skip_before_filter :authorize, only: [:authenticate, :create]

  def authenticate
    return render Error.forbidden unless @current_user.blank?
    no_user = params.fetch(:user, {})[:number].blank?
    no_device = params.fetch(:device, {})[:device_id].blank?
    return render Error.missed_param(:device) if no_user && no_device
    @user = User.me params[:user][:number] unless no_user
    @user = User.me_by_device params[:device][:device_id] unless no_device
    return render Error.not_found if @user.blank?
    @user.new_session!
  end
  
  def me
  end

  def create
    return render Error.missed_param(:device) if params.fetch(:device, {})[:device_id].blank?
    @user = User.me_by_device params[:device][:device_id]
    return render Error.duplicate unless @user.blank?
    @current_user = User.new user_params
    if @current_user.save
      render save_device_or_finish
    else
      render Error.failed @current_user.errors
    end
  end

  def update
    return render Error.not_found if @current_user.blank?
    if @current_user.update_attributes user_params
      render save_device_or_finish
    else
      render Error.failed @current_user.errors
    end
  end

  def location
    if @current_user.update_attributes location_params
      render '/layouts/true'
    else
      render Error.failed @current_user.errors
    end
  end

private

  def location_params
    params.require(:location).permit :latitude, :longitude
  end

  def user_params
    params.require(:user).permit :first_name, :last_name, :gender, :birthdate, :city, :country, :image, :latitude, :longitude, :number
  end

  def device_params
    params.require(:device).permit :device_id, :device_type, :notification_id, :can_notify
  end

  def save_device_or_finish
    return :me if params[:device].blank?
    device = Device.find_or_create_for @current_user.id, device_params
    if device.errors.blank?
      :me
    else
      Error.failed device.errors
    end
  end

end


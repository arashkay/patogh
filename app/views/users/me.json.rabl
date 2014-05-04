object @current_user
attributes :first_name, :last_name, :city, :country, :gender, :birthdate, :age
if @current_user.image.exists?
  extends '/photos/_photo'
end
unless params[:extend].nil?
  if params[:extend].include? 'device'
    child :devices do
      attributes :id, :device_type, :device_id, :can_notify
    end
  end
end


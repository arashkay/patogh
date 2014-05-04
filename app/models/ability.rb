class Ability
  
  include CanCan::Ability

  def initialize(admin)
    admin ||= Admin.new
    if admin.is_wizard? || admin.is_hobbit?
      can :manage, :all
    elsif admin.is_business?
      can :manage, Venue
      cannot :approve, Venue
      cannot :pause, Venue
    end
  end

end

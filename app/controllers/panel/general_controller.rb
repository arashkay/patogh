class Panel::GeneralController < Panel::MainController

  skip_load_and_authorize_resource
  before_filter :is_wizard_or_redirect!, except: [:index]

  def index
  end

  def api
    @routes = []
    Rails.application.routes.routes.each do |route|
      path = route.path.spec.to_s.gsub('(.:format)', '.json')
      method = route.constraints[:request_method].to_s.gsub(/[\D]*\^/, '').gsub(/\$[\D]*/,'')
      @routes << "#{method} #{path}" if path.starts_with?('/api')
    end
  end

  def charts
  end

end


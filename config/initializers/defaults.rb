module APP
  
  yaml = File.join(Rails.root, 'config', 'defaults.yml')
  if File.exist?(yaml) then
    hash = YAML.load_file(yaml)
    configs = (hash.has_key?('general') && !hash['general'].blank?) ? hash['general'] : {}
    configs = configs.merge(hash[Rails.env]) unless hash[Rails.env].blank?
  else
    puts "Please create #{yaml} file in your /config folder"
    raise "Missing file"
  end
  
  CONFIGS = configs

  ACTIONS = APP::CONFIGS['actions']

  module VENUE
    DISTANCE = APP::CONFIGS['venue']['distance']
    
    module PUNCHES
      INTERVAL = APP::CONFIGS['venue']['punches']['interval']
    end
  end

  module LISTING
    LIMIT = APP::CONFIGS['listing']['limit']
    BULK = APP::CONFIGS['listing']['bulk']
  end

#  module RESQUE
#    PROFILE_PHOTO = "#{APP::CONFIGS['prefix']}_#{APP::CONFIGS['resque']['queues']['profile_photo']}"
#    NOTIFICATIONS = "#{APP::CONFIGS['prefix']}_#{APP::CONFIGS['resque']['queues']['notifications']}"
#  end

  module PHOTO
    CARD         = { small400: '400x225#', thumb: '200x112#' }
    VENUE        = { small400: '400x200#', thumb: '200x112#' }
    COUPON       = { small400: '400x300#', thumb: '200x150#' }
    PUNCH_HOLE   = { small200: '200x200#', small120: '120x120#' }
    AVATAR       = { small200: '200x200#', small120: '120x120#' }
    TYPES        = ["image/jpg", "image/jpeg", "image/png"]
  end

  module DEVICE
    IOS = 'ios'
    ANDROID = 'android'
  end
  
  module ROLES
    WIZARD   = 'wizard'
    HOBBIT   = 'hobbit'
    BUSINESS = 'business'
    CREATURE = 'creature'
  end

end
 
# IMAGE
Paperclip::Attachment.default_options[:styles] = { thumb: '150x150#' }
Paperclip::Attachment.default_options[:default_url] = "/assets/:class.png"
Paperclip::Attachment.default_options[:content_type] = APP::PHOTO::TYPES
# JSON
Rabl.configure do |config|
  config.include_json_root = false
  config.include_child_root = false
end

# GEO
# Geocoder.configure lookup: :google, api_key: "AIzaSyCBSzBDmJekpUpguBOTcUTL_6s6f3zKNiA"

# NOTIFICATION
#APNS.host = 'gateway.push.apple.com'
#APNS.pem  = APP::CONFIGS['notification']['pem_path']
#GCM.key = APP::CONFIGS['notification']['gcm_key']


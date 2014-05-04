source 'https://rubygems.org'

# Bundle edge Rails instead: gem 'rails', github: 'rails/rails'
gem 'rails', '4.1.0'

gem 'mysql2'
gem 'sass-rails', '~> 4.0.3'
gem 'uglifier', '>= 1.3.0'
gem 'coffee-rails', '~> 4.0.0'
gem 'therubyracer',  platforms: :ruby
gem 'turbolinks'
gem 'spring',        group: :development
gem 'yajl-ruby', require: 'yajl'
gem 'rabl'
gem 'slim'
gem 'foreigner'
gem 'pushmeup'
#gem "paperclip", "~> 4.1"
gem 'paperclip', github: 'thoughtbot/paperclip'
gem 'aasm'
gem "resque", "~> 1.25.2"
gem "resqovery"
gem 'geocoder'
#gem "elasticsearch", git: "git://github.com/elasticsearch/elasticsearch-ruby.git"
#gem "elasticsearch-model", git: "git://github.com/elasticsearch/elasticsearch-rails.git"
#gem "elasticsearch-rails", git: "git://github.com/elasticsearch/elasticsearch-rails.git"
gem 'kaminari'
#gem 'god'
gem 'devise'
gem 'cancancan', '~> 1.7'
gem "non-stupid-digest-assets"

gem 'debugger', group: [:development, :test]

group :development do
  gem 'capistrano', '~> 3.1'
  gem 'capistrano-rails', '~> 1.1'
  gem 'capistrano-bundler', '~> 1.1.2'
  gem 'capistrano-rvm'
  gem 'guard-yard'
end

group :development, :test do
  gem 'activerecord-import'
  gem 'rspec-rails'
  gem 'capybara'
  gem 'simplecov'
  gem 'factory_girl_rails'
  gem 'faker'
end

group :doc do
  # bundle exec rake doc:rails generates the API under doc/api.
  gem 'sdoc', require: false
end


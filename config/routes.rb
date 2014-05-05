Rails.application.routes.draw do
  
  devise_for :admins
  root 'panel/general#index'
  
  namespace :panel do
    get 'api' => 'general#api'
    get 'charts' => 'general#charts'
    resources :venues do
      member do
        post :approve
        post :pause
      end
    end
    resources :coupons do
      member do
        post :start
        post :pause
      end
    end
    resources :cards
    resources :users, only: [:index]
  end

  scope :api, defaults: { format: 'json' } do
    scope :v1, :version => 'v1' do
      post '/authenticate' => 'users#authenticate'
      resources :me, controller: :users, only: [] do
        collection do
          post '', action: :create
          get  '', action: :me
          put  '', action: :update
        end
      end
      scope :me do
        resources :cards, only:[] do
          collection do
            post :use
            post :punch
          end
        end
        resources :coupons, only: [] do
          collection do
            get '/', action: :me
            post :mark
          end
        end
      end
      resources :cards, only:[:index]
      resources :coupons, only:[:index]
      resources :notifications, only:[] do
        collection do
          post :mock
        end
      end
    end
  end

end

# Read about factories at https://github.com/thoughtbot/factory_girl

FactoryGirl.define do
  factory :device do
    user_id 1
    device_id "MyString"
    device_type "MyString"
    notification_id "MyText"
    can_notify false
  end
end

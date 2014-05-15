# Read about factories at https://github.com/thoughtbot/factory_girl

FactoryGirl.define do
  factory :user_venue do
    user_id 1
    venue_id 1
    action "MyString"
    uname "MyString"
  end
end

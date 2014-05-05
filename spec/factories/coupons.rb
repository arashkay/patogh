# Read about factories at https://github.com/thoughtbot/factory_girl

FactoryGirl.define do
  factory :coupon do
    venue_id 1
    title "MyString"
    description "MyText"
    start_date "2014-05-05"
    expires_at "2014-05-05"
    state "MyString"
    impressions 1
  end
end

# Read about factories at https://github.com/thoughtbot/factory_girl

FactoryGirl.define do
  factory :venue do
    name "MyString"
    address "MyString"
    phone "MyString"
    has_card false
    card_description "MyString"
  end
end

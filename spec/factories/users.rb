# Read about factories at https://github.com/thoughtbot/factory_girl

FactoryGirl.define do
  factory :user do
    number "MyString"
    first_name "MyString"
    last_name "MyString"
    gender false
    authentication_token "MyString"
  end
end

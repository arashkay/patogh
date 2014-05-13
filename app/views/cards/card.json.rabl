object @card
attributes :ucode, :name, :address, :card_description, :latitude, :longitude
node(:card_small400){ |o| o.card_image.url(:small400) }
node(:card_on_small200){ |o| o.card_on.url(:small200) }
node(:card_off_small200){ |o| o.card_off.url(:small200) }
node(:points){ |o| o.loyalty.blank? ? nil : o.loyalty.points }

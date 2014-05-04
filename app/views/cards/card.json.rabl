object @card
attributes :ucode, :name, :address, :card_description, :latitude, :longitude
node(:card_small400){ |o| o.card_image.url(:small400) }
node(:card_on_small400){ |o| o.card_on.url(:small400) }
node(:card_off_small400){ |o| o.card_off.url(:small400) }
node(:points){ |o| o.loyalty.blank? ? nil : o.loyalty.points }

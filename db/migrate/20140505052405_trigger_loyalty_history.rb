class TriggerLoyaltyHistory < ActiveRecord::Migration
  def self.up
    execute <<-SQL
      CREATE TRIGGER loyalty_insert_track AFTER INSERT ON loyalties
      FOR EACH ROW BEGIN
        INSERT INTO loyalty_histories (user_id, venue_id, created_at, updated_at) values (NEW.user_id, NEW.venue_id, NOW(), NOW());
      END;
    SQL
 
    execute <<-SQL
      CREATE TRIGGER loyalty_update_track AFTER UPDATE ON loyalties
      FOR EACH ROW BEGIN
        IF (NEW.points > OLD.points) THEN
          INSERT INTO loyalty_histories (user_id, venue_id, created_at, updated_at) values (NEW.user_id, NEW.venue_id, NOW(), NOW());
        END IF;
      END;
    SQL
  end

  def self.down
    execute <<-SQL
      DROP TRIGGER loyalty_insert_track;
    SQL
    execute <<-SQL
      DROP TRIGGER loyalty_update_track;
    SQL
  end
end

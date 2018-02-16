DELETE FROM users_user_profiles;
DELETE FROM users;
DELETE FROM user_profiles;
DELETE FROM persistent_logins;

/* Populate users Table */
INSERT INTO users (user_id, user_name, password, first_name, last_name, enabled) VALUES
(1, 'Sunil', 'Bora', 'Sunil', 'Bora', 1),
(2, 'David', 'Miller', 'David', 'Miller', 1),
(3, 'Sameer', 'Singh', 'Sameer', 'Singh', 0),
(4, 'Paul', 'Smith', 'Paul', 'Smith', 0);

/* Populate user_profiles Table */
INSERT INTO user_profiles (type) VALUES 
('USER'),
('ADMIN'),
('DBA');

/* Populate JOIN Table */
INSERT INTO users_user_profiles (user_id, profile_id)
  SELECT user.user_id, profile.profile_id FROM users user, user_profiles profile
  where user.user_name='Sunil' and profile.type='ADMIN';

/* Populate persistent_logins Table */
INSERT INTO persistent_logins (username, series, token, last_used) VALUES 
('test', 'test', 'test', NOW());
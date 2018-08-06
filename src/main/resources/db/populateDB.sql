DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (datetime, description, calories, userid) VALUES
  ('1999-01-08 09:05:06', 'Syrnichki', 420, 100000),
  ('1999-01-08 17:05:06', 'Katletki', 800, 100000);
 /* ('1999-01-08 18:05:06', 'Rysik', 450, 100000),
  ('1999-01-09 04:05:06', 'Kapustka', 300, 100000),
  ('1999-01-09 12:05:06', 'Morozhenka', 500, 100000),
  ('1999-01-09 19:05:06', 'Kefirchik', 150, 100000),
  ('1999-01-10 10:05:06', 'Sochok', 250, 100000);*/
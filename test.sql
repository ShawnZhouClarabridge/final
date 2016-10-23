CREATE TABLE authorities
(
  username character varying(20),
  authority character varying(120)
);

CREATE TABLE users
(
  username character varying(20) NOT NULL,
  password character varying(24) NOT NULL,
  email character varying(128) NOT NULL,
  enabled boolean NOT NULL DEFAULT true
);

INSERT INTO users(
            username, password, email, enabled)
    VALUES ('admin', 'password', 'admin@edureka.com', true);

CREATE TABLE calendar
(
  course character varying(100),
  start_date date,
  end_date date,
  sessions int,
  trainer character varying(20)
);

CREATE TABLE courses
(
  id character varying(10),
  name character varying(100),
  price integer,
  sessions integer
);

CREATE TABLE resumes
(
  trainer character varying(20),
  resume character varying(100)
);

CREATE TABLE trainers
(
  name character varying(20),
  experience integer,
  email character varying(100),
  phone bigint,
  address character varying(100)
);


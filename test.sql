CREATE TABLE traineres
  (
     NAME       CHARACTER VARYING(20),
     experience INT,
     email      CHARACTER VARYING(100),
     phone      BIGINT,
     address    CHARACTER VARYING(100)
  );

CREATE TABLE users
  (
     id       INT NOT NULL,
     username CHARACTER VARYING(20) NOT NULL,
     password CHARACTER VARYING(24) NOT NULL,
     enabled  BOOLEAN NOT NULL DEFAULT true,
     CONSTRAINT users_pkey PRIMARY KEY (username)
  );

CREATE TABLE users
(
  username character varying(20) NOT NULL,
  password character varying(24) NOT NULL,
  email character varying(128) NOT NULL,
  enabled boolean NOT NULL DEFAULT true
)

CREATE TABLE calendar
(
  course character varying(100),
  start_date date,
  end_date date,
  sessions int,
  trainer character varying(20)
)
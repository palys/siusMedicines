CREATE TABLE users
(
  username character varying(45) NOT NULL,
  password character varying(45),
  user_role character varying(45) NOT NULL,
  enabled boolean,
  CONSTRAINT users_pkey PRIMARY KEY (username)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE users
  OWNER TO postgres;
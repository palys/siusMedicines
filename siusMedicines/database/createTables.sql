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
  
CREATE TABLE medicines
(
  name character varying(45) NOT NULL,
  description character varying(500),
  meal_info character varying(45) NOT NULL,
  CONSTRAINT medicines_pkey PRIMARY KEY (name)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE users
  OWNER TO postgres;
  
CREATE TABLE patients
(
  id SERIAL,
  name character varying(45) NOT NULL,
  surname character varying(45) NOT NULL,
  username character varying(45) NOT NULL references users (username),
  pesel character varying(11) NOT NULL,
  phone_number character varying(13) NOT NULL,
  birthdate date NOT NULL,
  CONSTRAINT patients_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE patients
  OWNER TO postgres;
  
CREATE TABLE doctors
(
  id SERIAL,
  name character varying(45) NOT NULL,
  surname character varying(45) NOT NULL,
  username character varying(45) NOT NULL references users (username),
  phone_number character varying(13) NOT NULL,
  email character varying(45) NOT NULL,
  spetialization character varying(45) NOT NULL,
  CONSTRAINT doctors_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE doctors
  OWNER TO postgres;
  
CREATE TABLE prescriptions
(
  id SERIAL,
  patient_id integer references patients (id),
  doctor_id integer references doctors (id),
  medicine_name character varying(45) references medicines (name),
  CONSTRAINT prescriptions_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE prescriptions
  OWNER TO postgres;
  
CREATE TABLE portions
(
  id SERIAL,
  unit character varying(45),
  size real,
  take_time timestamp,
  taken boolean,
  declined boolean,
  decline_reason character varying(100),
  prescription_id integer references prescriptions (id),
  CONSTRAINT portions_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE portions
  OWNER TO postgres;

DROP TABLE IF EXISTS address, customer;

CREATE SEQUENCE customer_customer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE customer (
    customer_id integer DEFAULT nextval('customer_customer_id_seq'::regclass) NOT NULL,
    store_id smallint,
    first_name character varying(45) NOT NULL,
    last_name character varying(45) NOT NULL,
    email character varying(50),
    address_id smallint NOT NULL,
    activebool boolean DEFAULT true NOT NULL,
    create_date date DEFAULT ('now'::text)::date NOT NULL,
    last_update timestamp without time zone DEFAULT now(),
    active integer
);

CREATE SEQUENCE address_address_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE address (
    address_id integer DEFAULT nextval('address_address_id_seq'::regclass) NOT NULL,
    address character varying(50) NOT NULL,
    address2 character varying(50),
    district character varying(20) ,
    city_id smallint NOT NULL,
    postal_code character varying(10),
    phone character varying(20),
    last_update timestamp without time zone DEFAULT now() NOT NULL
);

\copy address(address_id,address,address2, district, city_id, postal_code, phone, last_update) FROM 'address.csv' DELIMITER ',' CSV HEADER;
\copy customer(customer_id,store_id,first_name, last_name, email, address_id, activebool, create_date, last_update, active) FROM 'customers.csv' DELIMITER ',' CSV HEADER;
CREATE TABLE person (
    person_id      integer IDENTITY,
    first_name     varchar(50) NOT NULL,
    last_name      varchar(50) NOT NULL,
    email_address  varchar(50) NOT NULL,
    street_address varchar(50) NOT NULL,
    city           varchar(50) NOT NULL,
    state          varchar(2) NOT NULL,
    zip_code       varchar(5) NOT NULL,
    client_Id      integer
);

CREATE TABLE client
(
    client_id      integer IDENTITY,
    company_name   varchar(50) NOT NULL,
    website_url    varchar(50) NOT NULL,
    phone_number   varchar(15) NOT NULL,
    street_address varchar(50) NOT NULL,
    city           varchar(50) NOT NULL,
    state          varchar(2)  NOT NULL,
    zip_code       varchar(5)  NOT NULL
);
INSERT INTO person (
    first_name,
    last_name,
    email_address,
    street_address,
    city,
    state,
    zip_code,
    client_id
) VALUES (
    'John',
    'Smith',
    'fake1@aquent.com',
    '123 Any St.',
    'Asheville',
    'NC',
    '28801',
    '2'
), (
    'Jane',
    'Smith',
    'fake2@aquent.com',
    '123 Any St.',
    'Asheville',
    'NC',
    '28801',
    '1'
);

INSERT INTO client (
    company_name,
    website_url,
    phone_number,
    street_address,
    city,
    state,
    zip_code
) VALUES (
     'Chipotle',
     'https://www.chipotle.com/',
     '9786881883',
     '93 Turnpike St',
     'North Andover',
     'MA',
     '01845'
    ), (
     'Chick-fil-A',
     'https://www.chick-fil-a.com/',
     '9788721421',
     '73 Pleasant Valley St',
     'Methuen',
     'MA',
     '01844'
    );

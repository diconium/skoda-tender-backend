CREATE TABLE Cars
(
    vin   VARCHAR(17) PRIMARY KEY,
    make  VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    year  INT         NOT NULL
);

CREATE TABLE Users
(
    id         SERIAL PRIMARY KEY,
    username   VARCHAR(50)  NOT NULL,
    email      VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    name       VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Users_Cars
(
    user_id INT,
    car_vin VARCHAR(17),
    PRIMARY KEY (user_id, car_vin),
    FOREIGN KEY (user_id) REFERENCES Users (id),
    FOREIGN KEY (car_vin) REFERENCES Cars (vin)
);

CREATE TABLE Services
(
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(100)   NOT NULL,
    description     TEXT,
    image           VARCHAR(255),
    contract_length INT,
    price           DECIMAL(10, 2) NOT NULL
);

CREATE TABLE Products
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description TEXT,
    image       VARCHAR(255)
);

CREATE TABLE Cars_Services
(
    service_id INT,
    car_vin    VARCHAR(17),
    status     VARCHAR(50),
    start_date TIMESTAMP,
    end_date   TIMESTAMP,
    PRIMARY KEY (service_id, car_vin),
    FOREIGN KEY (service_id) REFERENCES Services (id),
    FOREIGN KEY (car_vin) REFERENCES Cars (vin)
);

CREATE TABLE Services_Products
(
    service_id INT,
    product_id INT,
    PRIMARY KEY (service_id, product_id),
    FOREIGN KEY (service_id) REFERENCES Services (id),
    FOREIGN KEY (product_id) REFERENCES Products (id)
);

-- Load data into Users table
INSERT INTO Users (id, username, email, password, name, created_at) VALUES (1, 'diconium', 'skoda@diconium.com', 'e5d860d36bdae99ca3c1a82747f8a6c96731ca501f79113398476a0af02e59c6eb905d8c6826bbce532c7efd7e70b49ffb8121b36f87e84a4e71997dc8e36dc0', 'diconium', '2023-10-05 00:00:00');

-- Load data into Cars table
INSERT INTO Cars (vin, make, model, year) VALUES ('TMBJR0NX4RY183174', 'Škoda', 'Octavia', 2024);

-- Load data into Users_Cars table
INSERT INTO Users_Cars (user_id, car_vin) VALUES (1, 'TMBJR0NX4RY183174');

-- Load data into Services table
INSERT INTO Services (id, name, description, image, contract_length, price) VALUES (1, 'Care Connect - Remote Access', 'Care Connect - Remote Access is the right choice for even more convenience. Your personal assistants support you in every situation and provide you with all the key information you need regarding your mobility.', 'https://shop.skoda-connect.com/img/large/article-image-CC.png', 12, 49.99);
INSERT INTO Services (id, name, description, image, contract_length, price) VALUES (2, 'Infotainment Online', 'Keep up-to-date on the move with the Infotainment Online services from Škoda Connect. Search for interesting destinations in your area, get up-to-the-minute traffic information from the internet, and much more besides.', 'https://shop.skoda-connect.com/img/small/article-image-IO.png', 12, 49.99);
INSERT INTO Services (id, name, description, image, contract_length, price) VALUES (3, 'Online Data', 'Online data for Travel Assist, Media Streaming, Online data for Intelligent Speed Assist, Traffication.', 'https://shop.skoda-connect.com/CubicImage_3.jpg', 12, 49.99);

-- Load data into Products table
INSERT INTO Products (id, name, description, image) VALUES (1, 'Driving Data', 'Driving data provides you history of driving in MyŠkoda App.', 'https://shop.skoda-connect.com/img/large/product-image-driving-data.png');
INSERT INTO Products (id, name, description, image) VALUES (2, 'Vehicle Status', 'All needed data are accessible from MyŠkoda App.', 'https://shop.skoda-connect.com/img/large/product-image-rvs.png');
INSERT INTO Products (id, name, description, image) VALUES (3, 'Pay to Fuel', 'With Pay to Fuel, you can pay for fueling at supported petrol stations across Europe.', 'https://shop.skoda-connect.com/img/small/product-image-P2F-small.png');
INSERT INTO Products (id, name, description, image) VALUES (4, 'Pay to Park', 'With Pay to Park, you can pay for parking at supported parking locations across Europe.', 'https://shop.skoda-connect.com/img/small/product-image-P2P-small.png');
INSERT INTO Products (id, name, description, image) VALUES (5, 'Online Anti-Theft Alarm', 'Be notified always your vehicle finds itself in trouble.', 'https://shop.skoda-connect.com/img/small/product-image-dwa.png');
INSERT INTO Products (id, name, description, image) VALUES (6, 'Parking Position', 'Last Parking Position lets you know where you parked your car.', 'https://shop.skoda-connect.com/img/large/product-image-lpp.png');
INSERT INTO Products (id, name, description, image) VALUES (7, 'Lock & Unlock including Car Access function', 'Remotely you can lock or unlock your vehicle using the MyŠkoda App.', 'https://shop.skoda-connect.com/img/large/product-image-rlu.png');
INSERT INTO Products (id, name, description, image) VALUES (8, 'Honk & Flash', 'Remotely you can make your vehicle honk and flash using the MyŠkoda App.', 'https://shop.skoda-connect.com/img/large/product-image-rhf.png');
INSERT INTO Products (id, name, description, image) VALUES (9, 'Online Auxiliary Heater', 'The Remote Auxiliary Heater enables you to set heating or ventilation in certain time frames or manually.', 'https://shop.skoda-connect.com/img/large/product-image-rah.png');
INSERT INTO Products (id, name, description, image) VALUES (10, 'Digital Certificate', 'Whether you want to sell your vehicle and eliminate the distrust of the potential buyer or you just want to access detailed information about your vehicle, the Digital Certificate is the ideal solution for you. With the Digital Certificate, you can display an overview of selected vehicle information through the MyŠkoda App (e.g. vehicle identification, technical specification, equipment, mileage and service history). This overview can be then generated into a form of PDF certificate and shared with potential used car buyers.', 'https://shop.skoda-connect.com/img/large/product-digicert.png');
INSERT INTO Products (id, name, description, image) VALUES (11, 'Online Voice-Control', 'Online Voice-Control allows you to use extra features which will make the voice control even more comfortable.', 'https://shop.skoda-connect.com/img/large/product-image-online-POI-search-speech.png');
INSERT INTO Products (id, name, description, image) VALUES (12, 'Petrol Stations', 'Let''s get a quick overview of nearby petrol stations, their opening hours and up-to-date fuel prices.', 'https://shop.skoda-connect.com/img/large/product-image-petrol-stations.png');
INSERT INTO Products (id, name, description, image) VALUES (13, 'Online Map Update - Download', 'With the Online Map Update you''ll have up-to-date data for your navigation system.', 'https://shop.skoda-connect.com/img/large/product-image-maps.png');
INSERT INTO Products (id, name, description, image) VALUES (14, 'Online Route Calculation', 'Thanks to this service, the best possible route to your destination is calculated in real time and allows the navigation system to briefly react to any inconveniences which occur on the roads.', 'https://shop.skoda-connect.com/img/large/product-image-tourimp.png');
INSERT INTO Products (id, name, description, image) VALUES (15, 'Parking Spaces', 'Let''s check parking spaces around your vehicle''s current location in an easy, stress-free way. The feature displays the overall capacity along with the current usage, opening hours and prices as well.', 'https://shop.skoda-connect.com/img/large/product-image-parking-spaces.png');
INSERT INTO Products (id, name, description, image) VALUES (16, 'Online Traffic Information', 'With the Online Traffic Information you get always up-to-date traffic information so you can save your time and avoid stressful staing in traffic jams.', 'https://shop.skoda-connect.com/img/large/product-image-online-traffic-information.png');

-- Load data into Cars_Services table
INSERT INTO Cars_Services (service_id, car_vin, status, start_date, end_date) VALUES (1, 'TMBJR0NX4RY183174', 'Activated', '2023-10-03 12:00:00', '2027-05-30 23:59:59');
INSERT INTO Cars_Services (service_id, car_vin, status, start_date, end_date) VALUES (2, 'TMBJR0NX4RY183174', 'Activated', '2023-10-03 12:00:00', '2027-05-30 23:59:59');
INSERT INTO Cars_Services (service_id, car_vin, status, start_date, end_date) VALUES (3, 'TMBJR0NX4RY183174', 'Inactive', '2023-10-03 12:00:00', '2023-10-03 13:00:00');

-- Load data into Services_Products table
INSERT INTO Services_Products (service_id, product_id) VALUES (1, 1);
INSERT INTO Services_Products (service_id, product_id) VALUES (1, 2);
INSERT INTO Services_Products (service_id, product_id) VALUES (1, 3);
INSERT INTO Services_Products (service_id, product_id) VALUES (1, 4);
INSERT INTO Services_Products (service_id, product_id) VALUES (1, 5);
INSERT INTO Services_Products (service_id, product_id) VALUES (1, 6);
INSERT INTO Services_Products (service_id, product_id) VALUES (1, 7);
INSERT INTO Services_Products (service_id, product_id) VALUES (1, 8);
INSERT INTO Services_Products (service_id, product_id) VALUES (1, 9);
INSERT INTO Services_Products (service_id, product_id) VALUES (1, 10);
INSERT INTO Services_Products (service_id, product_id) VALUES (2, 11);
INSERT INTO Services_Products (service_id, product_id) VALUES (2, 12);
INSERT INTO Services_Products (service_id, product_id) VALUES (2, 13);
INSERT INTO Services_Products (service_id, product_id) VALUES (2, 14);
INSERT INTO Services_Products (service_id, product_id) VALUES (2, 15);
INSERT INTO Services_Products (service_id, product_id) VALUES (2, 16);

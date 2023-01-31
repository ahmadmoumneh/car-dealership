DROP DATABASE IF EXISTS cardealership;
CREATE DATABASE cardealership;
USE cardealership;

DROP TABLE IF EXISTS user;
CREATE TABLE user (
    userId INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(30) NOT NULL,
    lastName VARCHAR(30) NOT NULL,
    email VARCHAR(30) UNIQUE NOT NULL,
    password VARCHAR(16) NOT NULL,
    role VARCHAR(10) NOT NULL
);

DROP TABLE IF EXISTS make;
CREATE TABLE make (
    makeId INT AUTO_INCREMENT PRIMARY KEY,
    makeName VARCHAR(15) UNIQUE NOT NULL,
	userId INT NOT NULL,
    makeDate DATE NOT NULL,
	CONSTRAINT fk_make_user
		FOREIGN KEY (userId)
		REFERENCES user(userId)
);

DROP TABLE IF EXISTS model;
CREATE TABLE model (
    modelId INT AUTO_INCREMENT PRIMARY KEY,
    modelName VARCHAR(70) UNIQUE NOT NULL,
    makeId INT NOT NULL,
	userId INT NOT NULL,
    modelDate DATE NOT NULL,
	CONSTRAINT fk_model_make
		FOREIGN KEY (makeId)
		REFERENCES make(makeId),
	CONSTRAINT fk_model_user
		FOREIGN KEY (userId)
		REFERENCES user(userId)
);

DROP TABLE IF EXISTS vehicle;
CREATE TABLE vehicle (
    vehicleId INT AUTO_INCREMENT  PRIMARY KEY,
    vehicleType VARCHAR(4) NOT NULL,
    modelId INT NOT NULL,
    year INT(4) NOT NULL,
    bodyStyle CHAR(5) NOT NULL,
    transmission VARCHAR(9) NOT NULL,
    color VARCHAR(10) NOT NULL,
    interior VARCHAR(10) NOT NULL,
    mileage INT NOT NULL,
    vin CHAR(17) UNIQUE NOT NULL,
    salePrice CHAR(10),
    msrp CHAR(10) NOT NULL,
    description TINYTEXT NOT NULL,
    isSold BOOL NOT NULL DEFAULT 0,
    isFeatured BOOL NOT NULL DEFAULT 0,
	CONSTRAINT fk_vehicle_model
		FOREIGN KEY (modelId)
        REFERENCES model(modelId)
);

DROP TABLE IF EXISTS picture;
CREATE TABLE picture (
    vehicleId INT PRIMARY KEY,
	content LONGBLOB NOT NULL,
    CONSTRAINT fk_vehicle_picture
		FOREIGN KEY (vehicleId)
        REFERENCES vehicle(vehicleId)
);

DROP TABLE IF EXISTS purchase;
CREATE TABLE purchase (
    purchaseId INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30),
    phone CHAR(12),
    email VARCHAR(30) NOT NULL,
    street1 VARCHAR(30) NOT NULL,
    street2 VARCHAR(30),
    city VARCHAR(30) NOT NULL,
    state CHAR(2) NOT NULL,
    zipcode CHAR(5) NOT NULL,
    price CHAR(8) NOT NULL,
    purchaseType VARCHAR(20) NOT NULL,
    date Date NOT NULL,
    userId INT NOT NULL,
	CONSTRAINT fk_sales_user
		FOREIGN KEY (userId)
        REFERENCES user(userId)
);

DROP TABLE IF EXISTS special;
CREATE TABLE special (
    specialId INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    description MEDIUMTEXT NOT NULL
);

DROP TABLE IF EXISTS contact;
CREATE TABLE contact (
    contactId INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    phone CHAR(12),
    email VARCHAR(30),
    message MEDIUMTEXT NOT NULL
);
CREATE SCHEMA USERNAME;

CREATE TABLE USERNAME.CARS (
  NUMBER_PLATE VARCHAR(6) NOT NULL PRIMARY KEY, 
  MAKE VARCHAR(20), 
  MODEL VARCHAR(20), 
  YEAR_OF_MANUFACTURING INTEGER NOT NULL, 
  DAILY_RENTAL_FEE INTEGER NOT NULL, 
  LAST_SERVICE DATE NOT NULL,
  IN_SERVICE BOOLEAN NOT NULL, 
  PHOTO BOOLEAN NOT NULL
);

INSERT INTO USERNAME.CARS 
  (NUMBER_PLATE, MAKE, MODEL, YEAR_OF_MANUFACTURING, DAILY_RENTAL_FEE, LAST_SERVICE, IN_SERVICE, PHOTO) 
	VALUES ('MJJ391', 'Renault', 'Clio', 2005, 3000, DATE('2018-12-21'), false, true);

CREATE TABLE USERNAME.CUSTOMERS (
  ID_CUSTOMER INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), 
  NAME VARCHAR(40) NOT NULL, 
  ADDRESS VARCHAR(60), 
  PHONE VARCHAR(18)
);

CREATE TABLE USERNAME.RENTS (
  ID_RENT INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
  ID_CUSTOMER INTEGER NOT NULL REFERENCES USERNAME.CUSTOMERS(ID_CUSTOMER),
  NUMBER_PLATE VARCHAR(6) NOT NULL REFERENCES USERNAME.CARS(NUMBER_PLATE), 
  BEGINNING_DATE DATE NOT NULL, 
  EXPECTED_RETURN_DATE DATE NOT NULL, 
  RETURN_DATE DATE DEFAULT NULL,
  PAID_FEE INTEGER DEFAULT 0
);

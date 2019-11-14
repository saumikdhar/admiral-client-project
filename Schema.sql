SHOW DATABASES;

CREATE SCHEMA IF NOT EXISTS ManagingTimeSheet;

USE ManagingTimeSheet;

CREATE TABLE IF NOT EXISTS Login(
login_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
email VARCHAR(150) NOT NULL,
password VARCHAR(12) NOT NULL,
access_level int(1) NOT NULL)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Agency(
agency_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
agency_name VARCHAR(100) NOT NULL)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Manager(
manager_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
manager_firstName VARCHAR(100) NOT NULL,
manager_lastName VARCHAR(100) NOT NULL,
login_id int,
 FOREIGN KEY (login_id) REFERENCES Login(login_id) 
 ON UPDATE CASCADE
 ON DELETE CASCADE    
 )
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Contractor(
contractor_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
contractor_firstName VARCHAR(100) NOT NULL,
contractor_lastName VARCHAR(100) NOT NULL,
login_id int,
manager_id int,
 FOREIGN KEY (login_id) REFERENCES Login(login_id) 
 ON UPDATE CASCADE
 ON DELETE CASCADE,
 FOREIGN KEY (manager_id) REFERENCES Manager(manager_id) 
 ON UPDATE CASCADE
 ON DELETE CASCADE
 )
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS Admin(
admin_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
admin_firstName VARCHAR(100) NOT NULL,
admin_lastName VARCHAR(100) NOT NULL,
login_id int,
 FOREIGN KEY (login_id) REFERENCES Login(login_id)
 ON UPDATE CASCADE
 ON DELETE CASCADE
 )
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS TimeSheet(
timesheet_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
days_worked VARCHAR(100) NOT NULL,
overtime int NOT NULL,
start_date DATE NOT NULL,
agency_id int,
contractor_id int
 )
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Agency_Contractor(
agency_contractor_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
agency_id int,
contractor_id int,
timesheet_id int,
 FOREIGN KEY (agency_id) REFERENCES Agency(agency_id)
 ON UPDATE CASCADE
 ON DELETE CASCADE,
 FOREIGN KEY (contractor_id) REFERENCES Contractor(contractor_id)
 ON UPDATE CASCADE
 ON DELETE CASCADE,
 FOREIGN KEY (timesheet_id) REFERENCES TimeSheet(timesheet_id)
 ON UPDATE CASCADE
 ON DELETE CASCADE
 )
ENGINE = InnoDB;
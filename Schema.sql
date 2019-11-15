 SHOW DATABASES;

DROP SCHEMA IF EXISTS ManagingTimeSheets;
CREATE SCHEMA IF NOT EXISTS ManagingTimeSheets;

USE ManagingTimeSheets;

CREATE TABLE IF NOT EXISTS Logins(
login_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
email VARCHAR(150) NOT NULL,
password VARCHAR(12) NOT NULL,
access_level int(1) NOT NULL DEFAULT 0)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Agencies(
agency_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
agency_name VARCHAR(100) NOT NULL)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Managers(
manager_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
manager_firstName VARCHAR(100) NOT NULL,
manager_lastName VARCHAR(100) NOT NULL,
login_id int,
 FOREIGN KEY (login_id) REFERENCES Logins(login_id)
 ON UPDATE CASCADE
 ON DELETE CASCADE    
 )
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Contractors(
contractor_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
contractor_firstName VARCHAR(100) NOT NULL,
contractor_lastName VARCHAR(100) NOT NULL,
login_id int,
manager_id int,
 FOREIGN KEY (login_id) REFERENCES Logins(login_id)
 ON UPDATE CASCADE
 ON DELETE CASCADE,
 FOREIGN KEY (manager_id) REFERENCES Managers(manager_id)
 ON UPDATE CASCADE
 ON DELETE CASCADE
 )
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS Admins(
admin_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
admin_firstName VARCHAR(100) NOT NULL,
admin_lastName VARCHAR(100) NOT NULL,
login_id int,
 FOREIGN KEY (login_id) REFERENCES Logins(login_id)
 ON UPDATE CASCADE
 ON DELETE CASCADE
 )
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS TimeSheets(
timesheet_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
days_worked BOOLEAN NOT NULL DEFAULT FALSE,
overtime int NOT NULL DEFAULT 0,
start_date DATE NOT NULL,
status VARCHAR(10)
 )
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Agency_Contractors(
agency_contractor_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
agency_id int,
contractor_id int,
timesheet_id int,
 FOREIGN KEY (agency_id) REFERENCES Agencies(agency_id)
 ON UPDATE CASCADE
 ON DELETE CASCADE,
 FOREIGN KEY (contractor_id) REFERENCES Contractors(contractor_id)
 ON UPDATE CASCADE
 ON DELETE CASCADE,
 FOREIGN KEY (timesheet_id) REFERENCES TimeSheets(timesheet_id)
 ON UPDATE CASCADE
 ON DELETE CASCADE
 )
ENGINE = InnoDB;
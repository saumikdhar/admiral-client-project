DROP SCHEMA IF EXISTS ManagingTimeSheets;
SHOW DATABASES;

CREATE SCHEMA IF NOT EXISTS ManagingTimeSheets;

USE ManagingTimeSheets;

CREATE TABLE IF NOT EXISTS logins
(
    login_id     INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email        VARCHAR(150) NOT NULL,
    password     VARCHAR(250) NOT NULL,
    access_level INT(1)       NOT NULL DEFAULT 0
)
    ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS confirmation_tokens
(
    token_id     INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    confirmation VARCHAR(150),
    login_id           INT,
    FOREIGN KEY (login_id) REFERENCES logins (login_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS agencies
(
    agency_id   INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    agency_name VARCHAR(100) NOT NULL
)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS managers
(
    manager_id         INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    manager_first_name VARCHAR(100) NOT NULL,
    manager_last_name  VARCHAR(100) NOT NULL,
    login_id           INT,
    FOREIGN KEY (login_id) REFERENCES logins (login_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS contractors
(
    contractor_id         INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    contractor_first_name VARCHAR(100) NOT NULL,
    contractor_last_name  VARCHAR(100) NOT NULL,
    login_id              INT          NOT NULL,
    manager_id            INT          NOT NULL,
    agency_id             INT          NOT NULL,
    FOREIGN KEY (agency_id) REFERENCES agencies (agency_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (login_id) REFERENCES logins (login_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (manager_id) REFERENCES managers (manager_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
    ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS admins
(
    admin_id        INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    admin_firstName VARCHAR(100) NOT NULL,
    admin_lastName  VARCHAR(100) NOT NULL,
    login_id        INT,
    FOREIGN KEY (login_id) REFERENCES logins (login_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS timesheets
(
    timesheet_id     INT     NOT NULL AUTO_INCREMENT PRIMARY KEY,
    contractor_id    INT     NOT NULL,
    monday_worked    BOOLEAN NOT NULL DEFAULT FALSE,
    tuesday_worked   BOOLEAN NOT NULL DEFAULT FALSE,
    wednesday_worked BOOLEAN NOT NULL DEFAULT FALSE,
    thursday_worked  BOOLEAN NOT NULL DEFAULT FALSE,
    friday_worked    BOOLEAN NOT NULL DEFAULT FALSE,
    saturday_worked  BOOLEAN NOT NULL DEFAULT FALSE,
    sunday_worked    BOOLEAN NOT NULL DEFAULT FALSE,
    overtime         INT              DEFAULT 0,
    start_date       DATE    NOT NULL,
    status           VARCHAR(10),
    FOREIGN KEY (contractor_id) REFERENCES contractors (contractor_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
    ENGINE = InnoDB;

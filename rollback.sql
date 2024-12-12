-- Create Database Change Log Table
CREATE TABLE PUBLIC.DATABASECHANGELOG (ID VARCHAR(255) NOT NULL, AUTHOR VARCHAR(255) NOT NULL, FILENAME VARCHAR(255) NOT NULL, DATEEXECUTED TIMESTAMP NOT NULL, ORDEREXECUTED INT NOT NULL, EXECTYPE VARCHAR(10) NOT NULL, MD5SUM VARCHAR(35), DESCRIPTION VARCHAR(255), COMMENTS VARCHAR(255), TAG VARCHAR(255), LIQUIBASE VARCHAR(20), CONTEXTS VARCHAR(255), LABELS VARCHAR(255), DEPLOYMENT_ID VARCHAR(10));

-- Create Database Lock Table
CREATE TABLE PUBLIC.DATABASECHANGELOGLOCK (ID INT NOT NULL, LOCKED BOOLEAN NOT NULL, LOCKGRANTED TIMESTAMP, LOCKEDBY VARCHAR(255), CONSTRAINT PK_DATABASECHANGELOGLOCK PRIMARY KEY (ID));

-- Initialize Database Lock Table
DELETE FROM PUBLIC.DATABASECHANGELOGLOCK;

INSERT INTO PUBLIC.DATABASECHANGELOGLOCK (ID, LOCKED) VALUES (1, FALSE);

-- *********************************************************************
-- SQL to roll back currently unexecuted changes
-- *********************************************************************
-- Change Log: liquibase/changelog-main.yaml
-- Ran at: 12.12.24 21:22
-- Against: SA@jdbc:h2:mem:db
-- Liquibase version: 4.29.2
-- *********************************************************************

-- Lock Database
UPDATE PUBLIC.DATABASECHANGELOGLOCK SET LOCKED = TRUE, LOCKEDBY = 'Matej--MacBook-Air-2.local (192.168.0.102)', LOCKGRANTED = NOW() WHERE ID = 1 AND LOCKED = FALSE;

-- Rolling Back ChangeSet: liquibase/changesets/001-init.yaml::1::matejvana
DROP TABLE PUBLIC.user_details_role;

DROP TABLE PUBLIC.user_details;

DELETE FROM PUBLIC.DATABASECHANGELOG WHERE ID = '1' AND AUTHOR = 'matejvana' AND FILENAME = 'liquibase/changesets/001-init.yaml';

-- Release Database Lock
UPDATE PUBLIC.DATABASECHANGELOGLOCK SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;


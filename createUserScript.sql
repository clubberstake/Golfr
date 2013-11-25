-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE PROCEDURE `new_procedure` ()
BEGIN
CREATE USER 'client'@'localhost' IDENTIFIED BY '12345';
GRANT SELECT, UPDATE, INSERT  ON golfr.* TO 'client'@'localhost';
FLUSH PRIVILEGES;
END
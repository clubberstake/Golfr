CREATE DATABASE  IF NOT EXISTS `golfr` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `golfr`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: golfr
-- ------------------------------------------------------
-- Server version	5.6.14-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_golfcoursedetails`
--

DROP TABLE IF EXISTS `t_golfcoursedetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_golfcoursedetails` (
  `courseID_pk` int(11) NOT NULL AUTO_INCREMENT,
  `courseName` varchar(45) DEFAULT NULL,
  `streetName` varchar(45) DEFAULT NULL,
  `streetNumber` varchar(45) DEFAULT NULL,
  `postalCode` int(11) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `webAddress` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`courseID_pk`),
  UNIQUE KEY `courseID_pk` (`courseID_pk`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_golfcoursedetails`
--

LOCK TABLES `t_golfcoursedetails` WRITE;
/*!40000 ALTER TABLE `t_golfcoursedetails` DISABLE KEYS */;
INSERT INTO `t_golfcoursedetails` VALUES (1,'Course1',NULL,NULL,NULL,'555-555-1234','www.course1.com'),(9,'Course2','West Street','155',26606,'555-965-5555','www.course2.com'),(10,'Course2','West Street','155',26606,'555-965-5555','www.course2.com'),(11,'Course2','West Street','155',26606,'555-965-5555','www.course2.com'),(12,'Course2','West Street','155',26606,'555-965-5555','www.course2.com'),(13,'Course2','West Street','155',26606,'555-965-5555','www.course2.com'),(14,'Course2','West Street','155',26606,'555-965-5555','www.course2.com'),(15,'Course2','West Street','155',26606,'555-965-5555','www.course2.com'),(16,'Course2','West Street','155',26606,'555-965-5555','www.course2.com'),(17,'Course2','West Street','155',26606,'555-965-5555','www.course2.com');
/*!40000 ALTER TABLE `t_golfcoursedetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_golfcoursehistory`
--

DROP TABLE IF EXISTS `t_golfcoursehistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_golfcoursehistory` (
  `golfCourseHistory_pk` int(11) NOT NULL AUTO_INCREMENT,
  `courseID` int(11) DEFAULT NULL,
  `scoreHistory` int(11) NOT NULL,
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`golfCourseHistory_pk`),
  UNIQUE KEY `golfCourseHistory_pk` (`golfCourseHistory_pk`),
  UNIQUE KEY `golfCourseHistory_pk_UNIQUE` (`golfCourseHistory_pk`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_golfcoursehistory`
--

LOCK TABLES `t_golfcoursehistory` WRITE;
/*!40000 ALTER TABLE `t_golfcoursehistory` DISABLE KEYS */;
INSERT INTO `t_golfcoursehistory` VALUES (1,1,1,'2013-12-02 02:38:12'),(2,1,2,'2013-12-02 02:38:12'),(3,17,8,'2013-12-02 03:25:44');
/*!40000 ALTER TABLE `t_golfcoursehistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_holes`
--

DROP TABLE IF EXISTS `t_holes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_holes` (
  `holeID` int(11) NOT NULL AUTO_INCREMENT,
  `golfCourseID` int(11) NOT NULL,
  `holeNumber` int(11) DEFAULT NULL,
  `whiteTee` varchar(45) DEFAULT NULL,
  `redTee` varchar(45) DEFAULT NULL,
  `blueTee` varchar(45) DEFAULT NULL,
  `handicap` varchar(45) DEFAULT NULL,
  `par` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`holeID`),
  UNIQUE KEY `holeID` (`holeID`),
  UNIQUE KEY `holeID_UNIQUE` (`holeID`),
  KEY `golfCourseID_fk_idx` (`golfCourseID`),
  CONSTRAINT `golfCourseID_fk` FOREIGN KEY (`golfCourseID`) REFERENCES `t_golfcoursedetails` (`courseID_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=217 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_holes`
--

LOCK TABLES `t_holes` WRITE;
/*!40000 ALTER TABLE `t_holes` DISABLE KEYS */;
INSERT INTO `t_holes` VALUES (19,1,1,'339','307','366','11','4'),(20,1,2,'373','334','417','3','4'),(21,1,3,'369','338','409','7','4'),(22,1,4,'128','106','154','17','3'),(23,1,5,'514','477','545','1','5'),(24,1,6,'242','192','292','13','4'),(25,1,7,'141','102','163','15','3'),(26,1,8,'374','324','412','5','4'),(27,1,9,'327','295','366','9','4'),(28,1,10,'376','346','422','10','4'),(29,1,11,'347','298','391','2','4'),(30,1,12,'141','118','168','18','3'),(31,1,13,'458','408','503','12','5'),(32,1,14,'290','276','323','14','4'),(33,1,15,'93','78','123','16','3'),(34,1,16,'352','274','394','6','4'),(35,1,17,'380','332','416','8','4'),(36,1,18,'434','385','477','4','5'),(55,9,1,'339','307','366','11','4'),(56,9,2,'373','334','417','3','4'),(57,9,3,'369','338','409','7','4'),(58,9,4,'128','106','154','17','3'),(59,9,5,'514','477','545','1','5'),(60,9,6,'242','192','292','13','4'),(61,9,7,'141','102','163','15','3'),(62,9,8,'374','324','412','5','4'),(63,9,9,'327','295','366','9','4'),(64,9,10,'376','346','422','10','4'),(65,9,11,'347','298','391','2','4'),(66,9,12,'141','118','168','18','3'),(67,9,13,'458','408','503','12','4'),(68,9,14,'290','276','323','14','4'),(69,9,15,'93','78','123','16','3'),(70,9,16,'352','274','394','6','4'),(71,9,17,'380','332','416','8','4'),(72,9,18,'434','385','477','4','5'),(73,10,1,'339','307','366','11','4'),(74,10,2,'373','334','417','3','4'),(75,10,3,'369','338','409','7','4'),(76,10,4,'128','106','154','17','3'),(77,10,5,'514','477','545','1','5'),(78,10,6,'242','192','292','13','4'),(79,10,7,'141','102','163','15','3'),(80,10,8,'374','324','412','5','4'),(81,10,9,'327','295','366','9','4'),(82,10,10,'376','346','422','10','4'),(83,10,11,'347','298','391','2','4'),(84,10,12,'141','118','168','18','3'),(85,10,13,'458','408','503','12','4'),(86,10,14,'290','276','323','14','4'),(87,10,15,'93','78','123','16','3'),(88,10,16,'352','274','394','6','4'),(89,10,17,'380','332','416','8','4'),(90,10,18,'434','385','477','4','5'),(91,11,1,'339','307','366','11','4'),(92,11,2,'373','334','417','3','4'),(93,11,3,'369','338','409','7','4'),(94,11,4,'128','106','154','17','3'),(95,11,5,'514','477','545','1','5'),(96,11,6,'242','192','292','13','4'),(97,11,7,'141','102','163','15','3'),(98,11,8,'374','324','412','5','4'),(99,11,9,'327','295','366','9','4'),(100,11,10,'376','346','422','10','4'),(101,11,11,'347','298','391','2','4'),(102,11,12,'141','118','168','18','3'),(103,11,13,'458','408','503','12','4'),(104,11,14,'290','276','323','14','4'),(105,11,15,'93','78','123','16','3'),(106,11,16,'352','274','394','6','4'),(107,11,17,'380','332','416','8','4'),(108,11,18,'434','385','477','4','5'),(109,12,1,'339','307','366','11','4'),(110,12,2,'373','334','417','3','4'),(111,12,3,'369','338','409','7','4'),(112,12,4,'128','106','154','17','3'),(113,12,5,'514','477','545','1','5'),(114,12,6,'242','192','292','13','4'),(115,12,7,'141','102','163','15','3'),(116,12,8,'374','324','412','5','4'),(117,12,9,'327','295','366','9','4'),(118,12,10,'376','346','422','10','4'),(119,12,11,'347','298','391','2','4'),(120,12,12,'141','118','168','18','3'),(121,12,13,'458','408','503','12','4'),(122,12,14,'290','276','323','14','4'),(123,12,15,'93','78','123','16','3'),(124,12,16,'352','274','394','6','4'),(125,12,17,'380','332','416','8','4'),(126,12,18,'434','385','477','4','5'),(127,13,1,'339','307','366','11','4'),(128,13,2,'373','334','417','3','4'),(129,13,3,'369','338','409','7','4'),(130,13,4,'128','106','154','17','3'),(131,13,5,'514','477','545','1','5'),(132,13,6,'242','192','292','13','4'),(133,13,7,'141','102','163','15','3'),(134,13,8,'374','324','412','5','4'),(135,13,9,'327','295','366','9','4'),(136,13,10,'376','346','422','10','4'),(137,13,11,'347','298','391','2','4'),(138,13,12,'141','118','168','18','3'),(139,13,13,'458','408','503','12','4'),(140,13,14,'290','276','323','14','4'),(141,13,15,'93','78','123','16','3'),(142,13,16,'352','274','394','6','4'),(143,13,17,'380','332','416','8','4'),(144,13,18,'434','385','477','4','5'),(145,14,1,'339','307','366','11','4'),(146,14,2,'373','334','417','3','4'),(147,14,3,'369','338','409','7','4'),(148,14,4,'128','106','154','17','3'),(149,14,5,'514','477','545','1','5'),(150,14,6,'242','192','292','13','4'),(151,14,7,'141','102','163','15','3'),(152,14,8,'374','324','412','5','4'),(153,14,9,'327','295','366','9','4'),(154,14,10,'376','346','422','10','4'),(155,14,11,'347','298','391','2','4'),(156,14,12,'141','118','168','18','3'),(157,14,13,'458','408','503','12','4'),(158,14,14,'290','276','323','14','4'),(159,14,15,'93','78','123','16','3'),(160,14,16,'352','274','394','6','4'),(161,14,17,'380','332','416','8','4'),(162,14,18,'434','385','477','4','5'),(163,15,1,'339','307','366','11','4'),(164,15,2,'373','334','417','3','4'),(165,15,3,'369','338','409','7','4'),(166,15,4,'128','106','154','17','3'),(167,15,5,'514','477','545','1','5'),(168,15,6,'242','192','292','13','4'),(169,15,7,'141','102','163','15','3'),(170,15,8,'374','324','412','5','4'),(171,15,9,'327','295','366','9','4'),(172,15,10,'376','346','422','10','4'),(173,15,11,'347','298','391','2','4'),(174,15,12,'141','118','168','18','3'),(175,15,13,'458','408','503','12','4'),(176,15,14,'290','276','323','14','4'),(177,15,15,'93','78','123','16','3'),(178,15,16,'352','274','394','6','4'),(179,15,17,'380','332','416','8','4'),(180,15,18,'434','385','477','4','5'),(181,16,1,'339','307','366','11','4'),(182,16,2,'373','334','417','3','4'),(183,16,3,'369','338','409','7','4'),(184,16,4,'128','106','154','17','3'),(185,16,5,'514','477','545','1','5'),(186,16,6,'242','192','292','13','4'),(187,16,7,'141','102','163','15','3'),(188,16,8,'374','324','412','5','4'),(189,16,9,'327','295','366','9','4'),(190,16,10,'376','346','422','10','4'),(191,16,11,'347','298','391','2','4'),(192,16,12,'141','118','168','18','3'),(193,16,13,'458','408','503','12','4'),(194,16,14,'290','276','323','14','4'),(195,16,15,'93','78','123','16','3'),(196,16,16,'352','274','394','6','4'),(197,16,17,'380','332','416','8','4'),(198,16,18,'434','385','477','4','5'),(199,17,1,'339','307','366','11','4'),(200,17,2,'373','334','417','3','4'),(201,17,3,'369','338','409','7','4'),(202,17,4,'128','106','154','17','3'),(203,17,5,'514','477','545','1','5'),(204,17,6,'242','192','292','13','4'),(205,17,7,'141','102','163','15','3'),(206,17,8,'374','324','412','5','4'),(207,17,9,'327','295','366','9','4'),(208,17,10,'376','346','422','10','4'),(209,17,11,'347','298','391','2','4'),(210,17,12,'141','118','168','18','3'),(211,17,13,'458','408','503','12','4'),(212,17,14,'290','276','323','14','4'),(213,17,15,'93','78','123','16','3'),(214,17,16,'352','274','394','6','4'),(215,17,17,'380','332','416','8','4'),(216,17,18,'434','385','477','4','5');
/*!40000 ALTER TABLE `t_holes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_scorecard`
--

DROP TABLE IF EXISTS `t_scorecard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_scorecard` (
  `scorecardID` int(11) NOT NULL AUTO_INCREMENT,
  `holeID` int(11) DEFAULT NULL,
  `strokes` int(11) DEFAULT NULL,
  `scoreHistory_fk` int(11) DEFAULT NULL,
  PRIMARY KEY (`scorecardID`),
  UNIQUE KEY `scorecardID` (`scorecardID`),
  KEY `hole_fk_idx` (`holeID`),
  KEY `scoreHistory_fk_idx` (`scoreHistory_fk`),
  CONSTRAINT `hole_fk` FOREIGN KEY (`holeID`) REFERENCES `t_holes` (`holeID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `scoreHistory_fk` FOREIGN KEY (`scoreHistory_fk`) REFERENCES `t_scorehistory` (`scoreHistory_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_scorecard`
--

LOCK TABLES `t_scorecard` WRITE;
/*!40000 ALTER TABLE `t_scorecard` DISABLE KEYS */;
INSERT INTO `t_scorecard` VALUES (35,19,5,1),(36,20,6,1),(37,21,4,1),(38,22,3,1),(39,23,8,1),(40,24,7,1),(41,25,2,1),(42,26,5,1),(43,27,5,1),(44,28,3,1),(45,29,4,1),(46,30,4,1),(47,31,7,1),(48,32,5,1),(49,33,4,1),(50,34,4,1),(51,35,6,1),(52,36,7,1),(53,19,4,2),(54,20,4,2),(55,21,4,2),(56,22,3,2),(57,23,5,2),(58,24,4,2),(59,25,3,2),(60,26,4,2),(61,27,4,2),(62,28,4,2),(63,29,4,2),(64,30,3,2),(65,31,5,2),(66,32,4,2),(67,33,3,2),(68,34,4,2),(69,35,4,2),(70,36,6,2),(71,199,NULL,8),(72,200,NULL,8),(73,201,NULL,8),(74,202,NULL,8),(75,203,NULL,8),(76,204,NULL,8),(77,205,NULL,8),(78,206,NULL,8),(79,207,NULL,8),(80,208,NULL,8),(81,209,NULL,8),(82,210,NULL,8),(83,211,NULL,8),(84,212,NULL,8),(85,213,NULL,8),(86,214,NULL,8),(87,215,NULL,8),(88,216,NULL,8);
/*!40000 ALTER TABLE `t_scorecard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_scorehistory`
--

DROP TABLE IF EXISTS `t_scorehistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_scorehistory` (
  `scoreHistory_pk` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) DEFAULT NULL,
  `totalScore` int(11) DEFAULT NULL,
  `courseID` int(11) DEFAULT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY `scoreHistory_pk` (`scoreHistory_pk`),
  KEY `courseID_fk_idx` (`courseID`),
  CONSTRAINT `courseID_fk` FOREIGN KEY (`courseID`) REFERENCES `t_golfcoursedetails` (`courseID_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_scorehistory`
--

LOCK TABLES `t_scorehistory` WRITE;
/*!40000 ALTER TABLE `t_scorehistory` DISABLE KEYS */;
INSERT INTO `t_scorehistory` VALUES (1,1,100,1,'2013-12-02 02:18:40'),(2,2,72,1,'2013-12-02 02:18:40'),(4,3,0,13,'2013-12-02 03:22:36'),(5,3,0,14,'2013-12-02 03:23:29'),(6,3,0,15,'2013-12-02 03:24:45'),(7,3,0,16,'2013-12-02 03:25:20'),(8,3,0,17,'2013-12-02 03:25:44');
/*!40000 ALTER TABLE `t_scorehistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `userID_pk` int(11) NOT NULL AUTO_INCREMENT,
  `faceBookID` varchar(45) NOT NULL,
  PRIMARY KEY (`userID_pk`),
  UNIQUE KEY `userID_pk` (`userID_pk`),
  UNIQUE KEY `faceBookID_UNIQUE` (`faceBookID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'mgisoni'),(2,'someguy'),(3,'Tim');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `v_userscorecard`
--

DROP TABLE IF EXISTS `v_userscorecard`;
/*!50001 DROP VIEW IF EXISTS `v_userscorecard`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `v_userscorecard` (
  `user` tinyint NOT NULL,
  `holeNumber` tinyint NOT NULL,
  `blueTee` tinyint NOT NULL,
  `whiteTee` tinyint NOT NULL,
  `redTee` tinyint NOT NULL,
  `handicap` tinyint NOT NULL,
  `par` tinyint NOT NULL,
  `strokes` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Dumping routines for database 'golfr'
--
/*!50003 DROP PROCEDURE IF EXISTS `CreateUser` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `CreateUser`()
BEGIN

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `v_userscorecard`
--

/*!50001 DROP TABLE IF EXISTS `v_userscorecard`*/;
/*!50001 DROP VIEW IF EXISTS `v_userscorecard`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_userscorecard` AS select `t_user`.`faceBookID` AS `user`,`t_holes`.`holeNumber` AS `holeNumber`,`t_holes`.`blueTee` AS `blueTee`,`t_holes`.`whiteTee` AS `whiteTee`,`t_holes`.`redTee` AS `redTee`,`t_holes`.`handicap` AS `handicap`,`t_holes`.`par` AS `par`,`t_scorecard`.`strokes` AS `strokes` from (((`t_holes` join `t_scorecard`) join `t_scorehistory`) join `t_user`) where ((`t_holes`.`holeID` = `t_scorecard`.`holeID`) and (`t_scorecard`.`scoreHistory_fk` = `t_scorehistory`.`scoreHistory_pk`) and (`t_scorehistory`.`userID` = `t_user`.`userID_pk`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-12-02  0:09:14

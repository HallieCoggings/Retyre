-- MySQL dump 10.13  Distrib 9.4.0, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: retyre
-- ------------------------------------------------------
-- Server version	9.4.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `idPerson` int NOT NULL,
  `SPE` enum('COACHBUILDER','ELECTRONIC','MACHINERY') DEFAULT NULL,
  PRIMARY KEY (`idPerson`),
  CONSTRAINT `FK3r2xvxojq96mrc9xiygmc3vo1` FOREIGN KEY (`idPerson`) REFERENCES `person` (`idPerson`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (4,'MACHINERY'),(5,'ELECTRONIC'),(6,'COACHBUILDER');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `intervention`
--

DROP TABLE IF EXISTS `intervention`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `intervention` (
  `DateIntervention` date DEFAULT NULL,
  `FK_Employee` int DEFAULT NULL,
  `FK_IntervType` int DEFAULT NULL,
  `Price` decimal(10,2) DEFAULT NULL,
  `idIntervention` int NOT NULL AUTO_INCREMENT,
  `kmMax` float DEFAULT NULL,
  `FK_Vehicle` varchar(15) DEFAULT NULL,
  `Status` enum('CANCELED','DONE','ONGOING','ONHOLD') DEFAULT NULL,
  PRIMARY KEY (`idIntervention`),
  KEY `FKffg5tjusrkhg4ntdvrmt5gls` (`FK_Employee`),
  KEY `FKj540axp5qqxcrqbe8hpjr336b` (`FK_IntervType`),
  KEY `FKlxv41dh7s9kp183yj7019sqbs` (`FK_Vehicle`),
  CONSTRAINT `FKffg5tjusrkhg4ntdvrmt5gls` FOREIGN KEY (`FK_Employee`) REFERENCES `employee` (`idPerson`),
  CONSTRAINT `FKj540axp5qqxcrqbe8hpjr336b` FOREIGN KEY (`FK_IntervType`) REFERENCES `interventiontype` (`idInterventionType`),
  CONSTRAINT `FKlxv41dh7s9kp183yj7019sqbs` FOREIGN KEY (`FK_Vehicle`) REFERENCES `vehicle` (`licencePlate`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `intervention`
--

LOCK TABLES `intervention` WRITE;
/*!40000 ALTER TABLE `intervention` DISABLE KEYS */;
INSERT INTO `intervention` VALUES ('2025-06-06',4,1,0.00,1,0,'AA-159-BB','ONGOING'),('2015-11-14',6,2,0.00,2,0,'OUTATIME','ONHOLD'),('2026-01-10',5,3,50.00,3,0,'CC-456-TV','DONE'),('2027-01-10',NULL,3,0.00,4,1025,'CC-456-TV','ONGOING');
/*!40000 ALTER TABLE `intervention` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interventiontype`
--

DROP TABLE IF EXISTS `interventiontype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interventiontype` (
  `DaysBetween` int DEFAULT NULL,
  `idInterventionType` int NOT NULL AUTO_INCREMENT,
  `kmMax` int DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Category` enum('MAINTENANCE','REPAIR') DEFAULT NULL,
  PRIMARY KEY (`idInterventionType`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interventiontype`
--

LOCK TABLES `interventiontype` WRITE;
/*!40000 ALTER TABLE `interventiontype` DISABLE KEYS */;
INSERT INTO `interventiontype` VALUES (90,1,60,'Oil Filtering','MAINTENANCE'),(0,2,0,'Motor Reparation','REPAIR'),(365,3,1000,'Change Tyres','MAINTENANCE');
/*!40000 ALTER TABLE `interventiontype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `owner`
--

DROP TABLE IF EXISTS `owner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `owner` (
  `idPerson` int NOT NULL,
  `Personal` varchar(255) NOT NULL,
  PRIMARY KEY (`idPerson`),
  CONSTRAINT `FKqtaj1uip81cy4alrta396gcyy` FOREIGN KEY (`idPerson`) REFERENCES `person` (`idPerson`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `owner`
--

LOCK TABLES `owner` WRITE;
/*!40000 ALTER TABLE `owner` DISABLE KEYS */;
INSERT INTO `owner` VALUES (1,'8989-456-132'),(2,'9874-123-456'),(3,'1985-989-991');
/*!40000 ALTER TABLE `owner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
  `idPerson` int NOT NULL AUTO_INCREMENT,
  `P_Type` varchar(31) NOT NULL,
  `First Name` varchar(255) NOT NULL,
  `Name` varchar(255) NOT NULL,
  PRIMARY KEY (`idPerson`),
  CONSTRAINT `person_chk_1` CHECK ((`P_Type` in (_utf8mb4'Owner',_utf8mb4'Employee')))
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'Owner','John','Doe'),(2,'Owner','Jennie','Kim'),(3,'Owner','Emet','Brown'),(4,'Employee','Rumi','Kim'),(5,'Employee','Mira','Yoon'),(6,'Employee','Zoey','Park');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `piece`
--

DROP TABLE IF EXISTS `piece`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `piece` (
  `PU_eur)` decimal(10,2) DEFAULT NULL,
  `Category` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Ref` varchar(255) NOT NULL,
  PRIMARY KEY (`Ref`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `piece`
--

LOCK TABLES `piece` WRITE;
/*!40000 ALTER TABLE `piece` DISABLE KEYS */;
INSERT INTO `piece` VALUES (28.00,'Liquid','Oil used for warious task','Oil','AZE123654'),(1500.00,'TimeTravel','Makes Time travel Possible','Flux Conductor','BTF1985'),(200.00,'Mechanical','Piston used to make the explosion','Piston','CAR2006'),(15.00,'Electrical','Used to light the motor','Sparkle','CP2077'),(140.00,'Mechanical','A crankshaft','Crankshaft','YTB202563');
/*!40000 ALTER TABLE `piece` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `piecesused`
--

DROP TABLE IF EXISTS `piecesused`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `piecesused` (
  `FK_InterventionType` int NOT NULL,
  `FK_Piece` varchar(255) NOT NULL,
  PRIMARY KEY (`FK_InterventionType`,`FK_Piece`),
  KEY `FKh6c1g6svfmgdlujpdh7qbfqp8` (`FK_Piece`),
  CONSTRAINT `FKh6c1g6svfmgdlujpdh7qbfqp8` FOREIGN KEY (`FK_Piece`) REFERENCES `piece` (`Ref`),
  CONSTRAINT `FKpofwyef55idux43vkf5l3teua` FOREIGN KEY (`FK_InterventionType`) REFERENCES `interventiontype` (`idInterventionType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `piecesused`
--

LOCK TABLES `piecesused` WRITE;
/*!40000 ALTER TABLE `piecesused` DISABLE KEYS */;
INSERT INTO `piecesused` VALUES (1,'AZE123654');
/*!40000 ALTER TABLE `piecesused` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle` (
  `CirculationDate` date DEFAULT NULL,
  `FK_Owner` int DEFAULT NULL,
  `FK_TypeV` int DEFAULT NULL,
  `Mileage` decimal(10,2) DEFAULT NULL,
  `licencePlate` varchar(15) NOT NULL,
  PRIMARY KEY (`licencePlate`),
  KEY `FKch3hhykb3us6lrlp0paspvnl8` (`FK_Owner`),
  KEY `FKfkuf8uqtm3164f4d2qnkalqux` (`FK_TypeV`),
  CONSTRAINT `FKch3hhykb3us6lrlp0paspvnl8` FOREIGN KEY (`FK_Owner`) REFERENCES `owner` (`idPerson`),
  CONSTRAINT `FKfkuf8uqtm3164f4d2qnkalqux` FOREIGN KEY (`FK_TypeV`) REFERENCES `vehicletype` (`idVehiculeType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
INSERT INTO `vehicle` VALUES ('2000-12-25',1,1,25000.00,'AA-159-BB'),('0199-04-05',1,3,25.00,'CC-456-TV'),('2016-08-06',2,2,2016.00,'DD-789-DD'),('1985-10-30',3,4,400.00,'OUTATIME');
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehiclecompo`
--

DROP TABLE IF EXISTS `vehiclecompo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehiclecompo` (
  `FK_Vehicle` int NOT NULL,
  `FK_Piece` varchar(255) NOT NULL,
  PRIMARY KEY (`FK_Vehicle`,`FK_Piece`),
  KEY `FK9b8h3nii4sutgk1sd1x4b9bf2` (`FK_Piece`),
  CONSTRAINT `FK9b8h3nii4sutgk1sd1x4b9bf2` FOREIGN KEY (`FK_Piece`) REFERENCES `piece` (`Ref`),
  CONSTRAINT `FKka2viyn19xwg9hoc7vg2ww77w` FOREIGN KEY (`FK_Vehicle`) REFERENCES `vehicletype` (`idVehiculeType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehiclecompo`
--

LOCK TABLES `vehiclecompo` WRITE;
/*!40000 ALTER TABLE `vehiclecompo` DISABLE KEYS */;
INSERT INTO `vehiclecompo` VALUES (1,'AZE123654'),(4,'BTF1985'),(2,'CAR2006'),(1,'CP2077'),(3,'CP2077'),(2,'YTB202563');
/*!40000 ALTER TABLE `vehiclecompo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicletype`
--

DROP TABLE IF EXISTS `vehicletype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicletype` (
  `Door` int DEFAULT NULL,
  `Places` int DEFAULT NULL,
  `Power` int DEFAULT NULL,
  `idVehiculeType` int NOT NULL AUTO_INCREMENT,
  `Model` varchar(100) NOT NULL,
  `Brand` varchar(255) NOT NULL,
  `Energy` enum('DIESEL','ELECTRIC','FUEL','HYBRID') DEFAULT NULL,
  `Transmission` enum('AUTOMATIC','MANUAL') DEFAULT NULL,
  PRIMARY KEY (`idVehiculeType`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicletype`
--

LOCK TABLES `vehicletype` WRITE;
/*!40000 ALTER TABLE `vehicletype` DISABLE KEYS */;
INSERT INTO `vehicletype` VALUES (3,2,180,1,'901','Ferrari','FUEL','MANUAL'),(5,5,100,2,'Zoe','Renault','ELECTRIC','AUTOMATIC'),(5,5,500,3,'Alvarado','Villefort','HYBRID','MANUAL'),(3,2,121,4,'DMC-12','DoLorean','DIESEL','MANUAL');
/*!40000 ALTER TABLE `vehicletype` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-01-10 22:32:05

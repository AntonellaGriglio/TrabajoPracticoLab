CREATE DATABASE  IF NOT EXISTS `tp_lab` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `tp_lab`;
-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: tp_lab
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado` (
  `Legajo` int NOT NULL,
  `Nombre` varchar(45) NOT NULL,
  `Apellido` varchar(45) NOT NULL,
  `Fecha_Nac` date NOT NULL,
  `Fecha_Ingreso` date NOT NULL,
  `Area` varchar(45) NOT NULL,
  `SueldoBruto` decimal(10,0) NOT NULL,
  PRIMARY KEY (`Legajo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES (1100,'Empl','efks','2000-05-05','2020-05-06','Ventas',35251),(1150,'Angela','Garcia','1998-06-28','2021-08-26','Compras',30000),(1200,'Antonella','Griglio','2000-01-18','2020-05-25','Ventas',25000),(1430,'Jeremias','Cortese','2001-04-24','2022-09-12','Ventas',35000);
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recibo`
--

DROP TABLE IF EXISTS `recibo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recibo` (
  `NumeroRecibo` int NOT NULL AUTO_INCREMENT,
  `Legajo` int NOT NULL,
  `Anio` int NOT NULL,
  `Mes` int NOT NULL,
  `MontoAntigüedad` decimal(10,0) NOT NULL,
  `Jubilacion` decimal(10,0) NOT NULL,
  `ObraSocial` decimal(10,0) NOT NULL,
  `FondoComplejidad` decimal(10,0) NOT NULL,
  PRIMARY KEY (`NumeroRecibo`),
  KEY `Fk_empleado_idx` (`Legajo`),
  CONSTRAINT `Fk_empleado` FOREIGN KEY (`Legajo`) REFERENCES `empleado` (`Legajo`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recibo`
--

LOCK TABLES `recibo` WRITE;
/*!40000 ALTER TABLE `recibo` DISABLE KEYS */;
INSERT INTO `recibo` VALUES (3,1200,2022,5,5000,2500,2365,2555),(4,1200,2022,5,5600,1200,2300,3000),(5,1200,2022,6,6000,1500,2400,2800),(6,1150,2022,5,4000,1000,2200,2300),(7,1430,2022,5,2500,1100,2600,2900),(8,1430,2022,7,2666,1325,5682,4652),(11,1100,2022,8,2646,3655,2698,3662);
/*!40000 ALTER TABLE `recibo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-14 19:25:03

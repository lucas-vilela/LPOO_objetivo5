-- MariaDB dump 10.19  Distrib 10.7.3-MariaDB, for Win64 (AMD64)
--
-- Host: 127.0.0.1    Database: teste_1
-- ------------------------------------------------------
-- Server version	10.7.3-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `animal`
--

DROP TABLE IF EXISTS `animal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `animal` (
  `id_animal` int(11) NOT NULL AUTO_INCREMENT,
  `id_cli` int(100) DEFAULT NULL,
  `id_esp` int(100) DEFAULT NULL,
  `nome_animal` varchar(100) NOT NULL,
  `idade_animal` int(100) NOT NULL,
  `sexo_animal` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_animal`),
  KEY `id_cli` (`id_cli`),
  KEY `id_esp` (`id_esp`),
  CONSTRAINT `animal_fk1` FOREIGN KEY (`id_cli`) REFERENCES `cliente` (`id_cli`),
  CONSTRAINT `animal_fk2` FOREIGN KEY (`id_esp`) REFERENCES `especie` (`id_esp`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animal`
--

LOCK TABLES `animal` WRITE;
/*!40000 ALTER TABLE `animal` DISABLE KEYS */;
INSERT INTO `animal` (`id_animal`, `id_cli`, `id_esp`, `nome_animal`, `idade_animal`, `sexo_animal`) VALUES (9,6,5,'Mcqueen',3,1),
(10,7,5,'Kevin',5,1),
(11,8,4,'Pipoca',2,0),
(12,9,6,'Ritch',1,1),
(13,6,5,'Chewee',2,1);
/*!40000 ALTER TABLE `animal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `id_cli` int(11) NOT NULL AUTO_INCREMENT,
  `nom_cli` varchar(100) NOT NULL,
  `end_cli` varchar(100) NOT NULL,
  `tel_cli` varchar(100) NOT NULL,
  `cep_cli` varchar(100) NOT NULL,
  `email_cli` varchar(100) NOT NULL,
  PRIMARY KEY (`id_cli`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` (`id_cli`, `nom_cli`, `end_cli`, `tel_cli`, `cep_cli`, `email_cli`) VALUES (6,'Lucas','Rua Manoel Caetano da Silva','(53) 98405-8779','96025230','lucas@gmail.com'),
(7,'Maria','Vila da Quinta','(53) 99123-9332','96023320','maria@gmail.com'),
(8,'Yuri','Canguçu','(53) 98423-2323','06022422','yuri@gmail.com'),
(9,'Clarice','Bachini','(53) 98451-3212','96031321','clarice@gmail.com');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consulta`
--

DROP TABLE IF EXISTS `consulta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `consulta` (
  `id_con` int(11) NOT NULL AUTO_INCREMENT,
  `id_trat` int(100) DEFAULT NULL,
  `id_vet` int(100) DEFAULT NULL,
  `dat_con` date NOT NULL,
  `historico` varchar(100) NOT NULL,
  PRIMARY KEY (`id_con`),
  KEY `id_trat` (`id_trat`),
  KEY `id_vet` (`id_vet`),
  CONSTRAINT `consulta_fk1` FOREIGN KEY (`id_trat`) REFERENCES `tratamento` (`id_trat`) ON DELETE CASCADE,
  CONSTRAINT `consulta_fk2` FOREIGN KEY (`id_vet`) REFERENCES `veterinario` (`id_vet`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consulta`
--

LOCK TABLES `consulta` WRITE;
/*!40000 ALTER TABLE `consulta` DISABLE KEYS */;
INSERT INTO `consulta` (`id_con`, `id_trat`, `id_vet`, `dat_con`, `historico`) VALUES (40,26,4,'2022-04-03','Pet deu entrada com indicios de fratura na pata dianteira direita.'),
(42,26,4,'2022-04-03','Pet Chewee não apresentou melhora no quadro.');
/*!40000 ALTER TABLE `consulta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especie`
--

DROP TABLE IF EXISTS `especie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `especie` (
  `id_esp` int(11) NOT NULL AUTO_INCREMENT,
  `nom_esp` varchar(100) NOT NULL,
  PRIMARY KEY (`id_esp`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especie`
--

LOCK TABLES `especie` WRITE;
/*!40000 ALTER TABLE `especie` DISABLE KEYS */;
INSERT INTO `especie` (`id_esp`, `nom_esp`) VALUES (4,'Gato'),
(5,'Cachorro'),
(6,'Coelho'),
(7,'Pássaro');
/*!40000 ALTER TABLE `especie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exame`
--

DROP TABLE IF EXISTS `exame`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exame` (
  `id_exame` int(11) NOT NULL AUTO_INCREMENT,
  `id_con` int(100) DEFAULT NULL,
  `des_exame` varchar(100) NOT NULL,
  PRIMARY KEY (`id_exame`),
  KEY `id_con` (`id_con`),
  CONSTRAINT `exame_fk` FOREIGN KEY (`id_con`) REFERENCES `consulta` (`id_con`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exame`
--

LOCK TABLES `exame` WRITE;
/*!40000 ALTER TABLE `exame` DISABLE KEYS */;
INSERT INTO `exame` (`id_exame`, `id_con`, `des_exame`) VALUES (22,40,'Raio-x pata dianteira direita.'),
(24,42,'Ultrassom pata dianteira direita');
/*!40000 ALTER TABLE `exame` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tratamento`
--

DROP TABLE IF EXISTS `tratamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tratamento` (
  `id_trat` int(11) NOT NULL AUTO_INCREMENT,
  `id_animal` int(100) DEFAULT NULL,
  `dat_ini` date NOT NULL,
  `dat_fin` date NOT NULL,
  PRIMARY KEY (`id_trat`),
  KEY `id_animal` (`id_animal`),
  CONSTRAINT `tartamento_fk` FOREIGN KEY (`id_animal`) REFERENCES `animal` (`id_animal`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tratamento`
--

LOCK TABLES `tratamento` WRITE;
/*!40000 ALTER TABLE `tratamento` DISABLE KEYS */;
INSERT INTO `tratamento` (`id_trat`, `id_animal`, `dat_ini`, `dat_fin`) VALUES (26,13,'2021-12-26','2021-12-26');
/*!40000 ALTER TABLE `tratamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `veterinario`
--

DROP TABLE IF EXISTS `veterinario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `veterinario` (
  `id_vet` int(11) NOT NULL AUTO_INCREMENT,
  `nom_vet` varchar(100) NOT NULL,
  `end_vet` varchar(100) NOT NULL,
  `tel_vet` varchar(100) NOT NULL,
  PRIMARY KEY (`id_vet`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `veterinario`
--

LOCK TABLES `veterinario` WRITE;
/*!40000 ALTER TABLE `veterinario` DISABLE KEYS */;
INSERT INTO `veterinario` (`id_vet`, `nom_vet`, `end_vet`, `tel_vet`) VALUES (4,'Hermes','Av Bento Gonçalves','(53) 98435-3675'),
(5,'Iara','Canguçu','(53) 98483-9321');
/*!40000 ALTER TABLE `veterinario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-03 23:26:12

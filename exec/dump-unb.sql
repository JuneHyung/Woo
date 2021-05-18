-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: k4d109.p.ssafy.io    Database: unb
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `fridge`
--

DROP TABLE IF EXISTS `fridge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fridge` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `type` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fridgetouserfk_idx` (`user_id`),
  CONSTRAINT `fridgetouserfk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fridge`
--

LOCK TABLES `fridge` WRITE;
/*!40000 ALTER TABLE `fridge` DISABLE KEYS */;
INSERT INTO `fridge` VALUES (11,'자취방',44,6),(19,'고기 냉장고',55,6),(31,'비밀창고',4444,6),(35,'본가',5555,6);
/*!40000 ALTER TABLE `fridge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredients`
--

DROP TABLE IF EXISTS `ingredients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredients` (
  `id` int NOT NULL AUTO_INCREMENT,
  `expired` date DEFAULT NULL,
  `locx` int NOT NULL,
  `locy` int NOT NULL,
  `fridge_id` int NOT NULL,
  `ingredientsdetail_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ingredientstofridgefk_idx` (`fridge_id`),
  KEY `ingredientstoingredientsdetailfk_idx` (`ingredientsdetail_id`),
  CONSTRAINT `ingredientstofridgefk` FOREIGN KEY (`fridge_id`) REFERENCES `fridge` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ingredientstoingredientsdetailfk` FOREIGN KEY (`ingredientsdetail_id`) REFERENCES `ingredientsdetail` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredients`
--

LOCK TABLES `ingredients` WRITE;
/*!40000 ALTER TABLE `ingredients` DISABLE KEYS */;
INSERT INTO `ingredients` VALUES (22,'2021-05-15',2,2,19,11),(24,'2021-05-15',2,2,19,14),(40,'2021-05-28',1,0,11,3),(41,'2021-05-28',0,1,11,4),(42,'2021-05-28',1,0,11,5),(43,'2021-05-28',0,0,11,6),(44,'2021-05-28',2,0,11,7),(46,'2021-05-28',1,0,11,9),(47,'2021-05-28',2,0,11,10),(48,'2021-05-28',3,1,11,11),(49,'2021-05-28',3,0,11,12),(50,'2021-05-28',10,10,11,13),(51,'2021-05-28',10,10,11,14),(52,'2021-05-28',10,10,11,15),(53,'2021-05-28',1,1,11,16),(54,'2021-05-28',10,10,11,17),(55,'2021-05-28',10,10,11,18),(56,'2021-05-14',10,10,11,20),(57,'2021-05-12',10,10,11,21),(58,'2021-05-13',10,10,11,22),(59,'2021-05-13',10,10,11,23),(60,'2021-05-13',10,10,11,24),(63,'2021-06-05',2,1,11,39);
/*!40000 ALTER TABLE `ingredients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredientsdetail`
--

DROP TABLE IF EXISTS `ingredientsdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredientsdetail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `category` varchar(45) NOT NULL,
  `image` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredientsdetail`
--

LOCK TABLES `ingredientsdetail` WRITE;
/*!40000 ALTER TABLE `ingredientsdetail` DISABLE KEYS */;
INSERT INTO `ingredientsdetail` VALUES (3,'사과','과일','사과.png'),(4,'달걀','알류','달걀.png'),(5,'바나나','과일','바나나.png'),(6,'소금','조미료','소금.png'),(7,'아보카도','과일','아보카도.png'),(9,'청경채','채소','청경채.png'),(10,'브로콜리','채소','브로콜리.png'),(11,'버터','유제품','버터.png'),(12,'배추','채소','배추.png'),(13,'닭고기','육류','닭고기.jpg'),(14,'콜라','음료','콜라.png'),(15,'오이','채소','오이.png'),(16,'포도','과일','포도.png'),(17,'레몬','과일','레몬.png'),(18,'우유','유제품','우유.png'),(20,'고추','채소','풋고추.png'),(21,'새우','갑각류','새우.png'),(22,'당근','채소','당근.png'),(23,'오징어','해산물','오징어.png'),(24,'가지','채소','가지.png'),(26,'양파','채소','양파.png'),(27,'치즈','유제품','치즈.png'),(28,'마늘','채소','마늘.png'),(29,'토마토','채소','토마토.png'),(31,'두리안','과일','두리안.png'),(32,'키위','과일','키위.png'),(33,'매실','과일','매실.png'),(34,'망고','과일','망고.png'),(35,'참외','과일','참외.png'),(36,'석류','과일','석류.png'),(37,'감','과일','감.png'),(38,'배','과일','배.png'),(39,'파인애플','과일','파인애플.png'),(40,'복숭아','과일','복숭아.png'),(41,'코코넛','과일','코코넛.png'),(42,'유자','과일','유자.png'),(43,'자몽','과일','자몽.png'),(44,'수박','과일','수박.png'),(45,'멜론','과일','멜론.png'),(46,'블루베리','과일','블루베리.png'),(47,'무화과','과일','무화과.png'),(48,'귤','과일','귤.png'),(49,'오렌지','과일','오렌지.png'),(50,'딸기','과일','딸기.png'),(51,'체리','과일','체리.png'),(52,'복분자','과일','복분자.png'),(53,'메추리알','알류','메추리알.png'),(54,'게','갑각류','게.png'),(55,'바닷가재','갑각류','바닷가재.png'),(56,'상추','채소','상추.png'),(57,'깻잎','채소','깻잎.png'),(58,'아스파라거스','채소','아스파라거스.png'),(59,'호박','채소','호박.png'),(60,'파','채소','파.png'),(62,'전복','해산물','전복.png'),(63,'굴','해산물','굴.png'),(64,'소라','해산물','소라.png'),(65,'조개','해산물','조개.png'),(66,'홍합','해산물','홍합.png'),(67,'생선','해산물','생선.png'),(68,'돼지고기','육류','돼지고기.png'),(69,'소고기','육류','소고기.png'),(70,'오리고기','육류','오리고기.png'),(71,'양고기','육류','양고기.png'),(73,'요거트','유제품','요거트.png'),(74,'아이스크림','유제품','아이스크림.png'),(75,'사이다','음료','사이다.png'),(76,'오렌지주스','음료','오렌지주스.png'),(77,'포도주스','음료','포도주스.png'),(78,'사과주스','음료','사과주스.png'),(79,'자몽주스','음료','자몽주스.png'),(80,'매실주스','음료','매실주스.png'),(81,'소주','음료','소주.png'),(82,'맥주','음료','맥주.png'),(83,'막걸리','음료','막걸리.png');
/*!40000 ALTER TABLE `ingredientsdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `main`
--

DROP TABLE IF EXISTS `main`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `main` (
  `id` int NOT NULL AUTO_INCREMENT,
  `recipe_id` int NOT NULL,
  `ingredientsdetail_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `maintorecipefk_idx` (`recipe_id`),
  KEY `maintoingredientsdeatilfk_idx` (`ingredientsdetail_id`),
  CONSTRAINT `maintoingredientsdeatilfk` FOREIGN KEY (`ingredientsdetail_id`) REFERENCES `ingredientsdetail` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `maintorecipefk` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `main`
--

LOCK TABLES `main` WRITE;
/*!40000 ALTER TABLE `main` DISABLE KEYS */;
INSERT INTO `main` VALUES (5,2,4),(7,2,21),(8,2,23),(9,3,24),(11,3,27),(12,3,29),(26,8,24),(27,9,24),(28,9,27),(29,9,29),(30,10,24),(31,10,27),(32,11,24),(33,11,68),(34,12,24),(35,13,24),(36,13,21),(37,14,24);
/*!40000 ALTER TABLE `main` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `contents` varchar(256) DEFAULT NULL,
  `date` datetime DEFAULT CURRENT_TIMESTAMP,
  `imagecnt` int DEFAULT '0',
  `visit` int DEFAULT '0',
  `good` int DEFAULT '0',
  `hate` int DEFAULT '0',
  `user_name` varchar(45) NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `posttouserfk_idx` (`user_id`),
  CONSTRAINT `posttouserfk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipe`
--

DROP TABLE IF EXISTS `recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipe` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `category` varchar(45) NOT NULL,
  `subcategory` varchar(45) NOT NULL,
  `url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `views` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe`
--

LOCK TABLES `recipe` WRITE;
/*!40000 ALTER TABLE `recipe` DISABLE KEYS */;
INSERT INTO `recipe` VALUES (1,'알리오올리오 떡볶이','한식','분식','https://youtu.be/4QwUVgVoMSE',23),(2,'해물 파전','한식','전','https://youtu.be/G6xqFFzB5C0',23),(3,'가지 그라탕','양식','그라탕','https://youtu.be/CgyTNJbLxy0',11),(8,'가지 탕수육','중식','튀김','https://youtu.be/QehD20l1iHs',6),(9,'가지 라자냐','양식','파스타','https://youtu.be/uTjl5M_8M6Q',0),(10,'가지 피자','양식','피자','https://youtu.be/nWHeTAn52Uc',0),(11,'가지 덮밥','일식','덮밥','https://youtu.be/weABfBMPsT0',0),(12,'가지 나물','한식','나물','https://youtu.be/QyGceIDX2Qs',0),(13,'가지 멘보샤','중식','튀김','https://youtu.be/0PPVdCr9JfQ',0),(14,'가지 무침','한식','무침','https://youtu.be/aM4MxS_qwEw',0);
/*!40000 ALTER TABLE `recipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sub`
--

DROP TABLE IF EXISTS `sub`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sub` (
  `id` int NOT NULL AUTO_INCREMENT,
  `recipe_id` int NOT NULL,
  `ingredientsdetail_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `subtorecipefk_idx` (`recipe_id`),
  KEY `subtoingredientsdeatilfk_idx` (`ingredientsdetail_id`),
  CONSTRAINT `subtoingredientsdeatilfk` FOREIGN KEY (`ingredientsdetail_id`) REFERENCES `ingredientsdetail` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `subtorecipefk` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub`
--

LOCK TABLES `sub` WRITE;
/*!40000 ALTER TABLE `sub` DISABLE KEYS */;
INSERT INTO `sub` VALUES (5,2,20),(6,2,22),(7,3,26),(8,3,28),(20,8,26),(21,8,28),(22,10,26),(23,10,68),(24,11,26),(25,11,60),(26,12,20),(27,12,60),(28,13,20),(29,13,60),(30,14,6);
/*!40000 ALTER TABLE `sub` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscribe`
--

DROP TABLE IF EXISTS `subscribe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subscribe` (
  `id` int NOT NULL AUTO_INCREMENT,
  `subscribe_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnpc4xqvgbgx7tguyijna5pfwr` (`subscribe_id`),
  KEY `FK31lkjb2x51cw4bljtmswtj6r` (`user_id`),
  CONSTRAINT `FK31lkjb2x51cw4bljtmswtj6r` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKnpc4xqvgbgx7tguyijna5pfwr` FOREIGN KEY (`subscribe_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscribe`
--

LOCK TABLES `subscribe` WRITE;
/*!40000 ALTER TABLE `subscribe` DISABLE KEYS */;
INSERT INTO `subscribe` VALUES (3,6,1),(4,21,6),(5,23,6),(6,23,1),(10,25,1),(12,6,6),(15,21,21),(18,25,6);
/*!40000 ALTER TABLE `subscribe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `pwd` varchar(45) NOT NULL,
  `nick` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `nick_UNIQUE` (`nick`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'test@gmail.com','Ssafy1234!@','test'),(6,'dnjswnsthd@naver.com','Ssafy1234!@','song'),(21,'vcxttlvcx@naver.com','#ssafy123','fasdfas'),(23,'sdr9873@naver.com','#Ssafy1234','해쨔니3333'),(24,'cjh951114@naver.com','Ssafy1234!@','JuneHyung'),(25,'cjh1114@naver.com','1234','dfasf'),(26,'jo@gmail.com','123','jo'),(27,'cjh1114@gmail.com','Ssafy1234!@','준혀엉'),(28,'seok@naver.com','#Tjrwns123','tjrwns');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userinterest`
--

DROP TABLE IF EXISTS `userinterest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userinterest` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `post_id` int NOT NULL,
  `interest` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userinteresttouserfk_idx` (`user_id`),
  KEY `userinteresttopostfk_idx` (`user_id`),
  KEY `userinteresttopostfk` (`post_id`),
  CONSTRAINT `userinteresttopostfk` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userinteresttouserfk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userinterest`
--

LOCK TABLES `userinterest` WRITE;
/*!40000 ALTER TABLE `userinterest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'unb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-18 16:42:43

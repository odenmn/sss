-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: csdn
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article` (
  `article_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `user_id` int NOT NULL,
  `is_top` tinyint(1) DEFAULT '0',
  `publish_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `like_count` int NOT NULL,
  PRIMARY KEY (`article_id`),
  KEY `articles_ibfk_1` (`user_id`),
  CONSTRAINT `article_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (3,'Java鐨勪粙缁?,'\nJava 鏄竴绉嶅箍娉涗娇鐢ㄧ殑缂栫▼璇█锛屼互鍏惰法骞冲彴鎬с€佸彲闈犳€у拰寮哄ぇ鐨勫姛鑳借€岄椈鍚嶃€傝嚜1995骞撮娆″彂甯冧互鏉ワ紝Java 宸叉垚涓哄叏鐞冨紑鍙戣€呯殑閲嶈宸ュ叿锛屽箍娉涘簲鐢ㄤ簬浼佷笟绾у簲鐢ㄣ€佺Щ鍔ㄥ簲鐢ㄣ€佸ぇ鏁版嵁鍜屼簯璁＄畻绛夐鍩熴€俓nJava 鐨勬牳蹇冪悊蹇垫槸鈥滀竴娆＄紪鍐欙紝鍒板杩愯鈥濓紙Write Once, Run Anywhere, WORA锛夈€傞€氳繃 Java 铏氭嫙鏈猴紙JVM锛夛紝Java 绋嬪簭鍙互鍦ㄤ换浣曟敮鎸?JVM 鐨勫钩鍙颁笂杩愯锛屾棤闇€閲嶆柊缂栬瘧銆傝繖绉嶈法骞冲彴鐗规€т娇寰?Java 鎴愪负鍒嗗竷寮忕郴缁熷拰浼佷笟绾у簲鐢ㄧ殑鐞嗘兂閫夋嫨銆俓nJava 鏄竴绉嶉潰鍚戝璞＄殑缂栫▼璇█锛屽己璋冧唬鐮佺殑妯″潡鍖栧拰鍙噸鐢ㄦ€с€傚畠鐨勮娉曠畝娲佹槑浜嗭紝鏄撲簬瀛︿範鍜屼娇鐢紝鍚屾椂鎻愪緵浜嗕赴瀵岀殑鏍囧噯搴撳拰妗嗘灦锛屾敮鎸佸紑鍙戣€呭揩閫熸瀯寤哄鏉傜殑搴旂敤绋嬪簭銆俓n',2,0,'2025-04-08 12:51:55',0),(4,'涓€鏉挅鍟＄殑鏃跺厜','\n娓呮櫒鐨勯槼鍏夐€忚繃绐楀笜娲掑湪妗岄潰涓婏紝鎴戠璧蜂竴鏉儹姘旇吘鑵剧殑鍜栧暋锛岃交杞诲惞浜嗗惞锛岀劧鍚庡皬鍙ｅ搧灏濄€傚挅鍟＄殑棣欐皵鍦ㄧ┖姘斾腑寮ユ极锛岃嫤娑╀腑甯︾潃涓€涓濅笣鐢滃懗锛屼豢浣涘湪鎻愰啋鎴戯紝鏂扮殑涓€澶╁紑濮嬩簡銆俓n杩欐槸涓€涓钩鍑＄殑鏃╂櫒锛屽嵈鍥犱负杩欐澂鍜栧暋鑰屾樉寰楁牸澶栫壒鍒€傚挅鍟＄殑娓╁害娓╂殩浜嗘墜蹇冿紝涔熸俯鏆栦簡蹇冩儏銆傛垜鍧愬湪绐楄竟锛岀湅鐫€澶栭潰鐨勮閬撻€愭笎鐑椆璧锋潵锛岃浜哄寙鍖嗭紝杞︽祦涓嶆伅锛岃€屾垜鍗村湪杩欏皬灏忕殑瑙掕惤閲岋紝浜彈鐫€鐗囧埢鐨勫畞闈欍€俓n鍜栧暋鐨勯姘旇鎴戞兂璧蜂簡璁稿浜嬫儏銆傞偅浜涘繖纰岀殑鏃ュ瓙锛岄偅浜涗笌鏈嬪弸鍏卞害鐨勬椂鍏夛紝閭ｄ簺涓轰簡姊︽兂鑰屽姫鍔涚殑鐬棿銆傛瘡涓€鍙ｅ挅鍟￠兘鍍忔槸涓€涓皬灏忕殑浠紡锛屾彁閱掓垜鍋滀笅鏉ワ紝鎰熷彈鐢熸椿鐨勭編濂姐€俓n鏈夋椂鍊欙紝鐢熸椿灏卞儚杩欐澂鍜栧暋锛岃嫤娑╀腑甯︾潃涓€涓濈敎铚溿€傛垜浠渶瑕佸浼氬湪蹇欑涓壘鍒扮墖鍒荤殑瀹侀潤锛屽湪鍠у殻涓壘鍒板唴蹇冪殑骞抽潤銆傚氨鍍忚繖鏉挅鍟★紝瀹冧笉浼氬洜涓轰綘鐨勫寙蹇欒€屽彉寰楁洿棣欙紝涔熶笉浼氬洜涓轰綘鐨勭瓑寰呰€屽け鍘绘俯搴︺€俓n鍠濆畬鏈€鍚庝竴鍙ｅ挅鍟★紝鎴戠珯璧疯韩锛屽噯澶囧紑濮嬫柊鐨勪竴澶┿€傜獥澶栫殑闃冲厜鏇村姞鏄庝寒锛屼豢浣涘湪鍛婅瘔鎴戯紝鏃犺鏄ㄥぉ濡備綍锛屼粖澶╅兘鏄竴涓柊鐨勫紑濮嬨€傝€屾垜锛屽甫鐫€杩欐澂鍜栧暋甯︽潵鐨勬俯鏆栦笌鍔涢噺锛岀户缁墠琛屻€俓n\n\n\n\n',3,0,'2025-04-08 14:24:34',0);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_tag`
--

DROP TABLE IF EXISTS `article_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_tag` (
  `id` int NOT NULL AUTO_INCREMENT,
  `article_id` int NOT NULL,
  `tag_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `article_tag_ibfk_1` (`article_id`),
  KEY `article_tag_ibfk_2` (`tag_id`),
  CONSTRAINT `article_tag_ibfk_1` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `article_tag_ibfk_2` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_tag`
--

LOCK TABLES `article_tag` WRITE;
/*!40000 ALTER TABLE `article_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `article_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `collection`
--

DROP TABLE IF EXISTS `collection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `collection` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `article_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `article_id` (`article_id`),
  CONSTRAINT `collection_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `collection_ibfk_2` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collection`
--

LOCK TABLES `collection` WRITE;
/*!40000 ALTER TABLE `collection` DISABLE KEYS */;
/*!40000 ALTER TABLE `collection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `column`
--

DROP TABLE IF EXISTS `column`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `column` (
  `id` int NOT NULL AUTO_INCREMENT,
  `column_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_id` int NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `column_ibfk_1` (`user_id`),
  CONSTRAINT `column_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `column`
--

LOCK TABLES `column` WRITE;
/*!40000 ALTER TABLE `column` DISABLE KEYS */;
INSERT INTO `column` VALUES (2,'Java',2,'涓€闂ㄩ珮绾ц瑷€');
/*!40000 ALTER TABLE `column` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `column_article`
--

DROP TABLE IF EXISTS `column_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `column_article` (
  `id` int NOT NULL AUTO_INCREMENT,
  `column_id` int NOT NULL,
  `article_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `column_id` (`column_id`),
  KEY `article_id` (`article_id`),
  CONSTRAINT `column_article_ibfk_1` FOREIGN KEY (`column_id`) REFERENCES `column` (`id`),
  CONSTRAINT `column_article_ibfk_2` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `column_article`
--

LOCK TABLES `column_article` WRITE;
/*!40000 ALTER TABLE `column_article` DISABLE KEYS */;
/*!40000 ALTER TABLE `column_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `user_id` int NOT NULL,
  `article_id` int NOT NULL,
  `publish_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `like_count` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `comment_ibfk_1` (`user_id`),
  KEY `comment_ibfk_2` (`article_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `following`
--

DROP TABLE IF EXISTS `following`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `following` (
  `id` int NOT NULL AUTO_INCREMENT,
  `follower_id` int NOT NULL,
  `followed_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `following_ibfk_1` (`follower_id`),
  KEY `following_ibfk_2` (`followed_id`),
  CONSTRAINT `following_ibfk_1` FOREIGN KEY (`follower_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `following_ibfk_2` FOREIGN KEY (`followed_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `following`
--

LOCK TABLES `following` WRITE;
/*!40000 ALTER TABLE `following` DISABLE KEYS */;
INSERT INTO `following` VALUES (7,2,3);
/*!40000 ALTER TABLE `following` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like`
--

DROP TABLE IF EXISTS `like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `like` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `article_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `like_ibfk_1` (`user_id`),
  KEY `like_ibfk_2` (`article_id`),
  CONSTRAINT `like_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `like_ibfk_2` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like`
--

LOCK TABLES `like` WRITE;
/*!40000 ALTER TABLE `like` DISABLE KEYS */;
/*!40000 ALTER TABLE `like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `article_id` int NOT NULL,
  `reason` text,
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'pending',
  `process_time` timestamp NULL DEFAULT NULL,
  `comment_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `article_id` (`article_id`),
  KEY `comment_id` (`comment_id`),
  CONSTRAINT `report_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES (6,3,3,'杩濊浜?,'rejected','2025-04-08 14:14:20',0),(7,3,1,'澶煭浜?,'approved','2025-04-08 14:25:54',0);
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`tag_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (2,'鍚庡彴'),(1,'鏁版嵁搴?);
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(255) NOT NULL,
  `is_banned` tinyint(1) DEFAULT '0',
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','3932a0deabbe1d668c846b446c2eca90','543b3646-9f8f-46a6-9dc3-1d75b783e923',0,'绠＄悊鍛?),(2,'xjx','306ec6fd3e6730837983c7cd71502061','0e779408-1147-48ed-9ad0-24af876ff19d',0,'鏅€氱敤鎴?),(3,'灏忔槑','37ca7d7db9ff1620fe93c103b39b987c','15124b1f-0ba3-497f-be56-733204ecc16c',0,'鏅€氱敤鎴?),(4,'灏忕孩','5c709221312b9c71654a0b6742c18c51','7ab80c1d-9920-4860-9e71-1b0a315703ad',0,'鏅€氱敤鎴?);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-09  0:05:58

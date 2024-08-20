-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: course_manager
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(100) DEFAULT NULL,
  `role` enum('ADMIN','TEACHER','STUDENT') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'$2a$10$b1nO1roIHM4SpMup0bzcZukRJHz2mv7hdnY.QV0NsE9cDMgTZsAM6','ADMIN'),(2,'$2a$10$.zsX.q.BNrPeJC2.O2f0LuEBtA475kMAzVkTB1UaLGDffWW1VCu0i','TEACHER'),(3,'$2a$10$2tRCH0j8u.Cs7G7nB8h0x.j3FxVQu/fQlywMI1w30a5J3/JlJZJka','TEACHER'),(4,'$2a$10$7hdbHBMpd/ZBkAj7NpJuceSDOfqXHMU1Hd2HivDx9YrCVdO9EHqsu','TEACHER'),(5,'$2a$10$.BYkTBpPNB1Ltub47/hkvu77N7ET2U8UKSJ9QqK0/o3z28XEEWmWm','TEACHER'),(6,'$2a$10$fNfj8IEiVzRuGtGFZVzwy.GknQUQqG..kUEbcOi9yCWUROAweJktq','TEACHER'),(7,'$2a$10$VhVMA1Jju6Ico6pB.Ah1Y.CFQGoXuDcNCtf4kEmJUXC.hr2ULayzi','STUDENT'),(8,'$2a$10$GuKR/t5iJNoKPE6.BlSfD.jRAYZ.zcFBdt1OK.Ma1A229/TQIwXhq','STUDENT'),(9,'$2a$10$Q.wl2ztbt9sK06rDV9IQe.7MJeB/wp4llwuvM98IdYsKsMlFZXgsy','STUDENT'),(10,'$2a$10$UcsEQkjXyROEc7L.q1v6VeTPPVKN61FYl6/3zNjXQ7.Dsoe0HmeL2','STUDENT'),(11,'$2a$10$N.YQZIdq.SUC4Ulmzj8k1.HfLKf0Kk9IgZbOjj/SkxS4iVYrEUapK','STUDENT'),(12,'$2a$10$RdEvnC37nt6UvVRkNrobW.laKpPjuK3kotBZAXxt94P2f.ofuVBta','STUDENT'),(13,'$2a$10$dxHwfpUHpZApaXLyw.y3jebabfnvXxrUr8mlDcQNGsaTZxW1tRrHG','STUDENT'),(14,'$2a$10$O3vREBjbfq9wQdsYjYsRdOGaxFrzuMq0jPTRN/BDeU38dUEXLgOY.','STUDENT'),(15,'$2a$10$sAAb/P9li/vFwHUIcNu43.4tVu2h2lvHS9NXqV/jaHe8SaSo6vChC','STUDENT'),(16,'$2a$10$yIvzeTvYdeQAiof7H6R4ouuxPNI8JLVQv000kaIEDcFEV1FGfySqO','STUDENT'),(17,'$2a$10$JsMCEoH5tIDnfm2OnmWcteQqnXst5jCpuOzwxq29qddEnqWsvFy9y','STUDENT'),(18,'$2a$10$eFunlHl8EqqgQ8u6qwxDQeqIrrTG8XFaLUwiFSJ4CYhGnrEko62ES','STUDENT'),(19,'$2a$10$WMO1vhg9MsEmTQ6Vezsq9uSir0POfdJUyZYjgwfVC2fFm3ISpu60i','STUDENT'),(20,'$2a$10$4iXxLlVH.xCysq3OLni2..3YZBksZ0uaHlwW0iwLMiYyKH9huSl.m','STUDENT'),(21,'$2a$10$Qc1sakNYnaUCn8Vx33.pn.DpXwmazGlgssi.znugDw2UNoH2jS/ti','STUDENT'),(22,'$2a$10$PB2VBYD9IdPR5L8GK1xJGeQpfVseuK9QALYdFUZWgKkoHdskPbMv.','STUDENT'),(23,'$2a$10$DDU..FNAa6poiaN/mpNSZO.LVDLE1t189tQZo09D0XGJ3Xo4w3dS.','STUDENT'),(24,'$2a$10$o.tJTzrQ5sNx.i92DXaldOl534K0UwKEKfbeHhafJcCiCyTFWHL1K','STUDENT');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class_student`
--

DROP TABLE IF EXISTS `class_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class_student` (
  `class_id` bigint NOT NULL,
  `student_id` bigint NOT NULL,
  PRIMARY KEY (`class_id`,`student_id`),
  KEY `FK7sbm5g487ge9v25pyh8tvs8lg` (`student_id`),
  CONSTRAINT `FK7sbm5g487ge9v25pyh8tvs8lg` FOREIGN KEY (`student_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK9m1ymqmf3sj0ikimsmbv7s7hj` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_student`
--

LOCK TABLES `class_student` WRITE;
/*!40000 ALTER TABLE `class_student` DISABLE KEYS */;
INSERT INTO `class_student` VALUES (14,7),(14,8),(14,9),(14,10),(14,11),(14,12),(14,13),(14,14),(14,15),(14,16),(14,17),(14,18),(14,19),(14,20),(14,21),(14,22),(14,23),(14,24);
/*!40000 ALTER TABLE `class_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classes`
--

DROP TABLE IF EXISTS `classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classes` (
  `max_student` smallint DEFAULT NULL,
  `course_id` bigint DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `teacher_id` bigint DEFAULT NULL,
  `time_start` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `code` varchar(30) DEFAULT NULL,
  `background` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `status` enum('HOAT_DONG','KHONG_HOAT_DONG') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkhwfqb4rahpl3q2w4jw5k7jlp` (`course_id`),
  KEY `FK9r6phdgb34r4wd6ui07h3qfs5` (`teacher_id`),
  CONSTRAINT `FK9r6phdgb34r4wd6ui07h3qfs5` FOREIGN KEY (`teacher_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKkhwfqb4rahpl3q2w4jw5k7jlp` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classes`
--

LOCK TABLES `classes` WRITE;
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
INSERT INTO `classes` VALUES (20,6,'2024-03-29 08:47:26.747319',1,2,'2024-03-29 00:00:00.000000','2024-03-29 08:47:26.747319','SP241','https://www.gstatic.com/classroom/themes/img_code.jpg','Admin','Admin','HOAT_DONG'),(30,6,'2024-03-29 08:47:47.982145',2,2,'2024-03-29 00:00:00.000000','2024-03-29 08:47:47.982145','SP242','https://www.gstatic.com/classroom/themes/img_code.jpg','Admin','Admin','HOAT_DONG'),(20,5,'2024-03-29 09:08:24.328033',3,3,'2024-03-29 00:00:00.000000','2024-03-29 09:08:24.328033','SP243','https://www.gstatic.com/classroom/themes/img_code.jpg','Admin','Admin','HOAT_DONG'),(20,5,'2024-03-29 09:08:40.049674',4,3,'2024-03-29 00:00:00.000000','2024-03-29 09:08:40.049674','SP244','https://www.gstatic.com/classroom/themes/img_code.jpg','Admin','Admin','HOAT_DONG'),(30,4,'2024-03-29 09:08:54.786696',5,4,'2024-03-29 00:00:00.000000','2024-03-29 09:08:54.786696','SP245','https://www.gstatic.com/classroom/themes/img_code.jpg','Admin','Admin','HOAT_DONG'),(30,5,'2024-03-29 09:09:17.770788',6,5,'2024-03-29 00:00:00.000000','2024-03-29 09:09:17.770788','SP246','https://www.gstatic.com/classroom/themes/img_code.jpg','Admin','Admin','HOAT_DONG'),(30,4,'2024-03-29 09:09:39.745074',7,6,'2024-03-29 00:00:00.000000','2024-03-29 09:09:39.745074','SP247','https://www.gstatic.com/classroom/themes/img_code.jpg','Admin','Admin','HOAT_DONG'),(40,3,'2024-03-29 09:09:56.264692',8,6,'2024-03-29 00:00:00.000000','2024-03-29 09:09:56.264692','SP248','https://www.gstatic.com/classroom/themes/img_code.jpg','Admin','Admin','HOAT_DONG'),(20,3,'2024-03-29 09:10:28.464953',9,3,'2024-03-29 00:00:00.000000','2024-03-29 09:10:28.464953','SP249','https://www.gstatic.com/classroom/themes/img_code.jpg','Admin','Admin','HOAT_DONG'),(30,2,'2024-03-29 09:18:57.093143',10,3,'2024-03-29 00:00:00.000000','2024-03-29 09:18:57.093143','SP2410','https://www.gstatic.com/classroom/themes/img_code.jpg','Admin','Admin','HOAT_DONG'),(30,2,'2024-03-29 09:19:11.611598',11,6,'2024-03-29 00:00:00.000000','2024-03-29 09:19:11.611598','SP2411','https://www.gstatic.com/classroom/themes/img_code.jpg','Admin','Admin','HOAT_DONG'),(20,1,'2024-03-29 09:19:30.081636',12,2,'2024-03-29 00:00:00.000000','2024-03-29 09:19:30.081636','SP2412','https://www.gstatic.com/classroom/themes/img_code.jpg','Admin','Admin','HOAT_DONG'),(30,1,'2024-03-29 09:19:44.065675',13,4,'2024-03-29 00:00:00.000000','2024-03-29 09:19:44.065675','SP2413','https://www.gstatic.com/classroom/themes/img_code.jpg','Admin','Admin','HOAT_DONG'),(20,6,'2024-03-29 09:20:50.032845',14,5,'2024-02-01 00:00:00.000000','2024-03-29 10:38:00.596126','SP2414','https://www.gstatic.com/classroom/themes/img_code.jpg','Admin','Admin','HOAT_DONG'),(30,4,'2024-03-29 09:40:23.261408',15,6,'2024-03-29 00:00:00.000000','2024-03-29 09:40:23.261408','SP2415','https://www.gstatic.com/classroom/themes/img_code.jpg','Admin','Admin','HOAT_DONG');
/*!40000 ALTER TABLE `classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `price` decimal(38,2) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `start_date` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `code` varchar(30) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `time_end` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `status` enum('HOAT_DONG','KHONG_HOAT_DONG') DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1999999.00,'2024-03-28 16:56:39.406452',NULL,1,NULL,'2024-03-28 16:56:39.406452','COURSE1','Admin','Khóa học Lập trình C++ Nâng cao là khóa học cung cấp trọn vẹn kiến thức của ngôn ngữ lập trình C++. Mục tiêu là xây dựng tư duy logic, kỹ năng giải quyết bài toán, nền tảng thuật toán và cấu trúc dữ liệu cho học viên bằng cách sử dụng ngôn ngữ lập trình C++. \n\nKhóa học phù hợp với người mới chưa từng học lập trình, người mất gốc, tư duy logic còn yếu và chưa có nền tảng kỹ thuật lập trình mong muốn cải thiện kỹ năng.\n\nKhóa học C++ nâng cao có lượng kiến thức và bài tập nhiều và khó hơn so với khóa cơ bản tới nâng cao. Nội dung khóa học cũng đi từ cơ bản cho người mới nhưng có mục tiêu cao hơn và hứng thú với việc thực hành nhiều bài tập.','Lập trình C++ Nâng cao',NULL,'Admin','HOAT_DONG','https://cdn-main.28tech.com.vn/media/anh-khoa-hoc/l%E1%BA%ADp%20tr%C3%ACnh%20c%2B%2B%20n%C3%A2ng%20cao_.png'),(2999999.00,'2024-03-28 17:04:42.181371',NULL,2,NULL,'2024-03-28 17:04:42.181371','COURSE2','Admin','Khóa học Java & SQL cung cấp kiến thức về ngôn ngữ lập trình Java, ngôn ngữ truy vấn SQL và các khái niệm về cơ sở dữ liệu. Ngoài ra bạn còn được học kiến thức về JSP, Servlet, một kiến thức quan trọng nếu bạn muốn theo đuổi Java web.\n\nKhóa học gồm 40 buổi học qua zoom với 500-550 bài tập lập trình được chấm tự động bằng hệ thống chấm bài, 70-80 bài tập SQL sẽ cung cấp kỹ năng toàn diện về ngôn ngữ lập trình Java, SQL. Bạn được còn training lại kiến thức về kỹ thuật lập trình và thuật toán trong khóa học. ','Lập trình Java và SQL',NULL,'Admin','HOAT_DONG','https://cdn-main.28tech.com.vn/thumbs/anh-khoa-hoc/l%E1%BA%ADp%20tr%C3%ACnh%20java%20_%20sql_thumb_500.png'),(8999999.00,'2024-03-28 17:08:05.546921',NULL,3,NULL,'2024-03-28 17:08:05.546921','COURSE3','Admin','Khóa học dành cho các bạn học viên có định hướng phát triển website Frontend sử dụng ReactJS, NextJS và các Framework liên quan.\n\nYêu cầu chung: Khóa học sẽ dạy từ cơ bản đến nâng cao. Các bạn chỉ cần: chăm chỉ, không ngại hỏi đáp cũng như đưa ra các thắc mắc trong quá trình học tập.\n\nSau khóa học, các bạn có thể tự tin ứng tuyển vị trí Fresher tại các công ty.','Lập trình Front-end',NULL,'Admin','HOAT_DONG','https://cdn-main.28tech.com.vn/media/anh-khoa-hoc/front-end.png'),(5999999.00,'2024-03-28 17:19:07.093614',NULL,4,NULL,'2024-03-28 17:19:07.093614','COURSE4','Admin','Khóa học lập trình thi đấu dành cho các bạn học sinh, sinh viên IT yêu thích thuật toán và có định hướng học thuật toán để thi đấu các kỳ thi học sinh giỏi, tin học trẻ, Olympic tin học sinh viên.\n\nNội dung của khóa học cũng giúp bạn dễ dàng vượt qua những bài phỏng vấn thuật toán khó khăn nhất tại các doanh nghiệp lớn. \n\nĐể tham gia được khóa học này bạn cần thành thạo ngôn ngữ lập trình C++ & STL, đã nắm vững các cấu trúc dữ liệu, giải thuật và đồ thị. Đặc biệt bạn cần có niềm đam mê với thuật toán và thi đấu lập trình.','Lập Trình Thi Đấu',NULL,'Admin','HOAT_DONG','https://cdn-main.28tech.com.vn/media/anh-khoa-hoc/competitive%20programming.png'),(3999999.00,'2024-03-28 17:19:38.787374',NULL,5,NULL,'2024-03-28 17:19:38.787374','COURSE5','Admin','Khóa học Cấu trúc dữ liệu & Giải thuật (Thuật toán) gồm 3 phần chính : Ôn luyện lại kỹ thuật lập trình với C++ và thư viện STL, Thuật toán, Cấu trúc dữ liệu. \n\nKhóa học gồm 48 buổi học qua zoom với 650-700 bài tập được chấm tự động bằng hệ thống chấm bài sẽ cung cấp kỹ năng toàn diện về Thuật toán & Cấu trúc dữ liệu cho học viên.\n\nKhóa học dành cho các bạn đã có kỹ thuật lập trình và sử dụng tốt ngôn ngữ lập trình C++ cũng như thư viện STL. ','Cấu trúc dữ liệu và Giải thuật',NULL,'Admin','HOAT_DONG','https://cdn-main.28tech.com.vn/media/anh-khoa-hoc/ca%CC%82%CC%81u%20tru%CC%81c%20du%CC%9B%CC%83%20lie%CC%A3%CC%82u%20va%CC%80%20gia%CC%89i%20thua%CC%A3%CC%82tpng.png'),(7999999.00,'2024-03-28 17:20:12.729563',NULL,6,NULL,'2024-03-28 17:20:12.729563','COURSE6','Admin','Khóa học Back-end Java sử dụng Framework Spring mới mục tiêu cung cấp kỹ năng về phát triển website cho học viên, phục vụ công việc học tập tại đại học, intern hoặc fresher. \n\nKhóa học hứa hẹn sẽ là bước đệm cực kỳ quan trọng nếu bạn là sinh viên CNTT muốn theo đuổi công việc lập trình website sử dụng ngôn ngữ Java.','Lập trình Back-end Java',NULL,'Admin','HOAT_DONG','https://cdn-main.28tech.com.vn/media/anh-khoa-hoc/be-java.png');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_lesson`
--

DROP TABLE IF EXISTS `course_lesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_lesson` (
  `ordinal` int DEFAULT NULL,
  `course_id` bigint DEFAULT NULL,
  `lesson_id` bigint DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf3no0dhg3wsy4h2sih25rivr2` (`course_id`),
  KEY `FKog5frd1fln5htfsdmsxc8o3jq` (`lesson_id`),
  CONSTRAINT `FKf3no0dhg3wsy4h2sih25rivr2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `FKog5frd1fln5htfsdmsxc8o3jq` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_lesson`
--

LOCK TABLES `course_lesson` WRITE;
/*!40000 ALTER TABLE `course_lesson` DISABLE KEYS */;
INSERT INTO `course_lesson` VALUES (9,3,28,'041dd51c-7ac2-4cab-9f62-62d919072cd9'),(1,4,11,'05da66c7-969c-46d2-8108-a55984e94e80'),(7,6,12,'14d405ae-e6d0-4009-b630-c382cfada598'),(2,2,2,'1625c7c8-f9fd-48a2-953c-7764b3654856'),(4,5,10,'1b2ba518-c20e-408b-8a0b-c7a807434b7f'),(3,3,22,'1d862e4d-93d8-4b0c-98a6-1e58b7a75b6e'),(5,2,5,'1d93a5d5-6181-4bb9-99bc-b7277118ece1'),(4,1,7,'1f025b77-68f6-4ad5-84c1-e14b9471262f'),(10,6,15,'1f177dae-5913-442d-84aa-2f08fa2a6bf7'),(14,2,17,'1f6cb779-5d3f-4b52-9c9e-ee1abdf059c1'),(7,1,10,'208b5b24-7149-4da6-9043-aeb66b9e8b17'),(7,4,17,'292b163c-192a-4f67-bd62-5898ec931c23'),(3,1,6,'30347ab1-515a-4c3c-b83c-3c248fe44fc2'),(2,3,21,'314dbe34-2bcd-421e-bdc8-0452554ea4b2'),(4,6,9,'3202c2fa-e83f-45d6-9b16-1599b853f3aa'),(11,2,14,'34a2b2f0-3ce2-4f62-ad9a-dc519136ae6f'),(2,1,5,'3ab538a9-fe3b-439a-9d8b-eba61a422723'),(16,2,19,'3d9baf41-40da-4e89-82bb-e25040342ac5'),(3,4,13,'4191d7d8-05f0-45ab-a9fd-0ead4d74c04a'),(3,2,3,'4276f4de-c53b-444e-b5b8-17dc1be93e18'),(13,5,29,'4783d8ff-71da-49ab-83f6-ece73faaa209'),(2,6,7,'484b1fa5-d71b-4345-a230-6b951e9cca24'),(9,1,12,'4d7fd04d-72d8-42f2-b58a-03e458a93ece'),(15,2,18,'5283421b-4c54-429a-85be-7aaf679d0bb2'),(10,5,16,'5314810c-e923-4236-9217-6c8a5ef4d77e'),(9,5,15,'5591898e-de3b-43e4-af06-74e19df27364'),(10,4,20,'5744e990-9d4e-4af4-9159-99fd99647234'),(15,4,25,'57988fd7-9e75-4200-a813-434e947f9628'),(1,3,20,'59ec7408-17a6-436e-86ec-a97f54e6ea9f'),(6,1,9,'5a264cac-448b-4a43-b3f6-4073a7997329'),(14,4,24,'5af7dc16-6229-400c-b535-e2ae5391e0d4'),(5,3,24,'5bad6aff-e656-46fa-9529-eb7d4c57269b'),(4,2,4,'60d67e20-49c8-4707-bda6-3dc86dd54c03'),(13,2,16,'648b3419-2656-4f7c-a27e-316c635d01a1'),(1,5,7,'64b75881-e81d-4f49-8047-4d1e8dedaf1d'),(6,4,16,'65442ecb-f503-45fc-a370-55e47076aac2'),(2,5,8,'677d42c6-076b-4cd9-a3c1-f0cbe6aa718b'),(5,1,8,'67cfdc9b-2c3b-47e0-a2de-81a703f5fd18'),(5,6,10,'70345066-e8a8-4f82-b090-820886580ee6'),(8,5,14,'7333689c-b9c2-436c-921b-691f2b985452'),(8,1,11,'75c2f133-9ab0-4587-9f34-04d1263cf629'),(20,4,30,'7f36b8cc-bbe8-4d06-8882-12740688b0b9'),(4,4,14,'8364b25b-abc4-4149-8686-64db8adeb0b8'),(9,4,19,'8980a0ea-7042-4c22-ba09-817688491b0c'),(8,6,13,'8bcd6a53-0391-44f4-8360-8a057c46f557'),(6,2,7,'8e9975f5-454d-400c-afee-e4e0b2a9d4c2'),(6,5,12,'90ec398c-09a8-4c22-8065-29fabb671285'),(12,5,25,'94b75734-01f1-407a-8663-c6dd12931730'),(7,2,8,'94cdaac9-beb5-4f56-98ab-51ab387f2a86'),(18,4,28,'99ddc527-4da7-432b-b870-1e6bc1a66314'),(12,4,22,'9f0cbc24-c32c-4bb6-99e6-2e3a08add0f7'),(4,3,23,'a0743d07-ceb0-48a6-8a5d-b70a8615c910'),(6,3,25,'a37f7ceb-8c8f-4026-800f-a85e35974d49'),(9,6,14,'a8f24406-0f09-4dc1-bd44-e75975b886a5'),(3,6,8,'ab5f86dc-f16f-4c3d-8bad-a86af492c88b'),(1,2,1,'b0379558-b126-4232-aea0-9b894885795d'),(11,5,17,'b1652590-85c2-4571-ac05-663eb4345c43'),(3,5,9,'b7c72413-c01b-4ed9-a439-b624069924c7'),(7,5,13,'bb0d7e5e-4b3b-4c77-acce-2839d6bcea31'),(8,6,28,'bd532892-f900-4293-9a4f-6049d494cc8f'),(7,6,27,'c0a3a2fa-2d91-488d-833c-a25fea3522d0'),(5,4,15,'c1afcc79-5af8-4b2a-b534-0bac8c7feb26'),(6,6,11,'c93dc9a3-692d-4749-a703-77b8996457ac'),(10,1,13,'ca4507b8-29ba-452b-99e5-aa8d40cb9960'),(12,2,15,'cb210985-c1f5-4d5e-af99-ca9a54154018'),(1,1,4,'d4229015-4631-46ca-80a9-40961704f426'),(5,5,11,'d758ce79-301c-4c8b-afa0-b4d19240ebaa'),(1,6,6,'d9d52e6b-156d-46dc-9f68-73231c175955'),(7,3,26,'e04fa00b-0616-4436-b44b-9557fbe59e8d'),(9,2,12,'e3b101b2-84cc-45ed-b4dd-f9c772ad5c01'),(10,2,13,'e98e9479-d597-4267-af76-fa5b11e6ff85'),(6,6,26,'eded671b-bbd0-479b-a7ac-df511b9374d6'),(8,2,11,'fa7bf962-e976-49f6-bb80-fff0c77a5f34'),(8,3,27,'ffc98be1-d3e0-4198-ac2c-a1de603803d5');
/*!40000 ALTER TABLE `course_lesson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_student`
--

DROP TABLE IF EXISTS `course_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_student` (
  `course_id` bigint NOT NULL,
  `student_id` bigint NOT NULL,
  PRIMARY KEY (`course_id`,`student_id`),
  KEY `FK2sxaxgn2f7wv33s5a91wm0uuk` (`student_id`),
  CONSTRAINT `FK2sxaxgn2f7wv33s5a91wm0uuk` FOREIGN KEY (`student_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKlmj50qx9k98b7li5li74nnylb` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_student`
--

LOCK TABLES `course_student` WRITE;
/*!40000 ALTER TABLE `course_student` DISABLE KEYS */;
/*!40000 ALTER TABLE `course_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lesson`
--

DROP TABLE IF EXISTS `lesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lesson` (
  `quantity` int DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `updated_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `status` enum('HOAT_DONG','KHONG_HOAT_DONG') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson`
--

LOCK TABLES `lesson` WRITE;
/*!40000 ALTER TABLE `lesson` DISABLE KEYS */;
INSERT INTO `lesson` VALUES (4,'2024-03-28 16:52:36.475982',1,'2024-03-28 16:52:36.475982','Admin','- Hướng dẫn sử dụng IDE\n- Kiểu dữ liệu và Biến trong C++\n- Toán tử trong C++\n- Các hàm toán học phổ biến','Nhập xuất, Kiểu Dữ Liệu, Toán tử','Admin','HOAT_DONG'),(2,'2024-03-28 16:53:02.434892',2,'2024-03-28 16:53:02.434892','Admin','- Cấu trúc rẽ nhánh : If else, switch - case\n- Bảng mã ASCII','Cấu trúc rẽ nhánh if else và switch-case','Admin','HOAT_DONG'),(6,'2024-03-28 16:53:15.770691',3,'2024-03-28 16:53:15.770691','Admin','- Vòng lặp For\n- Các vòng lặp for thường gặp\n- Câu lệnh break & continue\n- Vòng lặp While, Do-While\n- Dạng bài tập tách chữ số bằng vòng lặp while\n- Cách tạo vòng lặp vĩnh viễn','Vòng lặp for, while, do-while','Admin','HOAT_DONG'),(7,'2024-03-28 16:53:31.338833',4,'2024-03-28 16:53:31.338833','Admin','- Hàm và lời gọi hàm trong C++\n- Tham số và đối số\n- Kiểu trả về của hàm và câu lệnh return\n- Hàm void là gì ?\n- Lý thuyết số : Số nguyên tố, Số chính phương,Số thuận nghịch, Phân tích thừa số nguyên tố, Số hoàn hảo, Tính tổng ước, Đếm ước của số nguyên, Lý thuyết đồng dư, Ước chung lớn nhất, bội chung nhỏ nhất…','Hàm trong C++','Admin','HOAT_DONG'),(3,'2024-03-28 16:53:54.459788',5,'2024-03-28 16:53:54.459788','Admin','Lý thuyết số và tổ hợp nâng cao :\n- Số Fibonacci, Ước chung lớn nhất, Bội chung nhỏ nhất, Tổ hợp, Lý thuyết đồng dư,\n- Nghịch đảo Modulo, Định lý nhỏ Fermat, Định lý Euler, Công thức Legendre,\n- Lũy thừa nhị phân, Chỉnh hợp lặp…','Lý thuyết số, tổ hợp nâng cao','Admin','HOAT_DONG'),(7,'2024-03-28 16:54:18.066378',6,'2024-03-28 16:54:18.066378','Admin','- Mảng 1 Chiều\n- Cách duyệt mảng bằng chỉ số\n- Vòng lặp Range-based for loop\n- 15 dạng bài tập phổ biến trên mảng 1 chiều','Mảng 1 Chiều & Range-based for loop (C++11)','Admin','HOAT_DONG'),(7,'2024-03-28 16:54:30.875946',7,'2024-03-28 16:54:30.875946','Admin','- Vector và Iterator\n- Kiểu dữ liệu Pair và Auto trong C++\n- Bộ nhớ ngăn xếp\n- Stack frame\n- Kỹ thuật đệ quy và các bài toán đệ quy thường gặp','Vector, Iterator, Pair, Auto & Kỹ thuật đệ quy','Admin','HOAT_DONG'),(5,'2024-03-28 16:54:57.746735',8,'2024-03-28 16:54:57.746735','Admin','- Set, Multiset, Unordered_set\n- Map, Multimap, Unordered_map\n- Các dạng bài tập với set & map','Set & Map','Admin','HOAT_DONG'),(5,'2024-03-28 16:55:17.865961',9,'2024-03-28 16:55:17.865961','Admin','- Mảng 2 chiều & các dạng bài toán\n- Ma trận và các phép toán trên ma trận\n- Kỹ thuật loang các ô liên kề trên mảng 2 chiều','Mảng 2 chiều & Ma trận','Admin','HOAT_DONG'),(5,'2024-03-28 16:55:30.410736',10,'2024-03-28 16:55:30.410736','Admin','- String trong C++\n- Duyệt String\n- Mảng, vector, set, map với kiểu string\n- Bài toán liên quan tới tần suất của từ và tần suất ký tự','Chuỗi ký tự (String) trong C++','Admin','HOAT_DONG'),(8,'2024-03-28 16:55:47.729670',11,'2024-03-28 16:55:47.729670','Admin','- Lập trình hướng đối tượng\n- Các khái niệm cơ bản trong lập trình hướng đối tượng\n- Nạp chồng toán tử, hàm bạn, lớp bạn, static keyword\n- Thiết kế lớp và các bài toán áp dụng','Lập trình hướng đối tượng','Admin','HOAT_DONG'),(7,'2024-03-28 16:55:59.170713',12,'2024-03-28 16:55:59.170713','Admin','- Kế thừa\n- Đa hình\n- Hàm ảo','Kế thừa & Đa hình','Admin','HOAT_DONG'),(6,'2024-03-28 16:56:12.234181',13,'2024-03-28 16:56:12.234181','Admin','- Xử lý File\n- Toán tử bit\n- Giao bài tập và hướng dẫn bài tập xử lý file','Đọc ghi file trong C++ & Toán tử bit','Admin','HOAT_DONG'),(7,'2024-03-28 17:05:13.233655',14,'2024-03-28 17:05:13.233655','Admin','- Các khái niệm về cơ sở dữ liệu\n- Kiểu dữ liệu trong SQL\n- Các câu lệnh truy vấn SQL cơ bản : SELECT, WHERE, LIKE, ...\n- Giao contest 18 : SQL (70 bài tập)','Truy vấn SQL cơ bản','Admin','HOAT_DONG'),(5,'2024-03-28 17:05:25.921032',15,'2024-03-28 17:05:25.921032','Admin','- Các câu lệnh truy vấn SQL nâng cao\n- JOIN, HAVING, GROUP BY....','Truy vấn SQL nâng cao','Admin','HOAT_DONG'),(5,'2024-03-28 17:05:39.905397',16,'2024-03-28 17:05:39.905397','Admin','- Kết nối ứng dụng Java với CSDL dùng JDBC\n- Statement, PreparedStatement, ResultSet\n- AutoCommit, Rollback','JDBC','Admin','HOAT_DONG'),(5,'2024-03-28 17:05:53.239927',17,'2024-03-28 17:05:53.239927','Admin','- Route\n- method GET/POST\n- forward/redirect\n- jsp chèn thêm code java\n- Tính năng: Login','JSP, Servlet','Admin','HOAT_DONG'),(5,'2024-03-28 17:06:11.928918',18,'2024-03-28 17:06:11.928918','Admin','- JButton, JTextField, JLabel, ...\n- Xử lý sự kiện\n- Giao contest 17 : GUI (30 bài tập)','Các thành phần cơ bản của GUI','Admin','HOAT_DONG'),(5,'2024-03-28 17:06:34.118646',19,'2024-03-28 17:06:34.118646','Admin','- Xử lý ngoại lệ trong Java\n- Đọc ghi file\n- Giao contest 16 : Đọc ghi file (15 bài tập)','Đọc ghi file trong Java, Xử lý ngoại lệ','Admin','HOAT_DONG'),(3,'2024-03-28 17:15:11.056216',20,'2024-03-28 17:15:11.056216','Admin','HTML 5','HTML5','Admin','HOAT_DONG'),(2,'2024-03-28 17:15:20.585797',21,'2024-03-28 17:15:20.585797','Admin','CSS3','CSS3','Admin','HOAT_DONG'),(3,'2024-03-28 17:16:02.680990',22,'2024-03-28 17:16:02.680990','Admin','Học NPM, PUG','Học NPM, PUG','Admin','HOAT_DONG'),(3,'2024-03-28 17:16:18.314580',23,'2024-03-28 17:16:18.314580','Admin','GIT','GIT','Admin','HOAT_DONG'),(3,'2024-03-28 17:16:28.594286',24,'2024-03-28 17:16:28.594286','Admin','Học Tailwind CSS','Học Tailwind CSS','Admin','HOAT_DONG'),(3,'2024-03-28 17:16:39.176654',25,'2024-03-28 17:16:39.176654','Admin','Học Firebase, Rest API','Học Firebase, Rest API','Admin','HOAT_DONG'),(3,'2024-03-28 17:16:51.642878',26,'2024-03-28 17:16:51.642878','Admin','Học ReactJS','Học ReactJS','Admin','HOAT_DONG'),(4,'2024-03-28 17:17:00.409711',27,'2024-03-28 17:17:00.409711','Admin','Học Redux','Học Redux','Admin','HOAT_DONG'),(4,'2024-03-28 17:17:32.056737',28,'2024-03-28 17:17:32.056737','Admin','JavaScript','JavaScript','Admin','HOAT_DONG'),(5,'2024-03-28 17:20:35.009196',29,'2024-03-28 17:20:35.009196','Admin','- Các mối quan hệ 1:1-1:n- n:n và\n- Lưu đồ ERD\n- Cách thiết kế lược đồ thực thể','Thiết kế cơ sở dữ liệu','Admin','HOAT_DONG'),(4,'2024-03-28 17:20:47.200211',30,'2024-03-28 17:20:47.200211','Admin','JPA','JPA','Admin','HOAT_DONG');
/*!40000 ALTER TABLE `lesson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lesson_class`
--

DROP TABLE IF EXISTS `lesson_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lesson_class` (
  `status` int DEFAULT NULL,
  `classes_id` bigint DEFAULT NULL,
  `lesson_id` bigint DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKihbpjmg0ijbehwvi3hvr0g5gy` (`classes_id`),
  KEY `FKt2s5n9e60c1oefe516yxro327` (`lesson_id`),
  CONSTRAINT `FKihbpjmg0ijbehwvi3hvr0g5gy` FOREIGN KEY (`classes_id`) REFERENCES `classes` (`id`),
  CONSTRAINT `FKt2s5n9e60c1oefe516yxro327` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson_class`
--

LOCK TABLES `lesson_class` WRITE;
/*!40000 ALTER TABLE `lesson_class` DISABLE KEYS */;
INSERT INTO `lesson_class` VALUES (3,14,6,'113ed225-de2b-4b73-994c-3857da2bb0fa'),(1,14,9,'3fa86f92-9a96-496f-acd3-8ed6a41b2c26'),(2,14,8,'41e57ebe-20d1-43ec-adb9-c73dd4aed5f7'),(3,14,7,'4e62367c-2dc2-408b-95e1-7793deddee39');
/*!40000 ALTER TABLE `lesson_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mail_sender`
--

DROP TABLE IF EXISTS `mail_sender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mail_sender` (
  `content` text,
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `code` enum('DANG_KY_KHOA_HOC','TAO_TAI_KHOAN','THAM_GIA_LOP') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_g9iyfnvtdhi8vg76wosukt02` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mail_sender`
--

LOCK TABLES `mail_sender` WRITE;
/*!40000 ALTER TABLE `mail_sender` DISABLE KEYS */;
INSERT INTO `mail_sender` VALUES ('<p>Chào <strong>[NAME]</strong>, </p><p>Chúc mừng! Chúng tôi rất vui thông báo rằng đăng ký của bạn cho khóa học <strong>[COURSE]</strong> đã được nhận và đang được xử lý.</p><p>Hồ sơ đăng ký của bạn đang được xem xét và duyệt bởi đội ngũ của chúng tôi. Xin vui lòng chờ trong thời gian ngắn để chúng tôi kiểm tra và xác nhận hồ sơ của bạn.</p><p>Trong trường hợp có bất kỳ thông tin bổ sung nào cần thiết từ phía bạn, chúng tôi sẽ liên hệ trực tiếp qua email hoặc điện thoại.</p><p>Cảm ơn bạn đã tham gia khóa học cùng chúng tôi. Chúng tôi rất mong được chào đón bạn vào lớp học!</p><p>Trân trọng,</p>','1','Đăng ký khóa học','Đăng ký khóa học','DANG_KY_KHOA_HOC'),('<p>Chào <strong>[NAME]</strong>, </p><p>Xin chúc mừng bạn đã tham gia lớp <span style=\"font-weight: bolder; background-color: var(--bs-card-bg); color: var(--bs-body-color);\">[CLASS] </span>thành công của khóa học <span style=\"font-weight: bolder; background-color: var(--bs-card-bg); color: var(--bs-body-color);\">[COURSE]</span></p><p><span style=\"background-color: var(--bs-card-bg); color: var(--bs-body-color);\">Bạn vui lòng chuẩn bị học phí và hồ sơ hoàn thiện trước ngày khai giảng,</span></p><p><span style=\"background-color: var(--bs-card-bg); color: var(--bs-body-color);\">Ngày khai giảng: </span><strong style=\"background-color: var(--bs-card-bg); color: var(--bs-body-color);\">[TIME]</strong></p><p>Bạn có thể truy cập đường dẫn <a href=\"http://localhost:5173/class\" rel=\"noopener noreferrer\" target=\"_blank\">http://localhost:5173/class</a> để xem thông tin</p><p>Tài khoản: <strong>[USERNAME]</strong></p><p>Mật khẩu:<strong> [PASSWORD]</strong></p><p><em style=\"color: rgb(102, 163, 224);\">Lưu ý đổi mật khẩu ngay khi nhận được mail</em></p><p>Trân trọng,</p>','2','Tham gia lớp thành công','Đã được thêm vào lớp','THAM_GIA_LOP');
/*!40000 ALTER TABLE `mail_sender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `point`
--

DROP TABLE IF EXISTS `point`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `point` (
  `points` int DEFAULT NULL,
  `classes_id` bigint DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `lesson_id` bigint DEFAULT NULL,
  `student_id` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `status` enum('HOAT_DONG','KHONG_HOAT_DONG') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKitsrs9wfpf84s09nqyj181u7h` (`classes_id`),
  KEY `FKq6ua1t85ncs9ny2nf6cmqf52v` (`lesson_id`),
  KEY `FKk6xjn8p7x7h4srm7yvpfadl64` (`student_id`),
  CONSTRAINT `FKitsrs9wfpf84s09nqyj181u7h` FOREIGN KEY (`classes_id`) REFERENCES `classes` (`id`),
  CONSTRAINT `FKk6xjn8p7x7h4srm7yvpfadl64` FOREIGN KEY (`student_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKq6ua1t85ncs9ny2nf6cmqf52v` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `point`
--

LOCK TABLES `point` WRITE;
/*!40000 ALTER TABLE `point` DISABLE KEYS */;
INSERT INTO `point` VALUES (5,14,'2024-03-29 09:54:30.589722',1,6,21,'2024-03-29 09:54:30.589722','Admin','Admin','HOAT_DONG'),(8,14,'2024-03-29 09:54:33.925120',2,6,7,'2024-03-29 09:54:33.925120','Admin','Admin','HOAT_DONG'),(7,14,'2024-03-29 09:54:36.789736',3,6,8,'2024-03-29 09:54:36.789736','Admin','Admin','HOAT_DONG'),(6,14,'2024-03-29 09:54:38.790476',4,6,9,'2024-03-29 09:54:38.790476','Admin','Admin','HOAT_DONG'),(8,14,'2024-03-29 09:54:40.642754',5,6,10,'2024-03-29 09:54:40.642754','Admin','Admin','HOAT_DONG'),(9,14,'2024-03-29 09:54:42.492146',6,6,11,'2024-03-29 09:54:42.492146','Admin','Admin','HOAT_DONG'),(4,14,'2024-03-29 09:54:44.596293',7,6,12,'2024-03-29 09:54:44.596293','Admin','Admin','HOAT_DONG'),(7,14,'2024-03-29 09:54:48.207473',8,6,14,'2024-03-29 09:54:48.207473','Admin','Admin','HOAT_DONG'),(7,14,'2024-03-29 09:54:49.953838',9,6,13,'2024-03-29 09:54:49.953838','Admin','Admin','HOAT_DONG'),(8,14,'2024-03-29 09:54:52.107284',10,6,15,'2024-03-29 09:54:52.107284','Admin','Admin','HOAT_DONG'),(9,14,'2024-03-29 09:54:53.789879',11,6,16,'2024-03-29 09:54:53.789879','Admin','Admin','HOAT_DONG'),(4,14,'2024-03-29 09:54:55.398882',12,6,17,'2024-03-29 09:54:55.398882','Admin','Admin','HOAT_DONG'),(6,14,'2024-03-29 09:54:57.358211',13,6,18,'2024-03-29 09:54:57.358211','Admin','Admin','HOAT_DONG'),(7,14,'2024-03-29 09:54:59.044051',14,6,19,'2024-03-29 09:54:59.044051','Admin','Admin','HOAT_DONG'),(8,14,'2024-03-29 09:55:01.276929',15,6,20,'2024-03-29 09:55:01.276929','Admin','Admin','HOAT_DONG'),(7,14,'2024-03-29 09:55:51.328995',16,7,7,'2024-03-29 09:55:51.328995','Admin','Admin','HOAT_DONG'),(8,14,'2024-03-29 09:55:53.437290',17,8,7,'2024-03-29 09:55:53.437290','Admin','Admin','HOAT_DONG'),(5,14,'2024-03-29 09:55:55.058704',18,7,8,'2024-03-29 09:55:55.058704','Admin','Admin','HOAT_DONG'),(6,14,'2024-03-29 09:55:56.525805',19,8,8,'2024-03-29 09:55:56.525805','Admin','Admin','HOAT_DONG'),(7,14,'2024-03-29 09:55:58.127164',20,7,9,'2024-03-29 09:55:58.127164','Admin','Admin','HOAT_DONG'),(8,14,'2024-03-29 09:55:59.927518',21,8,9,'2024-03-29 09:55:59.927518','Admin','Admin','HOAT_DONG'),(4,14,'2024-03-29 09:56:01.493015',22,7,10,'2024-03-29 09:56:01.493015','Admin','Admin','HOAT_DONG'),(6,14,'2024-03-29 09:56:03.077184',23,8,10,'2024-03-29 09:56:03.077184','Admin','Admin','HOAT_DONG'),(8,14,'2024-03-29 09:56:05.140643',24,7,11,'2024-03-29 09:56:05.140643','Admin','Admin','HOAT_DONG'),(4,14,'2024-03-29 09:56:06.592238',25,8,11,'2024-03-29 09:56:06.592238','Admin','Admin','HOAT_DONG'),(8,14,'2024-03-29 09:56:08.317493',26,8,12,'2024-03-29 09:56:08.317493','Admin','Admin','HOAT_DONG'),(7,14,'2024-03-29 09:56:10.020947',27,7,12,'2024-03-29 09:56:10.020947','Admin','Admin','HOAT_DONG'),(5,14,'2024-03-29 09:56:12.005965',28,7,13,'2024-03-29 09:56:12.005965','Admin','Admin','HOAT_DONG'),(8,14,'2024-03-29 09:56:13.744502',29,8,13,'2024-03-29 09:56:13.744502','Admin','Admin','HOAT_DONG'),(9,14,'2024-03-29 09:56:15.357478',30,8,14,'2024-03-29 09:56:15.357478','Admin','Admin','HOAT_DONG'),(7,14,'2024-03-29 09:56:16.984967',31,7,14,'2024-03-29 09:56:16.984967','Admin','Admin','HOAT_DONG'),(5,14,'2024-03-29 09:56:19.034015',32,7,15,'2024-03-29 09:56:19.034015','Admin','Admin','HOAT_DONG'),(8,14,'2024-03-29 09:56:20.607626',33,8,15,'2024-03-29 09:56:20.607626','Admin','Admin','HOAT_DONG'),(5,14,'2024-03-29 09:56:22.206556',34,8,16,'2024-03-29 09:56:22.206556','Admin','Admin','HOAT_DONG'),(7,14,'2024-03-29 09:56:23.776055',35,7,16,'2024-03-29 09:56:23.776055','Admin','Admin','HOAT_DONG'),(8,14,'2024-03-29 09:56:25.386558',36,7,17,'2024-03-29 09:56:25.386558','Admin','Admin','HOAT_DONG'),(5,14,'2024-03-29 09:56:26.908717',37,8,17,'2024-03-29 09:56:26.908717','Admin','Admin','HOAT_DONG'),(7,14,'2024-03-29 09:56:28.696843',38,8,18,'2024-03-29 09:56:28.696843','Admin','Admin','HOAT_DONG'),(5,14,'2024-03-29 09:56:30.194041',39,7,18,'2024-03-29 09:56:30.194041','Admin','Admin','HOAT_DONG'),(7,14,'2024-03-29 09:56:32.343622',40,7,19,'2024-03-29 09:56:32.343622','Admin','Admin','HOAT_DONG'),(8,14,'2024-03-29 09:56:33.751597',41,8,19,'2024-03-29 09:56:33.751597','Admin','Admin','HOAT_DONG'),(8,14,'2024-03-29 09:56:35.522331',42,7,20,'2024-03-29 09:56:35.522331','Admin','Admin','HOAT_DONG'),(5,14,'2024-03-29 09:56:36.961305',43,8,20,'2024-03-29 09:56:36.961305','Admin','Admin','HOAT_DONG'),(5,14,'2024-03-29 09:56:38.882806',44,7,21,'2024-03-29 09:56:38.882806','Admin','Admin','HOAT_DONG'),(3,14,'2024-03-29 09:56:40.326717',45,8,21,'2024-03-29 09:56:40.326717','Admin','Admin','HOAT_DONG'),(6,14,'2024-03-29 11:27:08.315584',46,8,24,'2024-03-29 11:27:08.315584','Admin','Admin','HOAT_DONG'),(7,14,'2024-03-29 11:27:09.784229',47,7,24,'2024-03-29 11:27:09.784229','Admin','Admin','HOAT_DONG'),(5,14,'2024-03-29 11:27:11.312468',48,7,23,'2024-03-29 11:27:11.312468','Admin','Admin','HOAT_DONG'),(6,14,'2024-03-29 11:27:12.869523',49,8,23,'2024-03-29 11:27:12.869523','Admin','Admin','HOAT_DONG'),(7,14,'2024-03-29 11:27:14.376348',50,8,22,'2024-03-29 11:27:14.376348','Admin','Admin','HOAT_DONG'),(8,14,'2024-03-29 11:27:16.058641',51,7,22,'2024-03-29 11:27:16.058641','Admin','Admin','HOAT_DONG'),(5,14,'2024-03-29 11:27:17.563064',52,6,22,'2024-03-29 11:27:17.563064','Admin','Admin','HOAT_DONG'),(4,14,'2024-03-29 11:27:19.138792',53,6,23,'2024-03-29 11:27:19.138792','Admin','Admin','HOAT_DONG'),(6,14,'2024-03-29 11:27:20.945056',54,6,24,'2024-03-29 11:27:20.945056','Admin','Admin','HOAT_DONG');
/*!40000 ALTER TABLE `point` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule` (
  `study` int DEFAULT NULL,
  `classes_id` bigint DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `time_study` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `day_of_week` enum('MONDAY','TUESDAY','WEDNESDAY','THURSDAY','FRIDAY','SATURDAY','SUNDAY') DEFAULT NULL,
  `status` enum('HOAT_DONG','KHONG_HOAT_DONG') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbtuoa3o7c765deag9016rah0b` (`classes_id`),
  CONSTRAINT `FKbtuoa3o7c765deag9016rah0b` FOREIGN KEY (`classes_id`) REFERENCES `classes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (2,1,'2024-03-29 08:47:26.785552',1,'2024-03-29 09:20:00.000000','2024-03-29 08:47:26.785552','Admin','Admin','FRIDAY','HOAT_DONG'),(2,1,'2024-03-29 08:47:26.787564',2,'2024-04-01 09:20:00.000000','2024-03-29 08:47:26.787564','Admin','Admin','MONDAY','HOAT_DONG'),(2,1,'2024-03-29 08:47:26.789549',3,'2024-04-03 09:20:00.000000','2024-03-29 08:47:26.789549','Admin','Admin','WEDNESDAY','HOAT_DONG'),(2,2,'2024-03-29 08:47:47.989145',4,'2024-04-04 09:20:00.000000','2024-03-29 08:47:47.989145','Admin','Admin','THURSDAY','HOAT_DONG'),(2,2,'2024-03-29 08:47:47.990146',5,'2024-04-02 09:20:00.000000','2024-03-29 08:47:47.990146','Admin','Admin','TUESDAY','HOAT_DONG'),(2,2,'2024-03-29 08:47:47.992145',6,'2024-03-30 09:20:00.000000','2024-03-29 08:47:47.992145','Admin','Admin','SATURDAY','HOAT_DONG'),(1,3,'2024-03-29 09:08:24.361331',7,'2024-04-03 07:15:00.000000','2024-03-29 09:08:24.361331','Admin','Admin','WEDNESDAY','HOAT_DONG'),(1,3,'2024-03-29 09:08:24.363331',8,'2024-04-01 07:15:00.000000','2024-03-29 09:08:24.363331','Admin','Admin','MONDAY','HOAT_DONG'),(1,3,'2024-03-29 09:08:24.364322',9,'2024-03-29 07:15:00.000000','2024-03-29 09:08:24.364322','Admin','Admin','FRIDAY','HOAT_DONG'),(3,5,'2024-03-29 09:08:54.793705',12,'2024-04-03 12:00:00.000000','2024-03-29 09:08:54.793705','Admin','Admin','WEDNESDAY','HOAT_DONG'),(3,5,'2024-03-29 09:08:54.794705',13,'2024-04-01 12:00:00.000000','2024-03-29 09:08:54.794705','Admin','Admin','MONDAY','HOAT_DONG'),(3,5,'2024-03-29 09:08:54.795704',14,'2024-03-29 12:00:00.000000','2024-03-29 09:08:54.795704','Admin','Admin','FRIDAY','HOAT_DONG'),(4,6,'2024-03-29 09:09:17.775800',15,'2024-04-02 14:10:00.000000','2024-03-29 09:09:17.775800','Admin','Admin','TUESDAY','HOAT_DONG'),(4,6,'2024-03-29 09:09:17.777789',16,'2024-04-04 14:10:00.000000','2024-03-29 09:09:17.777789','Admin','Admin','THURSDAY','HOAT_DONG'),(4,6,'2024-03-29 09:09:17.778804',17,'2024-03-30 14:10:00.000000','2024-03-29 09:09:17.778804','Admin','Admin','SATURDAY','HOAT_DONG'),(3,7,'2024-03-29 09:09:39.752110',18,'2024-04-04 12:00:00.000000','2024-03-29 09:09:39.752110','Admin','Admin','THURSDAY','HOAT_DONG'),(3,7,'2024-03-29 09:09:39.753163',19,'2024-04-02 12:00:00.000000','2024-03-29 09:09:39.753163','Admin','Admin','TUESDAY','HOAT_DONG'),(3,7,'2024-03-29 09:09:39.754164',20,'2024-03-30 12:00:00.000000','2024-03-29 09:09:39.754164','Admin','Admin','SATURDAY','HOAT_DONG'),(2,8,'2024-03-29 09:09:56.272741',21,'2024-04-03 09:20:00.000000','2024-03-29 09:09:56.272741','Admin','Admin','WEDNESDAY','HOAT_DONG'),(2,8,'2024-03-29 09:09:56.273743',22,'2024-04-01 09:20:00.000000','2024-03-29 09:09:56.273743','Admin','Admin','MONDAY','HOAT_DONG'),(2,8,'2024-03-29 09:09:56.274744',23,'2024-03-29 09:20:00.000000','2024-03-29 09:09:56.274744','Admin','Admin','FRIDAY','HOAT_DONG'),(3,9,'2024-03-29 09:10:28.470951',24,'2024-04-03 12:00:00.000000','2024-03-29 09:10:28.470951','Admin','Admin','WEDNESDAY','HOAT_DONG'),(3,9,'2024-03-29 09:10:28.471951',25,'2024-04-01 12:00:00.000000','2024-03-29 09:10:28.471951','Admin','Admin','MONDAY','HOAT_DONG'),(3,9,'2024-03-29 09:10:28.472951',26,'2024-03-29 12:00:00.000000','2024-03-29 09:10:28.472951','Admin','Admin','FRIDAY','HOAT_DONG'),(5,10,'2024-03-29 09:18:57.132189',27,'2024-04-01 16:20:00.000000','2024-03-29 09:18:57.132189','Admin','Admin','MONDAY','HOAT_DONG'),(5,10,'2024-03-29 09:18:57.134169',28,'2024-04-03 16:20:00.000000','2024-03-29 09:18:57.134169','Admin','Admin','WEDNESDAY','HOAT_DONG'),(5,10,'2024-03-29 09:18:57.135169',29,'2024-03-29 16:20:00.000000','2024-03-29 09:18:57.135169','Admin','Admin','FRIDAY','HOAT_DONG'),(5,11,'2024-03-29 09:19:11.618634',30,'2024-04-04 16:20:00.000000','2024-03-29 09:19:11.618634','Admin','Admin','THURSDAY','HOAT_DONG'),(5,11,'2024-03-29 09:19:11.619633',31,'2024-04-02 16:20:00.000000','2024-03-29 09:19:11.619633','Admin','Admin','TUESDAY','HOAT_DONG'),(5,11,'2024-03-29 09:19:11.621633',32,'2024-03-30 16:20:00.000000','2024-03-29 09:19:11.621633','Admin','Admin','SATURDAY','HOAT_DONG'),(4,12,'2024-03-29 09:19:30.087623',33,'2024-04-03 14:10:00.000000','2024-03-29 09:19:30.087623','Admin','Admin','WEDNESDAY','HOAT_DONG'),(4,12,'2024-03-29 09:19:30.089622',34,'2024-03-29 14:10:00.000000','2024-03-29 09:19:30.089622','Admin','Admin','FRIDAY','HOAT_DONG'),(4,12,'2024-03-29 09:19:30.090621',35,'2024-04-01 14:10:00.000000','2024-03-29 09:19:30.090621','Admin','Admin','MONDAY','HOAT_DONG'),(5,13,'2024-03-29 09:19:44.071623',36,'2024-03-30 16:20:00.000000','2024-03-29 09:19:44.071623','Admin','Admin','SATURDAY','HOAT_DONG'),(5,13,'2024-03-29 09:19:44.073638',37,'2024-04-04 16:20:00.000000','2024-03-29 09:19:44.073638','Admin','Admin','THURSDAY','HOAT_DONG'),(5,13,'2024-03-29 09:19:44.074623',38,'2024-04-02 16:20:00.000000','2024-03-29 09:19:44.074623','Admin','Admin','TUESDAY','HOAT_DONG'),(6,4,'2024-03-29 09:20:22.293408',39,'2024-04-02 18:30:00.000000','2024-03-29 09:20:22.293408','Admin','Admin','TUESDAY','HOAT_DONG'),(6,4,'2024-03-29 09:20:22.295408',40,'2024-04-04 18:30:00.000000','2024-03-29 09:20:22.295408','Admin','Admin','THURSDAY','HOAT_DONG'),(6,4,'2024-03-29 09:20:22.296416',41,'2024-03-30 18:30:00.000000','2024-03-29 09:20:22.296416','Admin','Admin','SATURDAY','HOAT_DONG'),(4,15,'2024-03-29 09:40:23.294555',45,'2024-03-29 14:10:00.000000','2024-03-29 09:40:23.294555','Admin','Admin','FRIDAY','HOAT_DONG'),(4,15,'2024-03-29 09:40:23.294555',46,'2024-04-03 14:10:00.000000','2024-03-29 09:40:23.294555','Admin','Admin','WEDNESDAY','HOAT_DONG'),(4,15,'2024-03-29 09:40:23.294555',47,'2024-04-01 14:10:00.000000','2024-03-29 09:40:23.294555','Admin','Admin','MONDAY','HOAT_DONG'),(3,14,'2024-03-29 09:55:32.658538',51,'2024-04-01 12:00:00.000000','2024-03-29 09:55:32.658538','Admin','Admin','MONDAY','HOAT_DONG'),(3,14,'2024-03-29 09:55:32.658538',52,'2024-04-03 12:00:00.000000','2024-03-29 09:55:32.658538','Admin','Admin','WEDNESDAY','HOAT_DONG'),(3,14,'2024-03-29 09:55:32.658538',53,'2024-03-29 12:00:00.000000','2024-03-29 09:55:32.658538','Admin','Admin','FRIDAY','HOAT_DONG');
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `account_id` bigint DEFAULT NULL,
  `birth_date` datetime(6) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `updated_at` datetime(6) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `code` varchar(30) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `gender` enum('MALE','FEMALE') DEFAULT NULL,
  `status` enum('HOAT_DONG','KHONG_HOAT_DONG') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nrrhhb0bsexvi8ch6wnon9uog` (`account_id`),
  CONSTRAINT `FKc3b4xfbq6rbkkrddsdum8t5f0` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'2003-11-11 00:00:00.000000','2024-03-28 16:50:57.360501',1,'2024-03-28 16:50:57.360501','0987657413','TuongTV','gaupanda567@gmail.com','Hà Nội','Admin','Triệu Văn Tưởng','Admin','MALE','HOAT_DONG'),(2,'1999-03-30 00:00:00.000000','2024-03-28 17:43:05.889858',2,'2024-03-28 17:43:05.889858','0986376526','HuongNT','Huong@gmail.com','Cầu giấy, Hà Nội','Admin','Nguyễn Thị Hưởng','Admin','FEMALE','HOAT_DONG'),(3,'1998-03-14 00:00:00.000000','2024-03-28 17:44:26.403815',3,'2024-03-28 17:44:26.403815','0986765374','namTV','nam@gmail.com','Mỹ Đình, Hà Nội','Admin','Triệu Văn nam','Admin','MALE','HOAT_DONG'),(4,'1997-03-28 00:00:00.000000','2024-03-28 17:45:03.869367',4,'2024-03-28 17:45:03.869367','0987637432','TuVV','tuVan@gmail.com','Từ Liêm, Hà Nội','Admin','Vũ Văn Tú','Admin','MALE','HOAT_DONG'),(5,'1999-03-30 00:00:00.000000','2024-03-28 17:45:31.067570',5,'2024-03-28 17:45:31.067570','0983746473','NguyenVV','nguyen@gmail.com','Từ Liêm, Hà Nội','Admin','Vũ Văn Nguyên','Admin','MALE','HOAT_DONG'),(6,'1998-03-29 00:00:00.000000','2024-03-28 17:46:00.066492',6,'2024-03-28 17:46:00.066492','093424242','TrungĐH','trung@gmail.com','Từ Liêm, Hà Nội','Admin','Đinh Hà Trung','Admin','MALE','HOAT_DONG'),(15,NULL,'2024-03-29 09:23:14.372797',7,'2024-03-29 09:47:33.792620','+84-710-244-5319','ChauM','nga02@hotmail.com',NULL,'Admin','Minh Châu','Admin','MALE','HOAT_DONG'),(14,NULL,'2024-03-29 09:23:35.184893',8,'2024-03-29 09:51:16.689023','0241-996-2566','TrangC','phuong.dao@cam.gov.vn',NULL,'Admin','Châu Trang','Admin','FEMALE','HOAT_DONG'),(12,NULL,'2024-03-29 09:23:54.600519',9,'2024-03-29 09:47:33.792620','(0164) 202 2884','NgocH','khuu.cuong@hotmail.com',NULL,'Admin','Hoàng Ngọc','Admin','MALE','HOAT_DONG'),(16,NULL,'2024-03-29 09:24:07.640169',10,'2024-03-29 09:51:12.392911','099-423-3788','MyT','trung27@hong.ac.vn',NULL,'Admin','Thanh Mỹ','Admin','FEMALE','HOAT_DONG'),(20,NULL,'2024-03-29 09:24:21.798771',11,'2024-03-29 09:47:33.792620','096 919 5009','HoangT','gtra@pho.com',NULL,'Admin','Trọng Hoàng','Admin','MALE','HOAT_DONG'),(17,NULL,'2024-03-29 09:24:45.822684',12,'2024-03-29 09:51:23.312329','(0351)683-2635','TranH','hien96@gmail.com',NULL,'Admin','Hằng Trần','Admin','FEMALE','HOAT_DONG'),(10,NULL,'2024-03-29 09:24:58.623023',13,'2024-03-29 09:51:25.552853','096 570 4439','NhuM','bong.giang@gmail.com',NULL,'Admin','My Như','Admin','FEMALE','HOAT_DONG'),(18,NULL,'2024-03-29 09:25:18.260410',14,'2024-03-29 09:47:33.793142','(0230) 706 8573','TranB','trang.hung@hotmail.com',NULL,'Admin','Bình Trần','Admin','MALE','HOAT_DONG'),(11,NULL,'2024-03-29 09:25:56.071706',15,'2024-03-29 09:47:33.793142','093 281 2632','MaiT','dinh.trung@gmail.com',NULL,'Admin','Tân Mai','Admin','MALE','HOAT_DONG'),(7,NULL,'2024-03-29 09:26:39.215763',16,'2024-03-29 09:47:33.793142','054-539-8410','HaiL','ydu@gmail.com',NULL,'Admin','Lộc Hải','Admin','MALE','HOAT_DONG'),(13,NULL,'2024-03-29 09:26:51.422661',17,'2024-03-29 09:51:30.912589','0230 896 0913','NhuC','di.dau@su.net',NULL,'Admin','Cúc Như ','Admin','FEMALE','HOAT_DONG'),(8,NULL,'2024-03-29 09:27:09.680577',18,'2024-03-29 09:47:33.792104','(0781) 251 7822','VuĐ','thoi.trinh@phan.mil.vn',NULL,'Admin','Điều Vũ','Admin','MALE','HOAT_DONG'),(9,NULL,'2024-03-29 09:28:52.183193',19,'2024-03-29 09:47:33.792104','+84-96-236-0597','VuL','dan.hoan@yahoo.com',NULL,'Admin','Lộc Vũ','Admin','MALE','HOAT_DONG'),(21,NULL,'2024-03-29 09:29:07.717089',20,'2024-03-29 09:47:33.792104','(0123) 348 6659','TienK','ycu@gmail.com',NULL,'Admin','Khôi Tien','Admin','MALE','HOAT_DONG'),(19,NULL,'2024-03-29 09:29:21.566111',21,'2024-03-29 09:51:34.448490','+84-781-558-2934','NguyetO','hy.thai@hotmail.com',NULL,'Admin','Oanh Nguyệt','Admin','FEMALE','HOAT_DONG'),(22,NULL,'2024-03-29 10:24:48.856223',22,'2024-03-29 10:24:58.222374','09983434234','TranT','trieutuong2003@gmail.com',NULL,'Admin','Tú Trần','Admin','MALE','HOAT_DONG'),(23,NULL,'2024-03-29 10:31:03.646751',23,'2024-03-29 10:31:41.131629','0987653743','HungTV','gaupanda567@gmail.com',NULL,'Admin','Triệu Văn Hùng','Admin','MALE','HOAT_DONG'),(24,NULL,'2024-03-29 10:37:36.582719',24,'2024-03-29 10:38:00.583076','0987458374','NguyenD','gaupanda567@gmail.com',NULL,'Admin','Dường Nguyễn','Admin','MALE','HOAT_DONG');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-29 13:34:22

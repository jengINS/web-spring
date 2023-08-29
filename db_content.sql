-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 29, 2023 at 06:48 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_content`
--

-- --------------------------------------------------------

--
-- Table structure for table `alert_tb`
--

CREATE TABLE `alert_tb` (
  `uid` binary(36) NOT NULL,
  `category` varchar(100) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `detail` text DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `inset_date` datetime DEFAULT NULL,
  `link_redirec` text DEFAULT NULL,
  `status` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `alert_tb`
--

INSERT INTO `alert_tb` (`uid`, `category`, `title`, `detail`, `user_name`, `inset_date`, `link_redirec`, `status`) VALUES
(0x35353065383430302d653239622d34310000000000000000000000000000000000000000, 'Hello', 'Test system', 'Tester nitification s of user permission 1', 'Tester', NULL, NULL, NULL),
(0x62336261613230372d336561352d31310000000000000000000000000000000000000000, 'Hello', 'noti system', 'Tester nitification s of user permission Test', 'Tester', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(57);

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE `person` (
  `id` int(10) NOT NULL,
  `Fistname` varchar(255) NOT NULL,
  `Lastname` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `status_tb`
--

CREATE TABLE `status_tb` (
  `id` int(11) NOT NULL,
  `category` varchar(30) DEFAULT NULL,
  `statusName` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `status_tb`
--

INSERT INTO `status_tb` (`id`, `category`, `statusName`) VALUES
(1, 'boq_Status', 'Pending'),
(2, 'boq_Status', 'Appoved'),
(3, 'boq_Status', 'Reject'),
(6, 'log_status', 'In Guarantee'),
(7, 'log_status', 'Completed');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_authen`
--

CREATE TABLE `tbl_authen` (
  `au_id` int(10) NOT NULL,
  `user_insert` int(11) DEFAULT NULL,
  `user_update` int(11) DEFAULT NULL,
  `au_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `au_detail` text CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `insert_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `au_status` enum('A','N') CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_authen`
--

INSERT INTO `tbl_authen` (`au_id`, `user_insert`, `user_update`, `au_name`, `au_detail`, `insert_date`, `update_date`, `au_status`) VALUES
(1, 140, 0, 'Administrator', 'สำหรับ test ระบบ', NULL, NULL, 'A'),
(2, 4, 0, 'Backend_Config', 'จัดการการตั้งค่า config ', NULL, '2023-08-18 16:26:44', 'A'),
(3, 4, 0, 'Backend_UserManagement', 'จัดการรายชื่อผู้ใช้งาน การให้สิทธิ์การใช้งานต่างๆ', '2023-08-18 14:44:27', '2023-08-19 14:12:27', 'N');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_boq`
--

CREATE TABLE `tbl_boq` (
  `id` varchar(50) NOT NULL,
  `user_insert` int(11) DEFAULT NULL,
  `user_update` int(11) DEFAULT NULL,
  `cost` double DEFAULT NULL,
  `boq_status` int(11) DEFAULT NULL,
  `insert_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  `active_status` enum('A','N') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_boq`
--

INSERT INTO `tbl_boq` (`id`, `user_insert`, `user_update`, `cost`, `boq_status`, `insert_date`, `update_date`, `active_status`) VALUES
('aswq', 11, 11, 310, 1, '2023-08-27 21:20:42', '2023-08-27 21:20:42', 'A'),
('aswq1', 11, 11, 310, 1, '2023-08-27 21:25:23', '2023-08-27 21:25:23', 'A'),
('aswq132', 15, 15, 310, 2, '2023-08-27 21:25:43', '2023-08-27 21:25:43', 'A'),
('aswqaaaa', 15, 15, 10, 1, '2023-08-27 22:40:39', '2023-08-27 22:40:39', 'N'),
('aswqwq', 15, 15, 10, 3, '2023-08-27 22:45:04', '2023-08-27 22:45:04', 'A'),
('wwq10', 15, 15, 130, 1, '2023-08-27 21:15:07', '2023-08-27 21:15:07', 'A');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_boqdetail`
--

CREATE TABLE `tbl_boqdetail` (
  `UID` binary(36) NOT NULL,
  `No` varchar(10) NOT NULL,
  `BOQnumber` varchar(45) DEFAULT NULL,
  `category` varchar(30) NOT NULL,
  `destcription` varchar(120) DEFAULT NULL,
  `cost` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_boqdetail`
--

INSERT INTO `tbl_boqdetail` (`UID`, `No`, `BOQnumber`, `category`, `destcription`, `cost`) VALUES
(0x31643665346532392d343462642d313165652d623366332d613032393139373631643362, '1', 'ddasa', 'ค่าของ', 'asas', 111),
(0x31643665663139372d343462642d313165652d623366332d613032393139373631643362, '2', 'ddasa', 'ค่าของ', 'ewe', 222),
(0x31663436363135372d343462642d313165652d623366332d613032393139373631643362, '1', 'ddasa', 'ค่าของ', 'asas', 111),
(0x31663437316337332d343462642d313165652d623366332d613032393139373631643362, '2', 'ddasa', 'ค่าของ', 'ewe', 222),
(0x33343839356463642d343462642d313165652d623366332d613032393139373631643362, '1', 'daa', 'ค่าแรง', 'czx', 220),
(0x37343632383433372d343462642d313165652d623366332d613032393139373631643362, '1', 'www', 'MSF', 'as', 100),
(0x65613965626261312d343465332d313165652d623366332d613032393139373631643362, '1', 'HSI100', 'ค่าแรง', 'wqw', 100),
(0x65613966326166322d343465332d313165652d623366332d613032393139373631643362, '2', 'HSI100', 'ค่าแรง', 'dw', 200),
(0x31386566356330622d343465342d313165652d623366332d613032393139373631643362, '1', 'wwq10', 'ค่าแรง', 'wq', 10),
(0x31386566653831322d343465342d313165652d623366332d613032393139373631643362, '2', 'wwq10', 'ค่าแรง', 'asa', 120),
(0x38353463626236362d343465342d313165652d623366332d613032393139373631643362, '1', 'aswq', 'ค่าของ', 'wqq', 10),
(0x38353464343733652d343465342d313165652d623366332d613032393139373631643362, '2', 'aswq', 'ค่าของ', 'dsd', 300),
(0x65373734386238612d343465342d313165652d623366332d613032393139373631643362, '1', 'aswq', 'ค่าของ', 'wqq', 10),
(0x65373735323133312d343465342d313165652d623366332d613032393139373631643362, '2', 'aswq', 'ค่าของ', 'dsd', 300),
(0x36616361646261612d343465352d313165652d623366332d613032393139373631643362, '1', 'aswq', 'ค่าของ', 'wqq', 10),
(0x36616363353964612d343465352d313165652d623366332d613032393139373631643362, '2', 'aswq', 'ค่าของ', 'dsd', 300),
(0x38663362663538382d343465352d313165652d623366332d613032393139373631643362, '1', 'aswq1', 'ค่าของ', 'wqq', 10),
(0x38663363636630302d343465352d313165652d623366332d613032393139373631643362, '2', 'aswq1', 'ค่าของ', 'dsd', 300),
(0x39623437376339652d343465352d313165652d623366332d613032393139373631643362, '1', 'aswq132', 'ค่าของ', 'wqq', 10),
(0x39623437666238632d343465352d313165652d623366332d613032393139373631643362, '2', 'aswq132', 'ค่าของ', 'dsd', 300),
(0x39386538303565612d343466302d313165652d623366332d613032393139373631643362, '1', 'aswq', 'ค่าแรง', 'as', 10),
(0x61373831656566332d343466302d313165652d623366332d613032393139373631643362, '1', 'aswq', 'ค่าแรง', 'as', 10),
(0x62313163616662382d343466302d313165652d623366332d613032393139373631643362, '1', 'aswqwq', 'ค่าแรง', 'as', 10);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_detail`
--

CREATE TABLE `tbl_detail` (
  `id` int(11) NOT NULL,
  `boqid` varchar(50) DEFAULT NULL,
  `boq_catagory` int(11) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `cost` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_log`
--

CREATE TABLE `tbl_log` (
  `id` int(11) NOT NULL,
  `LetterID` varchar(20) DEFAULT NULL,
  `Project` int(11) DEFAULT NULL,
  `Amount` float DEFAULT NULL,
  `Guatantee` varchar(50) DEFAULT NULL,
  `Customer` varchar(50) DEFAULT NULL,
  `StartDate` datetime DEFAULT NULL,
  `EndDate` datetime DEFAULT NULL,
  `Status` int(11) DEFAULT NULL,
  `FilePath` text DEFAULT NULL,
  `InsertDate` datetime DEFAULT NULL,
  `UpdateDate` datetime DEFAULT NULL,
  `UpdateUser` int(11) DEFAULT NULL,
  `Active` enum('A','N') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_log`
--

INSERT INTO `tbl_log` (`id`, `LetterID`, `Project`, `Amount`, `Guatantee`, `Customer`, `StartDate`, `EndDate`, `Status`, `FilePath`, `InsertDate`, `UpdateDate`, `UpdateUser`, `Active`) VALUES
(1, 'lt001', 1, 10000, 'กรุงไทย', 'การรถไฟแห่งประเทศไทย', '2023-08-28 23:23:31', '2024-08-01 23:23:31', 6, 'storage\\test.pdf', '2023-08-29 23:23:31', '2023-08-29 23:23:31', 1, 'A');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_permission`
--

CREATE TABLE `tbl_permission` (
  `id` int(10) NOT NULL,
  `user_id` int(10) NOT NULL DEFAULT 0,
  `au_id` int(10) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_permission`
--

INSERT INTO `tbl_permission` (`id`, `user_id`, `au_id`) VALUES
(1, 4, 1),
(3, 4, 2);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_project`
--

CREATE TABLE `tbl_project` (
  `id` int(11) NOT NULL,
  `user_insert` int(11) DEFAULT NULL,
  `user_update` int(11) DEFAULT NULL,
  `project_no` varchar(50) DEFAULT NULL,
  `boq_id` varchar(50) DEFAULT NULL,
  `project_name` varchar(100) DEFAULT NULL,
  `project_sumexpense` double DEFAULT NULL,
  `project_remaining` double DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `project_manager` int(11) DEFAULT NULL,
  `budget` double DEFAULT NULL,
  `project_staff` varchar(50) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `insert_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `project_status` int(11) DEFAULT NULL,
  `status` enum('A','N') NOT NULL DEFAULT 'A'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_project`
--

INSERT INTO `tbl_project` (`id`, `user_insert`, `user_update`, `project_no`, `boq_id`, `project_name`, `project_sumexpense`, `project_remaining`, `start_date`, `end_date`, `project_manager`, `budget`, `project_staff`, `customer_id`, `insert_date`, `update_date`, `project_status`, `status`) VALUES
(1, 140, NULL, 'TestProject', 'BOQ', 'Test', NULL, NULL, '2023-08-29', '2023-08-31', 2, 50000, '[2, 3, 4]', 2, '2023-08-29 23:36:56', NULL, NULL, 'A');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_users`
--

CREATE TABLE `tbl_users` (
  `id` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `department` int(11) DEFAULT NULL,
  `group_user` int(11) DEFAULT NULL,
  `user_status` enum('A','N') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_users`
--

INSERT INTO `tbl_users` (`id`, `username`, `password`, `full_name`, `email`, `department`, `group_user`, `user_status`) VALUES
(1, 'chanayute', '1234', 'Chanayute Panyawai1', 'Chanayute_Pan@truecorp.co.th', 0, 0, 'A'),
(2, 'anuson', '1234', 'Anuson Insa', 'Anuson_Ins@truecorp.co.th', 0, 0, 'A'),
(3, 'anusorn', '1234', 'Anusorn Baina', 'Anusorn_Bai@truecorp.co.th', 0, 0, 'A'),
(4, 'sompong', '1234', 'Sompong Junrien', 'Sompong_Jun@truecorp.co.th', 0, 0, 'A'),
(5, 'jarunpong', '1234', 'Jarunpong Phueneam', 'Jarunpong_Phu@truecorp.co.th', 0, 0, 'A'),
(6, 'krisda', '1234', 'Krisda Batasiri', 'Krisda_Bat@truecorp.co.th', 0, 0, 'A'),
(7, 'krittanat', '', 'Krittanat Panyimrungroj', 'Krittanat_Pan@truecorp.co.th', 0, NULL, 'A'),
(11, 'test', '1234', 'Tester', 'sparkcool22@hotmail.com', 0, 0, 'A'),
(12, 'dd', '', 'dddsfsdfds', 'sparkcool22@hotmail.com', 1, NULL, 'N'),
(13, 'fff66', '', 'ff55559911389', 'sparkcool22@gmail.com', 22, NULL, 'N'),
(14, 'fgdfg55', '', 'ffdd559911', 'spark_cool22@hotmail.com', 0, NULL, 'N'),
(15, 'System', '1234', 'System admin', 'admin@admin.com', NULL, NULL, 'A');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `pass` varchar(255) DEFAULT NULL,
  `Full_Name` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `pass`, `Full_Name`, `Email`) VALUES
(1, 'chanayute', '1234', 'Chanayute Panyawai1', 'Chanayute_Pan@truecorp.co.th'),
(2, 'anuson', '1234', 'Anuson Insa', 'Anuson_Ins@truecorp.co.th'),
(3, 'anusorn', '1234', 'Anusorn Baina', 'Anusorn_Bai@truecorp.co.th'),
(4, 'sompong', '1234', 'Sompong Junrien', 'Sompong_Jun@truecorp.co.th'),
(5, 'jarunpong', '1234', 'Jarunpong Phueneam', 'Jarunpong_Phu@truecorp.co.th'),
(6, 'krisda', '1234', 'Krisda Batasiri', 'Krisda_Bat@truecorp.co.th'),
(7, 'krittanat', '1234', 'Krittanat Panyimrungroj', 'Krittanat_Pan@truecorp.co.th'),
(55, NULL, NULL, 'dddd', 'ddd'),
(56, NULL, NULL, 'dddd', 'ddd');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `alert_tb`
--
ALTER TABLE `alert_tb`
  ADD PRIMARY KEY (`uid`);

--
-- Indexes for table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `status_tb`
--
ALTER TABLE `status_tb`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_authen`
--
ALTER TABLE `tbl_authen`
  ADD PRIMARY KEY (`au_id`);

--
-- Indexes for table `tbl_boq`
--
ALTER TABLE `tbl_boq`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_detail`
--
ALTER TABLE `tbl_detail`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_log`
--
ALTER TABLE `tbl_log`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_permission`
--
ALTER TABLE `tbl_permission`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_project`
--
ALTER TABLE `tbl_project`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_users`
--
ALTER TABLE `tbl_users`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `person`
--
ALTER TABLE `person`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `status_tb`
--
ALTER TABLE `status_tb`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `tbl_authen`
--
ALTER TABLE `tbl_authen`
  MODIFY `au_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tbl_detail`
--
ALTER TABLE `tbl_detail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_log`
--
ALTER TABLE `tbl_log`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_permission`
--
ALTER TABLE `tbl_permission`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tbl_project`
--
ALTER TABLE `tbl_project`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_users`
--
ALTER TABLE `tbl_users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

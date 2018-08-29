-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Aug 28, 2018 at 06:55 PM
-- Server version: 5.6.39-cll-lve
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gravo_dev`
--

-- --------------------------------------------------------

--
-- Table structure for table `achievements`
--

CREATE TABLE `achievements` (
  `id` int(11) NOT NULL,
  `total_points` varchar(45) DEFAULT NULL,
  `recycler_id` int(11) NOT NULL,
  `achievement_rank_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `achievement_points`
--

CREATE TABLE `achievement_points` (
  `id` int(11) NOT NULL,
  `achievement_name` varchar(45) NOT NULL,
  `points` varchar(45) DEFAULT NULL,
  `achievements_id` int(11) NOT NULL,
  `achievements_recycler_id` int(11) NOT NULL DEFAULT '-1',
  `category_id` varchar(50) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



--
-- Table structure for table `achievement_rank`
--

CREATE TABLE `achievement_rank` (
  `id` int(11) NOT NULL,
  `rank_name` varchar(200) CHARACTER SET utf32 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `achievement_rank`
--

INSERT INTO `achievement_rank` (`id`, `rank_name`) VALUES
(1, 'Member'),
(2, 'Silver'),
(3, 'Gold'),
(4, 'Platinum');

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `bulk_transaction_history`
--

CREATE TABLE `bulk_transaction_history` (
  `id` int(11) NOT NULL,
  `bulk_transaction_item_id` int(11) NOT NULL,
  `history` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `bulk_transaction_item`
--

CREATE TABLE `bulk_transaction_item` (
  `id` int(11) NOT NULL,
  `recycler_id` int(11) NOT NULL,
  `description` varchar(500) NOT NULL,
  `image` varchar(200) NOT NULL,
  `price_quote` varchar(200) DEFAULT NULL,
  `bulk_transaction_status_id` int(11) NOT NULL,
  `collection_date` varchar(100) DEFAULT NULL,
  `collection_date_timing` varchar(200) NOT NULL DEFAULT '',
  `transaction_id_key` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `bulk_transaction_status`
--

CREATE TABLE `bulk_transaction_status` (
  `id` int(11) NOT NULL,
  `status` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bulk_transaction_status`
--

INSERT INTO `bulk_transaction_status` (`id`, `status`) VALUES
(1, 'Waiting for Quotation'),
(2, 'Waiting for Scheduling'),
(3, 'Waiting for Collection'),
(4, 'Driver has Arrived'),
(5, 'Collected and Paid'),
(6, 'Accepted Transaction'),
(7, 'Rejected Transaction'),
(12, 'Waiting for your response');

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `id` int(11) NOT NULL,
  `weight` varchar(45) NOT NULL,
  `recycler_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `cart_details`
--

CREATE TABLE `cart_details` (
  `id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `cart_id` int(11) NOT NULL,
  `cart_recycler_id` int(11) NOT NULL,
  `weight` decimal(10,2) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `item` varchar(200) NOT NULL,
  `rate` varchar(45) NOT NULL,
  `points` int(11) NOT NULL DEFAULT '1',
  `co2_points` decimal(5,3) NOT NULL DEFAULT '0.000',
  `achievement_tag` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `item`, `rate`, `points`, `co2_points`, `achievement_tag`) VALUES
(1, 'Paper | Newspaper', '0.10/KG', 1, '3.325', 'paper'),
(2, 'Paper | White Paper (black print only)', '0.11/KG', 1, '2.705', 'paper'),
(3, 'Paper | Cardboard Carton', '0.08/KG', 1, '2.245', 'paper'),
(4, 'Paper | Textbook, Magazine, Colored Paper', '0.10/KG', 1, '2.245', 'paper'),
(5, 'Metals | Copper Wire -(Diameter <= 4mm)', '3.50/KG', 2, '17.160', 'metals'),
(6, 'Metals | Copper Wire -(Diameter > 4mm)', '4.00/KG', 2, '17.160', 'metals'),
(7, 'Metals | Untainted Stripped -Copper Wire', '7.00/KG', 2, '17.160', 'metals'),
(8, 'Metals | Dirty Stripped -Copper Wire', '6.50/KG', 2, '17.160', 'metals'),
(9, 'Metals | Copper Pipe -Copper Plate', '6.50/KG', 2, '17.160', 'metals'),
(10, 'Metals | Mixed Wire -(bundled / coiled)', '3.00/KG', 2, '17.160', 'metals'),
(11, 'Metals | Brass - ', '2.50/KG', 2, '17.160', 'metals'),
(12, 'Metals | Aluminium (UBC)- ', '0.70/KG', 2, '17.160', 'metals'),
(13, 'E-Waste | Smartphone', '2.00/Piece', 1, '0.000', 'ewaste'),
(14, 'E-Waste | Laptop', '2.00/Piece', 1, '0.000', 'ewaste'),
(15, 'E-Waste | Computer CPU', '2.00/Piece', 1, '0.000', 'ewaste'),
(16, 'E-Waste | Computer Display', '2.00/Piece', 1, '0.000', 'ewaste'),
(17, 'E-Waste | PCB', '0.00/Piece', 1, '0.000', 'ewaste'),
(18, 'E-Waste | Battery', '0.00/Piece', 1, '0.000', 'ewaste'),
(19, 'E-Waste | Printer', '0.00/Piece', 1, '0.000', 'ewaste');

-- --------------------------------------------------------

--
-- Table structure for table `collector`
--

CREATE TABLE `collector` (
  `id` int(11) NOT NULL,
  `first_name` varchar(100) CHARACTER SET latin1 NOT NULL,
  `last_name` varchar(100) CHARACTER SET latin1 NOT NULL,
  `email` varchar(200) CHARACTER SET latin1 NOT NULL,
  `password` varchar(200) CHARACTER SET latin1 NOT NULL,
  `forgotPassword` varchar(200) CHARACTER SET latin1 DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET latin1 NOT NULL,
  `address` varchar(500) CHARACTER SET latin1 NOT NULL,
  `block` varchar(50) DEFAULT NULL,
  `unit` varchar(50) DEFAULT NULL,
  `street` varchar(100) DEFAULT NULL,
  `postal` varchar(50) DEFAULT NULL,
  `nric` varchar(20) CHARACTER SET latin1 NOT NULL,
  `liscence_number` varchar(20) CHARACTER SET latin1 NOT NULL,
  `vehicle_number` varchar(20) CHARACTER SET latin1 NOT NULL,
  `status` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `cron_tran`
--

CREATE TABLE `cron_tran` (
  `id` int(11) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `collector_id` int(11) DEFAULT NULL,
  `last_tran_count` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `recycler`
--

CREATE TABLE `recycler` (
  `id` int(11) NOT NULL,
  `facebook_id` varchar(200) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `full_name` varchar(200) NOT NULL,
  `email` varchar(100) NOT NULL,
  `photo` varchar(100) DEFAULT NULL,
  `password` varchar(45) NOT NULL,
  `forgotPassword` varchar(200) DEFAULT NULL,
  `contact_number` varchar(45) DEFAULT NULL,
  `address` varchar(300) DEFAULT NULL,
  `block` varchar(50) DEFAULT NULL,
  `unit` varchar(50) DEFAULT NULL,
  `street` varchar(200) DEFAULT NULL,
  `postal` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `status`
--

CREATE TABLE `status` (
  `id` int(11) NOT NULL,
  `status_type` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `status`
--

INSERT INTO `status` (`id`, `status_type`) VALUES
(1, 'Waiting for collection'),
(2, 'Driver is on The Way'),
(3, 'Driver has Arrived'),
(4, 'Collected and Paid'),
(5, 'Transaction Complete'),
(6, 'Driver Unassigned'),
(7, 'Driver Assigned'),
(8, 'Transaction cancelled');

-- --------------------------------------------------------

--
-- Table structure for table `tb`
--

CREATE TABLE `tb` (
  `id` bigint(10) NOT NULL,
  `time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `token`
--

CREATE TABLE `token` (
  `id` int(11) NOT NULL,
  `recycler_id` int(11) NOT NULL,
  `token` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `id` int(11) NOT NULL,
  `collection_date` date NOT NULL,
  `collection_date_timing` varchar(200) DEFAULT NULL,
  `collection_address` varchar(300) NOT NULL,
  `collection_postal` varchar(10) DEFAULT NULL,
  `collection_user` varchar(45) NOT NULL,
  `collection_contact_number` varchar(45) NOT NULL,
  `driver_arrival_time` varchar(200) DEFAULT NULL,
  `total_price` decimal(50,2) NOT NULL,
  `total_weight` varchar(200) DEFAULT NULL,
  `remarks` varchar(45) DEFAULT NULL,
  `transaction_id_key` varchar(120) NOT NULL,
  `recycler_id` int(11) NOT NULL,
  `collector_id` int(11) DEFAULT NULL,
  `status_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `transaction_details`
--

CREATE TABLE `transaction_details` (
  `id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL DEFAULT '0',
  `transaction_id` int(11) NOT NULL DEFAULT '0',
  `transaction_recycler_id` int(11) NOT NULL DEFAULT '0',
  `weight` decimal(10,2) NOT NULL DEFAULT '0.00',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `transaction_history`
--

CREATE TABLE `transaction_history` (
  `id` int(11) NOT NULL,
  `transaction_id` int(11) NOT NULL,
  `status` varchar(500) CHARACTER SET latin1 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Indexes for dumped tables
--

--
-- Indexes for table `achievements`
--
ALTER TABLE `achievements`
  ADD PRIMARY KEY (`id`,`recycler_id`),
  ADD KEY `fk_achievements_recycler1_idx` (`recycler_id`),
  ADD KEY `achievement_rank_id` (`achievement_rank_id`);

--
-- Indexes for table `achievement_points`
--
ALTER TABLE `achievement_points`
  ADD PRIMARY KEY (`id`,`achievements_id`,`achievements_recycler_id`,`category_id`),
  ADD KEY `fk_achievement_points_achievements1_idx` (`achievements_id`,`achievements_recycler_id`),
  ADD KEY `fk_achievement_points_category1_idx` (`category_id`);

--
-- Indexes for table `achievement_rank`
--
ALTER TABLE `achievement_rank`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bulk_transaction_history`
--
ALTER TABLE `bulk_transaction_history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `transaction_bulk_id1_idx` (`bulk_transaction_item_id`);

--
-- Indexes for table `bulk_transaction_item`
--
ALTER TABLE `bulk_transaction_item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `recycler_id` (`recycler_id`),
  ADD KEY `bulk_transaction_status_id` (`bulk_transaction_status_id`);

--
-- Indexes for table `bulk_transaction_status`
--
ALTER TABLE `bulk_transaction_status`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`,`recycler_id`),
  ADD KEY `fk_cart_recycler1_idx` (`recycler_id`);

--
-- Indexes for table `cart_details`
--
ALTER TABLE `cart_details`
  ADD PRIMARY KEY (`id`,`category_id`,`cart_id`,`cart_recycler_id`),
  ADD KEY `fk_cart_details_category1_idx` (`category_id`),
  ADD KEY `fk_cart_details_cart1_idx` (`cart_id`,`cart_recycler_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `collector`
--
ALTER TABLE `collector`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cron_tran`
--
ALTER TABLE `cron_tran`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `recycler`
--
ALTER TABLE `recycler`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email_UNIQUE` (`email`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb`
--
ALTER TABLE `tb`
  ADD KEY `id` (`id`);

--
-- Indexes for table `token`
--
ALTER TABLE `token`
  ADD PRIMARY KEY (`id`),
  ADD KEY `recycler_id` (`recycler_id`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id`,`recycler_id`,`status_id`),
  ADD KEY `fk_transaction_recycler1_idx` (`recycler_id`),
  ADD KEY `fk_transaction_status1_idx` (`status_id`);

--
-- Indexes for table `transaction_details`
--
ALTER TABLE `transaction_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_category_id` (`category_id`),
  ADD KEY `fk_transaction_id` (`transaction_id`),
  ADD KEY `fk_transaction_recycler_id` (`transaction_recycler_id`);

--
-- Indexes for table `transaction_history`
--
ALTER TABLE `transaction_history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `transaction_id` (`transaction_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `achievements`
--
ALTER TABLE `achievements`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `achievement_points`
--
ALTER TABLE `achievement_points`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `achievement_rank`
--
ALTER TABLE `achievement_rank`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `bulk_transaction_history`
--
ALTER TABLE `bulk_transaction_history`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `bulk_transaction_item`
--
ALTER TABLE `bulk_transaction_item`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `bulk_transaction_status`
--
ALTER TABLE `bulk_transaction_status`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `cart_details`
--
ALTER TABLE `cart_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `collector`
--
ALTER TABLE `collector`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `cron_tran`
--
ALTER TABLE `cron_tran`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `recycler`
--
ALTER TABLE `recycler`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `status`
--
ALTER TABLE `status`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `tb`
--
ALTER TABLE `tb`
  MODIFY `id` bigint(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `token`
--
ALTER TABLE `token`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `transaction_details`
--
ALTER TABLE `transaction_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `transaction_history`
--
ALTER TABLE `transaction_history`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `achievements`
--
ALTER TABLE `achievements`
  ADD CONSTRAINT `fk_achievements_rank1` FOREIGN KEY (`achievement_rank_id`) REFERENCES `achievement_rank` (`id`),
  ADD CONSTRAINT `fk_achievements_recycler1` FOREIGN KEY (`recycler_id`) REFERENCES `recycler` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `achievement_points`
--
ALTER TABLE `achievement_points`
  ADD CONSTRAINT `fk_achievement_points_achievements1` FOREIGN KEY (`achievements_id`,`achievements_recycler_id`) REFERENCES `achievements` (`id`, `recycler_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `bulk_transaction_history`
--
ALTER TABLE `bulk_transaction_history`
  ADD CONSTRAINT `bulk_transaction_history_ibfk_1` FOREIGN KEY (`bulk_transaction_item_id`) REFERENCES `bulk_transaction_item` (`id`);

--
-- Constraints for table `bulk_transaction_item`
--
ALTER TABLE `bulk_transaction_item`
  ADD CONSTRAINT `bulk_transaction_item_ibfk_1` FOREIGN KEY (`recycler_id`) REFERENCES `recycler` (`id`),
  ADD CONSTRAINT `bulk_transaction_item_ibfk_2` FOREIGN KEY (`bulk_transaction_status_id`) REFERENCES `bulk_transaction_status` (`id`);

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `fk_cart_recycler1` FOREIGN KEY (`recycler_id`) REFERENCES `recycler` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `cart_details`
--
ALTER TABLE `cart_details`
  ADD CONSTRAINT `fk_cart_details_cart1` FOREIGN KEY (`cart_id`,`cart_recycler_id`) REFERENCES `cart` (`id`, `recycler_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_cart_details_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `token`
--
ALTER TABLE `token`
  ADD CONSTRAINT `token_ibfk_1` FOREIGN KEY (`recycler_id`) REFERENCES `recycler` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `fk_transaction_recycler1` FOREIGN KEY (`recycler_id`) REFERENCES `recycler` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_transaction_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `transaction_details`
--
ALTER TABLE `transaction_details`
  ADD CONSTRAINT `fk_category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  ADD CONSTRAINT `fk_transaction_id` FOREIGN KEY (`transaction_id`) REFERENCES `transaction` (`id`),
  ADD CONSTRAINT `fk_transaction_recycler_id` FOREIGN KEY (`transaction_recycler_id`) REFERENCES `recycler` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

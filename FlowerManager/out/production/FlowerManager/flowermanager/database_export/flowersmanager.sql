-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 26, 2023 at 10:02 AM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `flowersmanager`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `image` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`, `image`) VALUES
(1, 'Admin', '123456', 'C:\\Users\\Admin\\Pictures\\admin.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `customer_id` int(100) NOT NULL,
  `flower_id` int(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `quantity` int(100) NOT NULL,
  `total_price` double NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `customer_id`, `flower_id`, `name`, `quantity`, `total_price`, `date`) VALUES
(1, 1, 1, 'Rose', 2, 21.1, '2023-04-21'),
(2, 1, 1, 'Rose', 2, 21.1, '2023-04-21'),
(3, 1, 1, 'Rose', 2, 21.1, '2023-04-21'),
(4, 1, 1, 'Rose', 2, 21.1, '2023-04-21'),
(5, 1, 2, 'Jasmine', 1, 10.55, '2023-04-21'),
(6, 1, 6, 'Pesels', 1, 10.5, '2023-04-21'),
(7, 2, 1, 'Rose', 6, 63.300000000000004, '2023-04-21'),
(8, 3, 1, 'Rose', 10, 105.5, '2023-04-21'),
(9, 4, 2, 'Jasmine', 1, 10.55, '2023-04-21'),
(10, 5, 1, 'Rose', 1, 10.55, '2023-04-21'),
(11, 6, 4, 'Sunflower', 2, 21.1, '2023-04-21'),
(12, 7, 4, 'Sunflower', 3, 31.650000000000002, '2023-04-21'),
(13, 8, 1, 'Rose', 1, 10.55, '2023-04-21'),
(14, 8, 2, 'Jasmine', 1, 10.55, '2023-04-21'),
(15, 9, 1, 'Rose', 1, 10.55, '2023-04-21'),
(16, 10, 22, 'Pesels', 1, 10.5, '2023-04-21'),
(17, 11, 23, 'Tulip', 10, 105.5, '2023-04-21'),
(19, 12, 2, 'Jasmine', 2, 21.1, '2023-04-21'),
(20, 12, 2, 'Jasmine', 2, 21.1, '2023-04-21'),
(22, 12, 2, 'Jasmine', 1, 10.55, '2023-04-21'),
(24, 13, 6, 'Pesels', 1, 10.5, '2023-04-21'),
(25, 13, 6, 'Pesels', 1, 10.5, '2023-04-21'),
(26, 13, 6, 'Pesels', 1, 10.5, '2023-04-21'),
(28, 14, 4, 'Sunflower', 1, 10.55, '2023-04-21'),
(29, 14, 3, 'Purple', 1, 10.55, '2023-04-21'),
(30, 15, 3, 'Purple', 1, 10.55, '2023-04-21'),
(31, 15, 3, 'Purple', 1, 10.55, '2023-04-21'),
(32, 15, 3, 'Purple', 1, 10.55, '2023-04-21'),
(33, 15, 3, 'Purple', 1, 10.55, '2023-04-21'),
(35, 16, 22, 'Pesels', 4, 42, '2023-04-22'),
(36, 16, 1, 'Rose', 1, 10.55, '2023-04-22'),
(37, 16, 2, 'Jasmine', 1, 10.55, '2023-04-22'),
(38, 16, 3, 'Purple', 2, 21.1, '2023-04-22'),
(39, 16, 1, 'Rose', 10, 105.5, '2023-04-22'),
(41, 17, 2, 'Jasmine', 4, 42.2, '2023-04-22'),
(42, 17, 6, 'Pesels', 1, 10.5, '2023-04-22'),
(43, 17, 6, 'Pesels', 1, 10.5, '2023-04-22'),
(44, 18, 2, 'Jasmine', 1, 10.55, '2023-04-22'),
(45, 19, 3, 'Purple', 2, 21.1, '2023-04-22'),
(46, 19, 3, 'Purple', 3, 31.650000000000002, '2023-04-22'),
(47, 20, 3, 'Purple', 2, 21.1, '2023-04-22'),
(49, 20, 1, 'Rose', 6, 63.300000000000004, '2023-04-22'),
(51, 21, 1, 'Rose', 1, 10.55, '2023-04-22'),
(52, 21, 1, 'Rose', 1, 10.55, '2023-04-22'),
(53, 21, 1, 'Rose', 1, 10.55, '2023-04-22'),
(55, 22, 1, 'Rose', 1, 10.55, '2023-04-22'),
(56, 23, 1, 'Rose', 1, 10.55, '2023-04-22'),
(57, 23, 1, 'Rose', 1, 10.55, '2023-04-22'),
(58, 23, 4, 'Sunflower', 1, 10.55, '2023-04-22'),
(60, 24, 3, 'Purple', 8, 84.4, '2023-04-22'),
(61, 25, 2, 'Jasmine', 1, 10.55, '2023-04-23'),
(62, 25, 2, 'Jasmine', 4, 42.2, '2023-04-23'),
(63, 25, 1, 'Rose', 7, 73.85000000000001, '2023-04-23'),
(66, 26, 2, 'Jasmine', 5, 52.75, '2023-04-23'),
(67, 26, 2, 'Jasmine', 5, 52.75, '2023-04-23'),
(68, 26, 2, 'Jasmine', 5, 52.75, '2023-04-23'),
(69, 26, 2, 'Jasmine', 5, 52.75, '2023-04-23'),
(70, 26, 2, 'Jasmine', 5, 52.75, '2023-04-23'),
(71, 26, 2, 'Jasmine', 5, 52.75, '2023-04-23'),
(72, 26, 2, 'Jasmine', 5, 52.75, '2023-04-23');

-- --------------------------------------------------------

--
-- Table structure for table `customer_info`
--

CREATE TABLE `customer_info` (
  `id` int(11) NOT NULL,
  `customer_id` int(100) NOT NULL,
  `total` float NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer_info`
--

INSERT INTO `customer_info` (`id`, `customer_id`, `total`, `date`) VALUES
(1, 1, 105.45, '2023-04-20'),
(2, 2, 63.3, '2023-04-20'),
(3, 3, 105.5, '2023-04-21'),
(4, 4, 10.55, '2023-04-20'),
(5, 5, 10.55, '2023-04-20'),
(6, 6, 21.1, '2023-04-20'),
(7, 7, 31.65, '2023-04-20'),
(8, 8, 21.1, '2023-04-20'),
(9, 9, 10.55, '2023-04-21'),
(10, 10, 10.5, '2023-04-21'),
(11, 11, 105.5, '2023-04-21'),
(12, 12, 52.75, '2023-04-21'),
(13, 13, 31.5, '2023-04-21'),
(14, 14, 21.1, '2023-04-21'),
(15, 15, 42.2, '2023-04-21'),
(16, 16, 189.7, '2023-04-22'),
(17, 17, 63.2, '2023-04-22'),
(18, 18, 10.55, '2023-04-22'),
(19, 19, 52.75, '2023-04-22'),
(20, 20, 84.4, '2023-04-22'),
(21, 21, 31.65, '2023-04-22'),
(22, 22, 10.55, '2023-04-22'),
(23, 23, 31.65, '2023-04-22'),
(24, 24, 84.4, '2023-04-22'),
(25, 25, 126.6, '2023-04-23');

-- --------------------------------------------------------

--
-- Table structure for table `flowers`
--

CREATE TABLE `flowers` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `status` varchar(100) NOT NULL,
  `price` double NOT NULL,
  `image` varchar(500) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `flowers`
--

INSERT INTO `flowers` (`id`, `name`, `status`, `price`, `image`, `date`) VALUES
(29, 'Peselss', 'Not Available', 10.5, 'src\\\\\\\\flowermanager\\\\\\\\image\\\\\\\\plants-with-big-flowers-4138211-hero-b10becb169064cc4b3c7967adc1b22e1.jpg', '2023-04-24'),
(35, 'Peselss', 'Not Available', 10.5, 'src\\\\\\\\\\\\\\\\flowermanager\\\\\\\\\\\\\\\\image\\\\\\\\\\\\\\\\plants-with-big-flowers-4138211-hero-b10becb169064cc4b3c7967adc1b22e1.jpg', '2023-04-24'),
(38, 'Red', 'Available', 1234, 'src\\\\flowermanager\\\\image\\\\purple-flower-2212075.jpg', '2023-04-24'),
(39, 'Red', 'Available', 1234, 'src\\\\\\\\flowermanager\\\\\\\\image\\\\\\\\purple-flower-2212075.jpg', '2023-04-24'),
(43, 'Peselss', 'Not Available', 10.5, 'src\\\\\\\\\\\\\\\\flowermanager\\\\\\\\\\\\\\\\image\\\\\\\\\\\\\\\\plants-with-big-flowers-4138211-hero-b10becb169064cc4b3c7967adc1b22e1.jpg', '2023-04-26'),
(44, 'Peselss', 'Not Available', 10.5, 'src\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\flowermanager\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\image\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\plants-with-big-flowers-4138211-hero-b10becb169064cc4b3c7967adc1b22e1.jpg', '2023-04-26'),
(45, 'Red', 'Available', 1234, 'src\\\\\\\\\\\\\\\\flowermanager\\\\\\\\\\\\\\\\image\\\\\\\\\\\\\\\\purple-flower-2212075.jpg', '2023-04-26');

-- --------------------------------------------------------

--
-- Table structure for table `return_flower`
--

CREATE TABLE `return_flower` (
  `id` int(11) NOT NULL,
  `amount` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `return_flower`
--

INSERT INTO `return_flower` (`id`, `amount`) VALUES
(3, 10.55),
(4, 21.1),
(5, 10.55),
(6, 10.55),
(7, 0),
(8, 10.55),
(9, 105.5),
(10, 10.55),
(11, 105);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer_info`
--
ALTER TABLE `customer_info`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `flowers`
--
ALTER TABLE `flowers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `return_flower`
--
ALTER TABLE `return_flower`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=73;

--
-- AUTO_INCREMENT for table `customer_info`
--
ALTER TABLE `customer_info`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `flowers`
--
ALTER TABLE `flowers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT for table `return_flower`
--
ALTER TABLE `return_flower`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

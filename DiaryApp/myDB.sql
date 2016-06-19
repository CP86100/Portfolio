-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 07, 2016 at 12:45 PM
-- Server version: 10.1.10-MariaDB
-- PHP Version: 7.0.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `myDB`
--

-- --------------------------------------------------------

--
-- Table structure for table `diary`
--

CREATE TABLE `diary` (
  `whenwhere` varchar(100) NOT NULL,
  `event` varchar(100) NOT NULL,
  `emotion` varchar(100) NOT NULL,
  `auto` varchar(100) NOT NULL,
  `response` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `diary`
--

INSERT INTO `diary` (`whenwhere`, `event`, `emotion`, `auto`, `response`) VALUES
('Some plpace', 'Doing Something', 'not sure', 'Should think more', 'do nothing'),
('Home', 'Relax', 'None', 'This is good', 'Keep relaxing'),
('N/A', 'N/A', 'N/A', 'N/A', 'N/A'),
('blablabla', 'blabla', 'bla', 'blablablablabla', 'bla'),
('a', 'b', 'c', 'd', 'e'),
('e', 'd', 'c', 'b', 'a'),
('random', 'stuff', 'to ', 'put ', 'in');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

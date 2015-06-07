-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 05, 2015 at 06:49 PM
-- Server version: 5.5.39
-- PHP Version: 5.4.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE IF NOT EXISTS `message` (
`messageid` int(11) NOT NULL,
  `subject` varchar(45) DEFAULT NULL,
  `message` varchar(100) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `phone` int(11) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `message`
--

INSERT INTO `message` (`messageid`, `subject`, `message`, `name`, `phone`, `email`) VALUES
(5, 'feedback', 'nice food.rates up to mark.good ambience', 'shantanu ranjan', 34343, 'shantanuranjan3@gmail.com'),
(6, 'kyabaat', 'nice place', 'sdsdad', 34534, 'asfasda@fsdfsdf'),
(7, 'new message', 'testing new message', 'ronit roy', 3434, 'ronit@gmail.com'),
(8, 'feedback', 'test feedbck', 'test', 3434, 'feedback@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `owner`
--

CREATE TABLE IF NOT EXISTS `owner` (
`ownerid` int(11) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `dob` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `phone` int(11) DEFAULT NULL,
  `img` longblob
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `owner`
--

INSERT INTO `owner` (`ownerid`, `username`, `password`, `name`, `dob`, `email`, `address`, `city`, `phone`, `img`) VALUES
(1, 'shantanu.ranjan', 'ranjan', 'shantanu ranjan', '06/11/1989', 'sr3306@nyu.edu', '9521 lefferts blvd', 'queens', 343434, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE IF NOT EXISTS `reservation` (
`reservationid` int(11) NOT NULL,
  `reservationdate` varchar(20) DEFAULT NULL,
  `reservationtime` varchar(20) DEFAULT NULL,
  `firstname` varchar(40) DEFAULT NULL,
  `lastname` varchar(40) DEFAULT NULL,
  `phone` int(11) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `partysize` int(11) DEFAULT NULL,
  `specialneeds` varchar(50) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=40 ;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`reservationid`, `reservationdate`, `reservationtime`, `firstname`, `lastname`, `phone`, `email`, `partysize`, `specialneeds`) VALUES
(30, '30/05/2015', '8:00 PM', 'shantanu', 'ranjan', 3434, 'shantanuranjan3@gmail.com', 8, 'ambience'),
(32, '01/07/2015', '9:00 PM', 'kumar', 'sanu', 3434, 'kumar@gmail.com', 5, 'ambience'),
(34, '03/06/2015', '9:00 PM', 'rahul', 'verma', 0, 'sr3306@nyu.edu', 8, 'ambience'),
(36, '06/06/2015', '9:00 PM', 'shantanu', 'sdfsdfs', 345345, 'shantanuranjan3@gmail.com', 8, 'ambience'),
(39, '07/06/2015', '9:00 PM', 'tssd', 'sfsf', 345345, 'sdfsfs@sfsdf', 6, 'sfsfs');

-- --------------------------------------------------------

--
-- Table structure for table `seating`
--

CREATE TABLE IF NOT EXISTS `seating` (
`tableno` int(11) NOT NULL,
  `status` varchar(45) DEFAULT NULL,
  `reservationid` int(11) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `seating`
--

INSERT INTO `seating` (`tableno`, `status`, `reservationid`) VALUES
(1, 'occupied', 30),
(2, 'available', 0),
(3, 'occupied', 34),
(5, 'available', NULL),
(6, 'available', 0),
(7, 'occupied', 32),
(8, 'occupied', 36),
(9, 'occupied', 39),
(10, 'available', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `message`
--
ALTER TABLE `message`
 ADD PRIMARY KEY (`messageid`);

--
-- Indexes for table `owner`
--
ALTER TABLE `owner`
 ADD PRIMARY KEY (`ownerid`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
 ADD PRIMARY KEY (`reservationid`);

--
-- Indexes for table `seating`
--
ALTER TABLE `seating`
 ADD PRIMARY KEY (`tableno`), ADD KEY `reserve_idx` (`reservationid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `message`
--
ALTER TABLE `message`
MODIFY `messageid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `owner`
--
ALTER TABLE `owner`
MODIFY `ownerid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
MODIFY `reservationid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=40;
--
-- AUTO_INCREMENT for table `seating`
--
ALTER TABLE `seating`
MODIFY `tableno` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

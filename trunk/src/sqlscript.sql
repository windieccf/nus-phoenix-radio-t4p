-- phpMyAdmin SQL Dump
-- version 3.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Sep 20, 2012 at 09:07 PM
-- Server version: 5.5.25a
-- PHP Version: 5.4.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `phoenix`
--

-- --------------------------------------------------------

--
-- Table structure for table `PROGRAM_SLOT`
--

DROP TABLE IF EXISTS `PROGRAM_SLOT`;
CREATE TABLE IF NOT EXISTS `PROGRAM_SLOT` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `RADIO_PROGRAM_ID` bigint(20) DEFAULT NULL,
  `PRESENTER_ID` bigint(20) DEFAULT NULL,
  `PRODUCER_ID` bigint(20) DEFAULT NULL,
  `START_DATETIME` datetime DEFAULT NULL,
  `END_DATETIME` datetime DEFAULT NULL,
  `STATUS` char(1) CHARACTER SET latin1 DEFAULT NULL,
  `CREATED_BY_ID` bigint(20) DEFAULT NULL,
  `MODIFIED_BY_ID` bigint(20) DEFAULT NULL,
  `CREATED_DATETIME` datetime DEFAULT NULL,
  `MODIFIED_DATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

--
-- Dumping data for table `PROGRAM_SLOT`
--

INSERT INTO `PROGRAM_SLOT` (`ID`, `RADIO_PROGRAM_ID`, `PRESENTER_ID`, `PRODUCER_ID`, `START_DATETIME`, `END_DATETIME`, `STATUS`, `CREATED_BY_ID`, `MODIFIED_BY_ID`, `CREATED_DATETIME`, `MODIFIED_DATETIME`) VALUES
(1, 3, NULL, NULL, '2012-08-27 00:00:00', '2012-08-27 03:00:00', 'D', 1, 1, '2012-09-20 20:28:29', '2012-09-20 20:38:45'),
(2, 3, NULL, NULL, '2012-09-10 00:00:00', '2012-09-10 03:00:00', 'A', 1, NULL, '2012-09-20 20:31:25', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `RADIO_PROGRAM`
--

DROP TABLE IF EXISTS `RADIO_PROGRAM`;
CREATE TABLE IF NOT EXISTS `RADIO_PROGRAM` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PROGRAM_NAME` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `PROGRAM_DESC` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TYPICAL_DURATION` time DEFAULT NULL,
  `STATUS` char(1) CHARACTER SET latin1 DEFAULT NULL,
  `CREATED_BY_ID` bigint(20) DEFAULT NULL,
  `MODIFIED_BY_ID` bigint(20) DEFAULT NULL,
  `CREATED_DATETIME` datetime DEFAULT NULL,
  `MODIFIED_DATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=11 ;

--
-- Dumping data for table `RADIO_PROGRAM`
--

INSERT INTO `RADIO_PROGRAM` (`ID`, `PROGRAM_NAME`, `PROGRAM_DESC`, `TYPICAL_DURATION`, `STATUS`, `CREATED_BY_ID`, `MODIFIED_BY_ID`, `CREATED_DATETIME`, `MODIFIED_DATETIME`) VALUES
(1, 'Breakfast Club', 'Your daily dose of wellness, inspiration, arts and lifestyle.', '02:00:00', 'A', 4, 4, '2012-09-18 13:14:38', '2012-09-21 10:22:35'),
(2, 'The Living Room', 'Bringing you chats with guests from the entertainment scene.', '01:30:00', 'A', 4, 4, '2012-09-20 11:12:41', '2012-09-18 16:36:51'),
(3, 'A Slice of Life Hour', 'A talkshow that features guests who share motivational advice, and inspiring life stories.', '01:00:00', 'A', 4, 4, '2012-09-02 18:23:02', '2012-09-11 03:30:55'),
(4, 'Moneywise', 'Get smart with our money!', '03:00:00', 'A', 4, 4, '2012-09-13 02:37:10', '2012-09-21 04:29:03'),
(5, 'The Hot Seat', 'The hottest thing about Will Xavier''s radio show will be the guests themselves.', '03:00:00', 'A', 4, 4, '2012-09-05 03:15:28', '2012-09-07 11:05:56'),
(6, 'Parenting Made Easy', 'Helping you overcome the challenges of parenting and making the experience more enriching.', '03:30:00', 'A', 4, 4, '2012-09-07 09:12:36', '2012-09-11 15:13:21'),
(7, 'They are Making a Di', 'This series highlights people who make a difference in the lives of others and in the world around t', '02:30:00', 'A', 4, 4, '2012-09-12 07:34:01', '2012-09-06 07:35:38'),
(8, 'SportsZone', 'Previews and reviews of sports events and interviews with personalities.', '01:30:00', 'A', 4, 4, '2012-09-01 05:04:15', '2012-09-03 02:50:03'),
(9, 'The WOW Club', 'The WOW Club is inclusive space for people and ideas that all have that mysterious indefinable WOW f', '01:00:00', 'A', 4, 4, '2012-09-17 02:23:06', '2012-09-18 13:13:04'),
(10, 'TalkBack', 'Share your views and experiences on issues that affect our everyday lives.', '01:30:00', 'A', 4, 4, '2012-09-19 06:07:19', '2012-09-20 12:14:17');

-- --------------------------------------------------------

--
-- Table structure for table `ROLE`
--

DROP TABLE IF EXISTS `ROLE`;
CREATE TABLE IF NOT EXISTS `ROLE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACCESS_PRIVILEDGE` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STATUS` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATED_DATETIME` datetime DEFAULT NULL,
  `MODIFIED_DATETIME` blob,
  `CREATED_BY_ID` int(11) DEFAULT NULL,
  `MODIFIED_BY_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=5 ;

--
-- Dumping data for table `ROLE`
--

INSERT INTO `ROLE` (`ID`, `ROLE`, `ACCESS_PRIVILEDGE`, `STATUS`, `CREATED_DATETIME`, `MODIFIED_DATETIME`, `CREATED_BY_ID`, `MODIFIED_BY_ID`) VALUES
(1, 'Admin', 'Y', 'A', NULL, NULL, NULL, NULL),
(2, 'Manager', 'Y', 'A', NULL, NULL, NULL, NULL),
(3, 'Presenter', 'Y', 'A', NULL, NULL, NULL, NULL),
(4, 'Producer', 'Y', 'A', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `USER`
--

DROP TABLE IF EXISTS `USER`;
CREATE TABLE IF NOT EXISTS `USER` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `PASSWORD` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `FIRST_NAME` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `LAST_NAME` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `DOB` datetime DEFAULT NULL,
  `EMAIL` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `ADDRESS` text CHARACTER SET latin1,
  `CONTACT_HOME` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `CONTACT_MOBILE` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `JOIN_DATE` datetime DEFAULT NULL,
  `STATUS` char(1) CHARACTER SET latin1 DEFAULT NULL,
  `CREATED_BY_ID` bigint(20) DEFAULT NULL,
  `MODIFIED_BY_ID` bigint(20) DEFAULT NULL,
  `CREATED_DATETIME` datetime DEFAULT NULL,
  `MODIFIED_DATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=5 ;

--
-- Dumping data for table `USER`
--

INSERT INTO `USER` (`ID`, `USERNAME`, `PASSWORD`, `FIRST_NAME`, `LAST_NAME`, `DOB`, `EMAIL`, `ADDRESS`, `CONTACT_HOME`, `CONTACT_MOBILE`, `JOIN_DATE`, `STATUS`, `CREATED_BY_ID`, `MODIFIED_BY_ID`, `CREATED_DATETIME`, `MODIFIED_DATETIME`) VALUES
(1, 'admin', 'password', 'Administrator', 'Account', '1980-03-12 00:00:00', 'iss4pt@googlegroups.com', 'Institute Of System Science (ISS)25 Heng Mui Keng TerraceSingapore 119615', NULL, NULL, '2012-09-01 00:00:00', 'A', 1, 1, '2012-09-15 00:00:00', '2012-09-15 00:00:00'),
(2, 'manager', 'password', 'Manager', 'User', '1981-02-18 00:00:00', 'manager_phoenix_radio@gmail.com', '697B East Coast Road', NULL, NULL, '2012-09-12 00:00:00', NULL, 1, 1, '2012-09-17 12:20:19', '2012-09-16 04:21:10'),
(3, 'presenter', 'password', 'Presenter', 'User', '1972-08-06 00:00:00', 'presenter_phoenix_radio@gmail.com', '101 Yishun Ave 5 #01-37', NULL, NULL, '2012-07-11 00:00:00', NULL, 1, 1, '2012-09-13 02:22:39', '2012-09-18 04:12:14'),
(4, 'producer', 'password', 'Producer', 'User', '1976-03-16 00:00:00', 'producer_phoenix_radio@gmail.com', '351 Jurong East Street 31, #01-93', NULL, NULL, '2012-08-08 00:00:00', NULL, 1, 1, '2012-09-15 18:20:46', '2012-09-16 13:22:32');

-- --------------------------------------------------------

--
-- Table structure for table `USER_ROLE`
--

DROP TABLE IF EXISTS `USER_ROLE`;
CREATE TABLE IF NOT EXISTS `USER_ROLE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL,
  `ROLE_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=8 ;

--
-- Dumping data for table `USER_ROLE`
--

INSERT INTO `USER_ROLE` (`ID`, `USER_ID`, `ROLE_ID`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 1, 4),
(5, 2, 2),
(6, 3, 3),
(7, 4, 4);

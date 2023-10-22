-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: May 24, 2022 at 01:42 PM
-- Server version: 10.5.12-MariaDB
-- PHP Version: 7.3.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id18036852_bbms`
--

-- --------------------------------------------------------

--
-- Table structure for table `BloodReq`
--

CREATE TABLE `BloodReq` (
  `R_id` int(11) NOT NULL,
  `Name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Contact` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Address` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `City` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Blood` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `Date` date NOT NULL,
  `Time` time NOT NULL,
  `Bags` int(11) NOT NULL,
  `UserIdKey` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `BloodReq`
--

INSERT INTO `BloodReq` (`R_id`, `Name`, `Contact`, `Address`, `City`, `Blood`, `Date`, `Time`, `Bags`, `UserIdKey`) VALUES
(12, 'Uzair', '03002897654', 'Malir', 'Karachi', 'B+', '2021-12-24', '00:18:56', 4, 4),
(13, 'Kiran', '03002897654', 'Malir', 'Karachi', 'A+', '2021-12-24', '01:18:56', 4, 1),
(14, 'Ahmed', '03002897654', 'Malir', 'Karachi', 'AB-', '2021-12-24', '00:28:56', 2, 1),
(15, 'Kamlesh', '03002897654', 'Landhi', 'Karachi', 'A+', '2021-12-24', '01:18:56', 7, 4),
(16, 'Kinza', '03002897654', 'Liyari', 'Karachi', 'O-', '2021-12-24', '00:28:56', 4, 5),
(17, 'Kamran', '03002897654', 'Landhi', 'Karachi', 'A-', '2021-12-24', '01:18:56', 9, 4),
(18, 'Kashif', '03002897654', 'Model Colony', 'Lahore', 'O-', '2021-12-24', '00:28:56', 12, 1),
(21, 'Muzaffar', '03123662911', 'Kala pull', 'Karachi', 'O-', '2022-01-09', '16:23:14', 5, 1),
(23, 'Subhan', '03172012587', 'Hussainabad', 'Karachi', 'A+', '2022-01-10', '03:09:42', 2, 1),
(24, 'Moiz', '090078602', 'JAMA cloth', 'karachi', 'AB+', '2022-01-10', '12:26:43', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `UserAccount`
--

CREATE TABLE `UserAccount` (
  `Id` int(11) NOT NULL,
  `Name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Contact` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Password` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `BloodGroup` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `Donor` tinyint(1) NOT NULL,
  `Address` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `City` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `UserAccount`
--

INSERT INTO `UserAccount` (`Id`, `Name`, `Contact`, `Email`, `Password`, `BloodGroup`, `Donor`, `Address`, `City`) VALUES
(1, 'Uzair', '090078601', 'uzair@gmail.com', '123', 'O-', 0, 'mehmoodabad', 'karachi'),
(4, 'Shezad', '0900000034', 'Shezad@gmail.com', '123', 'B+', 1, 'Kimari', 'Karahi'),
(5, 'jazz', '03245363782', 'j@gmail.com', '123', 'A+', 0, 'DHA', 'Lahore'),
(7, 'Sheeraz', '03462908905', 'S@gmail.com', '123', 'AB+', 1, 'Liyari', 'Karachi'),
(8, 'Hussain', '03462908905', 'theshirazsays@gmail.com', 'srk0306', 'A-', 1, 'Landhi', 'Karachi'),
(9, 'Liza', '0989076468245', 'lala@gmail.com', '123', 'AB+', 1, 'ModeL colony', 'Lahore'),
(10, 'srk', '03462908905', 'thesheerazsays@gmail.com', 'srk0306', 'O+', 1, 'Lalokhait', 'Karachi'),
(16, 'Saqib', '09781621781', 'Saqib@gmail.com', '123', 'B-', 1, 'Mehmoodabad', 'Karachi'),
(17, 'Subhan', '03122012587', 'Subhan@gmail.com', '123', 'B+', 1, 'Hussainabad', 'Karachi');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `BloodReq`
--
ALTER TABLE `BloodReq`
  ADD PRIMARY KEY (`R_id`),
  ADD KEY `UserIdKey` (`UserIdKey`);

--
-- Indexes for table `UserAccount`
--
ALTER TABLE `UserAccount`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `BloodReq`
--
ALTER TABLE `BloodReq`
  MODIFY `R_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `UserAccount`
--
ALTER TABLE `UserAccount`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `BloodReq`
--
ALTER TABLE `BloodReq`
  ADD CONSTRAINT `BloodReq_ibfk_1` FOREIGN KEY (`UserIdKey`) REFERENCES `UserAccount` (`Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

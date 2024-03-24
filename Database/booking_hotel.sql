-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 22, 2024 at 09:08 AM
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
-- Database: `booking_hotel`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `id` int(11) NOT NULL,
  `username` varchar(250) DEFAULT NULL,
  `password` varchar(250) DEFAULT NULL,
  `fullname` varchar(250) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `address` varchar(250) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `role_id` int(11) NOT NULL,
  `created` date DEFAULT NULL,
  `updateted` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`id`, `username`, `password`, `fullname`, `birthday`, `status`, `address`, `email`, `role_id`, `created`, `updateted`) VALUES
(1, 'admin', '$2a$10$4hst3BT3DjrCESRA3/X46e1F/sqE46FKiIESSgdxrAybSu1XdIfGG', 'Admin_01', '2024-03-18', 1, '35/6 D5, phuong 25 , Binh Thanh', 'admin@gmail.com', 1, '2024-03-18', NULL),
(2, 'hotel1', '$2a$10$4hst3BT3DjrCESRA3/X46e1F/sqE46FKiIESSgdxrAybSu1XdIfGG', 'Hotel_01', '2024-03-18', 1, '35/6 D5, phuong 25 , Binh Thanh', 'hotel1@gmail.com', 2, '2024-03-18', NULL),
(3, 'hotel2', '$2a$10$4hst3BT3DjrCESRA3/X46e1F/sqE46FKiIESSgdxrAybSu1XdIfGG', 'Hotel_02', '2024-03-18', 1, '35/6 D5, phuong 25 , Binh Thanh', 'hotel2@gmail.com', 2, NULL, '2024-03-20'),
(4, 'hotel3', '$2a$10$4hst3BT3DjrCESRA3/X46e1F/sqE46FKiIESSgdxrAybSu1XdIfGG', 'User_001', '2024-03-18', 1, '35/6 D5, phuong 25 , Binh Thanh', 'hotel3@gmail.com', 2, '2024-03-18', NULL),
(5, 'hotel4', '$2a$10$4hst3BT3DjrCESRA3/X46e1F/sqE46FKiIESSgdxrAybSu1XdIfGG', 'Hotel_04', '2024-03-18', 1, '35/6 D5, phuong 25 , Binh Thanh', 'hotel4@gmail.com', 2, '2024-03-18', NULL),
(6, 'hotel5', '$2a$10$4hst3BT3DjrCESRA3/X46e1F/sqE46FKiIESSgdxrAybSu1XdIfGG', 'Hotel_05', '2024-03-18', 1, '35/6 D5, phuong 25 , Binh Thanh', 'hotel5@gmail.com', 2, '2024-03-18', NULL),
(7, 'user1', '$2a$10$4hst3BT3DjrCESRA3/X46e1F/sqE46FKiIESSgdxrAybSu1XdIfGG', 'User_01', '2024-03-18', 1, '35/6 D5, phuong 25 , Binh Thanh', '16minhkha06@gmail.com', 3, '2024-03-18', NULL),
(8, 'user2', '$2a$10$4hst3BT3DjrCESRA3/X46e1F/sqE46FKiIESSgdxrAybSu1XdIfGG', 'User_02', '2024-03-18', 1, '35/6 D5, phuong 25 , Binh Thanh', 'user2@gmail.com', 3, '2024-03-18', NULL),
(9, 'kha.km656@aptechlearning.edu.vn', '$2a$10$NJyI9yqIkjZidlHPp4u3OufN.6tRutpOiJxOiDzkvN0HCpwm1qdta', 'Minh Kha Kiều', '2024-03-18', 0, 'Ấp bến chò , xã thạnh đức , huyện gò dầu , tỉnh tây ninh', 'kha.km656@aptechlearning.edu.vn', 3, '2024-03-19', NULL),
(10, 'hotel6', '$2a$10$TozVEwSHE1pMuIpq1l6lsOwGG3WmhvjWxq.ZVZW6HCNrfezuriKxq', 'Minh Kha Kiều', '2024-03-18', 1, 'Ấp bến chò , xã thạnh đức , huyện gò dầu , tỉnh tây ninh', 'kha.km656@aptechlearning.edu.vn', 2, '2024-03-20', NULL),
(11, 'kha02', '$2a$10$D8liL.dehmfhiGyUXimnw.Hz9kLKs/j1C6cmCc1RYeR58dNuL0gQq', 'kha02', NULL, 0, 'Ấp bến chò , xã thạnh đức , huyện gò dầu , tỉnh tây ninh', 'a@gmail.com', 3, '2024-03-20', NULL),
(12, 'thai1', '$2a$10$4VrAKVo0ASo/uyKBRVexOud4H2/w1uF46v0I76O6cvZa/csTrev6q', 'Minh Kha Kiều', NULL, 1, 'Ấp bến chò , xã thạnh đức , huyện gò dầu , tỉnh tây ninh', 'kha.km656@aptechlearning.edu.vn', 3, '2024-03-20', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `area`
--

CREATE TABLE `area` (
  `id` int(11) NOT NULL,
  `name` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `area`
--

INSERT INTO `area` (`id`, `name`) VALUES
(1, 'HCM City'),
(2, 'Ha Noi City'),
(3, 'Tay Ninh City'),
(4, 'Bao Loc City'),
(5, 'Da Lat City'),
(6, 'Vung Tau City'),
(7, 'Hue City');

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `id` int(11) NOT NULL,
  `code_book` varchar(250) NOT NULL,
  `total` double NOT NULL,
  `date` date NOT NULL,
  `note` varchar(250) DEFAULT NULL,
  `status` tinyint(1) NOT NULL,
  `account_id` int(11) NOT NULL,
  `created` date DEFAULT NULL,
  `updated` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`id`, `code_book`, `total`, `date`, `note`, `status`, `account_id`, `created`, `updated`) VALUES
(1, 'mak15anuubcai454', 4, '2024-03-19', NULL, 1, 7, '2024-03-18', NULL),
(2, 'mak15anuubcaah51232', 8, '2024-03-19', NULL, 1, 8, '2024-03-18', NULL),
(9, 'ESBOOK-b3b71e0b', 4, '2024-03-01', 'Người book:Minh Kha Kiều-cccd:aaa-email:aaaaa-phone:0379409979', 1, 4, '2024-03-20', NULL),
(10, 'ESBOOK-6a4addd5', 1, '2024-03-01', 'Người book:khaaaaa-cccd:0214124313-email:-phone:00214214124', 1, 7, '2024-03-20', NULL),
(11, 'ESBOOK-7b961439', 1, '2024-03-01', 'Người book:aaaa-cccd:aaaa-email:-phone:aaaa', 1, 7, '2024-03-20', NULL),
(12, 'ESBOOK-c7e7e8e2', 1, '2024-03-01', 'Người book:aaa-cccd:aaa-email:-phone:aaaa', 1, 7, '2024-03-20', NULL),
(13, 'ESBOOK-29f7db44', 1, '2024-03-20', 'Người book:1111-cccd:111-email:16minhkha06@gmail.com-phone:111', 1, 7, '2024-03-20', NULL),
(14, 'ESBOOK-e8ad0540', 1, '2024-03-01', 'Người book:Minh Kha Kiều-cccd:09131231321312-email:16minhkha06@gmail.com-phone:0379409979', 1, 7, '2024-03-20', NULL),
(15, 'ESBOOK-91c100fc', 4, '2024-03-01', 'Người book:Minh Kha Kiều-cccd:26522-email:16minhkha06@gmail.com-phone:0379409979', 1, 7, '2024-03-20', NULL),
(16, 'ESBOOK-8021bbe7', 1, '2024-03-01', 'Người book:Minh Kha Kiều-cccd:136-email:16minhkha06@gmail.com-phone:0379409979', 1, 7, '2024-03-22', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `book_room`
--

CREATE TABLE `book_room` (
  `id` int(11) NOT NULL,
  `date_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `created` date DEFAULT NULL,
  `updated` date DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `book_room`
--

INSERT INTO `book_room` (`id`, `date_id`, `book_id`, `created`, `updated`, `price`, `quantity`) VALUES
(1, 1, 1, '2024-03-18', NULL, 4, 4),
(2, 1, 2, '2024-03-18', NULL, 2, 2),
(3, 2, 2, '2024-03-18', NULL, 6, 4),
(11, 11, 2, '2024-03-18', NULL, 6, 4),
(12, 2, 9, '2024-03-20', NULL, 2, 2),
(13, 1, 10, '2024-03-20', NULL, 1, 1),
(14, 1, 11, '2024-03-20', NULL, 1, 1),
(15, 1, 12, '2024-03-20', NULL, 1, 1),
(16, 1, 13, '2024-03-20', NULL, 1, 1),
(17, 1, 14, '2024-03-20', NULL, 1, 1),
(18, 2, 15, '2024-03-20', NULL, 2, 2),
(19, 1, 16, '2024-03-22', NULL, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(250) DEFAULT NULL,
  `created` date DEFAULT NULL,
  `updated` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`, `created`, `updated`) VALUES
(1, '1', '2024-03-18', NULL),
(2, '2', '2024-03-18', NULL),
(3, '3', '2024-03-18', NULL),
(4, '4', '2024-03-18', NULL),
(5, '5', '2024-03-18', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `date_price`
--

CREATE TABLE `date_price` (
  `id` int(11) NOT NULL,
  `quantity_date` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `room_id` int(11) DEFAULT NULL,
  `created` date DEFAULT NULL,
  `updated` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `date_price`
--

INSERT INTO `date_price` (`id`, `quantity_date`, `price`, `room_id`, `created`, `updated`) VALUES
(1, 1, 1, 1, '2024-03-18', NULL),
(2, 1, 2, 2, '2024-03-18', NULL),
(3, 1, 2, 3, '2024-03-18', NULL),
(4, 1, 2, 4, '2024-03-18', NULL),
(5, 1, 4, 5, '2024-03-18', NULL),
(6, 1, 4, 6, '2024-03-18', NULL),
(7, 1, 4, 7, '2024-03-18', NULL),
(8, 1, 8, 8, '2024-03-18', NULL),
(9, 1, 8, 9, '2024-03-18', NULL),
(10, 1, 8, 10, '2024-03-18', NULL),
(11, 1, 8, 11, '2024-03-18', '2024-03-19'),
(12, 1, 8, 12, '2024-03-18', NULL),
(13, 1, 5, 13, '2024-03-18', NULL),
(14, 1, 5, 14, '2024-03-18', NULL),
(15, 1, 5, 15, '2024-03-18', NULL),
(16, 1, 1, 16, '2024-03-18', NULL),
(17, 1, 2, 17, '2024-03-18', NULL),
(18, 1, 4, 18, '2024-03-18', NULL),
(19, 1, 2, 19, '2024-03-18', NULL),
(20, 1, 2, 20, '2024-03-18', NULL),
(21, 1, 2, 21, '2024-03-18', NULL),
(22, 1, 4, 22, '2024-03-18', NULL),
(23, 1, 8, 23, '2024-03-18', NULL),
(24, 1, 2, 24, '2024-03-18', NULL),
(25, 1, 1, 25, '2024-03-18', NULL),
(26, 1, 2, 26, '2024-03-18', NULL),
(27, 1, 4, 27, '2024-03-18', NULL),
(28, 1, 2, 28, '2024-03-18', NULL),
(29, 1, 8, 29, '2024-03-18', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `evaluate`
--

CREATE TABLE `evaluate` (
  `id` int(11) NOT NULL,
  `content` varchar(250) DEFAULT NULL,
  `star` int(11) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `hotel_id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `evaluate`
--

INSERT INTO `evaluate` (`id`, `content`, `star`, `status`, `hotel_id`, `account_id`) VALUES
(1, 'oke', 5, 1, 1, 7),
(2, 'tot', 5, 1, 2, 8),
(3, 'oke', 3, 1, 4, 3),
(4, '', 3, 0, 1, 4),
(5, '', 3, 0, 1, 4),
(6, '', 3, 0, 1, 4),
(7, '', 3, 0, 1, 4),
(8, 'dm', 0, 0, 1, 4);

-- --------------------------------------------------------

--
-- Table structure for table `hotel`
--

CREATE TABLE `hotel` (
  `id` int(11) NOT NULL,
  `name` varchar(250) DEFAULT NULL,
  `content` varchar(250) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `address` varchar(250) DEFAULT NULL,
  `category_id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL,
  `created` date DEFAULT NULL,
  `updated` date DEFAULT NULL,
  `image` varchar(250) DEFAULT NULL,
  `area_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hotel`
--

INSERT INTO `hotel` (`id`, `name`, `content`, `status`, `address`, `category_id`, `account_id`, `created`, `updated`, `image`, `area_id`) VALUES
(1, '22Land Hotel Saigon', 'Nằm ở TP. Hồ Chí Minh, cách Nhà hát Thành phố 5 phút đi bộ, 22Land Hotel Saigon cung cấp chỗ nghỉ có sân hiên, chỗ đậu xe riêng miễn phí, nhà hàng và quầy bar. Các điểm tham quan nổi tiếng gần đó có Nhà thờ Đức Bà, Trung Tâm Thương Mại Diamond Plaz', 1, '25 Lê Thánh Tôn, phường Bến Nghé, Quận 1, Quận 1, TP. Hồ Chí Minh, Việt Nam', 4, 2, '2024-03-18', NULL, '533183392.jpg', 1),
(2, 'Hoa De Nhat Hotel', 'Hoa De Nhat Hotel- Sân Bay Tân Sơn Nhất tọa lạc tại Thành phố Hồ Chí Minh, cách sân bay quốc tế Tân Sơn Nhất 3 km. Khách sạn này có nhà hàng và lễ tân 24 giờ.', 1, '4/1/9 - 4/1/11 Hoang Viet, Quận Tân Bình, TP. Hồ Chí Minh, Việt Nam', 3, 2, '2024-03-18', NULL, '251213937.jpg', 1),
(3, 'Nicecy Boutique Hotel', 'Tọa lạc tại vị trí thuận tiện ở Thành phố Hồ Chí Minh, Nicecy Boutique Hotel nằm cách khu trung tâm thành phố 1 phút đi bộ và Chợ Bến Thành 200 m. Chỗ nghỉ này cung cấp các phòng tiện nghi với WiFi miễn phí.', 1, ' 27-29 Truong Dinh, Quận 1, TP. Hồ Chí Minh, Việt Nam', 3, 2, '2024-03-18', NULL, '407424315.jpg', 1),
(4, 'RAON Bazan Hotel - STAY 24H', 'Nằm ở Đà Lạt, cách Công viên Yersin 19 phút đi bộ, RAON Bazan Hotel - STAY 24H cung cấp chỗ nghỉ có khu vườn, chỗ đậu xe riêng miễn phí và phòng chờ chung. Khách sạn 3 sao này có dịch vụ phòng và dịch vụ tiền sảnh. Chỗ nghỉ cung cấp lễ tân 24/24, dịc', 1, '36 To Hien Thanh , Đà Lạt, Việt Nam', 3, 3, '2024-03-18', NULL, '499873746.jpg', 5),
(5, 'TTR Moonstone Apart Hotel', 'Nằm ở Đà Lạt, cách Sân golf Dalat Palace Golf Club 1.9 km, TTR Moonstone Apart Hotel cung cấp chỗ nghỉ có xe đạp miễn phí, chỗ đậu xe riêng miễn phí, khu vườn và sân hiên. Khách sạn 2 sao này cung cấp nhà hàng. Chỗ nghỉ cung cấp lễ tân 24/24, dịch vụ', 1, '86 Đường Lý Tự Trọng, Đà Lạt, Việt Nam', 3, 3, '2024-03-18', NULL, '506989111.jpg', 5),
(6, 'Rose Valley Hotel Đà Lạt Vietnam', 'Rose Valley Hotel Đà Lạt Vietnam cách Công viên Yersin 3.4 km và Vườn hoa Đà Lạt 4.3 km. Sân bay gần nhất là Sân bay Liên Khương, cách chỗ nghỉ 29 km.', 1, '34 Ngô Thi Sy , Phường 4, Thành Phố Đà Lạt , Tỉnh Lâm Đồng , Việt Nam, Đà Lạt, Việt Nam', 3, 3, '2024-03-18', NULL, '401997912.jpg', 5),
(7, 'Cen Hotel', 'Leo núi và đi xe đạp là hoạt động được ưa chuộng trong khu vực. Ngoài ra, khách sạn 2 sao này có dịch vụ thuê xe đạp.', 1, '74A Vo Thi Sau, Vũng Tàu, Việt Nam', 2, 4, '2024-03-18', NULL, '242666738.jpg', 6),
(8, 'RIO Hotel and Apartment', 'Tọa lạc ở Vũng Tàu, cách Back Beach 14 phút đi bộ, RIO Hotel and Apartment cung cấp chỗ nghỉ có khu vườn, chỗ đậu xe riêng miễn phí, phòng chờ chung và sân hiên. Khách sạn 2 sao này cung cấp bếp chung, dịch vụ phòng và Wi-Fi miễn phí. Chỗ nghỉ này ', 1, '39 Phan Huy Ích, Phường 2, Vũng Tàu, Việt Nam', 2, 4, '2024-03-18', NULL, '242924503.jpg', 6),
(9, 'Annata Beach Hotel', 'Tọa lạc ở Vũng Tàu, cách Back Beach 2 phút đi bộ, Annata Beach Hotel cung cấp chỗ nghỉ có khu vườn, chỗ đậu xe riêng miễn phí, phòng chờ chung và sân hiên. Ngoài Wi-Fi miễn phí, khách sạn 3 sao này còn cung cấp bếp chung và dịch vụ phòng. Chỗ nghỉ ', 1, '165 Thuy Van, Vũng Tàu, Việt Nam', 3, 4, '2024-03-18', NULL, '492735041.jpg', 6),
(10, 'ARINA HOTEL', 'ARINA HOTEL nằm ở Tây Ninh và có sân hiên. Khách sạn 3 sao này có dịch vụ phòng và máy ATM. Chỗ nghỉ cung cấp lễ tân 24/24, dịch vụ đưa đón sân bay, phòng chờ chung và Wi-Fi miễn phí ở toàn bộ chỗ nghỉ.', 1, 'PG2-20 Khu Vincom Shophouse, Khu phố 5, Phường 3, Tây Ninh, Việt Nam', 3, 5, '2024-03-18', NULL, '525021238.jpg', 3),
(11, 'An Gia Hotel Tây Ninh', 'An Gia Hotel Tây Ninh tọa lạc ở Tây Ninh, nhìn ra thành phố và có Wi-Fi miễn phí cùng chỗ đậu xe riêng miễn phí.', 1, 'An gia hotel Tây ninh Chung cư bình phong A36 đường 781, KDC Bình Phong, ấp Bình, xã Thái Bình, huyện Châu Thành,tp Tây Ninh, Tây Ninh, Việt Nam', 3, 5, '2024-03-18', NULL, '497633352.jpg', 3),
(12, 'The Sun Hotel', 'The Sun Hotel tọa lạc ở Tây Ninh và có khu vườn. Chỗ nghỉ cung cấp lễ tân 24/24, dịch vụ đưa đón sân bay, dịch vụ phòng và Wi-Fi miễn phí.', 1, '12 hẻm 6 nguyễn văn rốp Tây Ninh province, Tây Ninh, Việt Nam', 3, 5, '2024-03-18', NULL, '239795561.jpg', 3),
(13, 'Aura FoxGlove', 'Với sân hiên và Wi-Fi miễn phí, Aura FoxGlove tọa lạc ở trung tâm Hà Nội, gần Ga Hà Nội, Văn Miếu - Quốc Tử Giám và Bảo tàng mỹ thuật Việt Nam.', 1, '71 Phố Hai Bà Trưng, Quận Hoàn Kiếm, Hà Nội, Việt Nam', 3, 6, '2024-03-18', NULL, '532173564.jpg', 2),
(14, 'Beryl Signature Hanoi Hotel', 'Khách sạn sẽ cung cấp cho khách các phòng được trang bị điều hòa có bàn làm việc, ấm đun nước, minibar, két an toàn, TV màn hình phẳng và phòng tắm riêng với vòi xịt/chậu rửa vệ sinh. Phòng khách có tủ quần áo.', 1, '24 Phố Quán Sứ, Quận Hoàn Kiếm, Hà Nội, Việt Nam', 3, 6, '2024-03-18', NULL, '530517076.jpg', 2),
(15, 'Golden Sun Hotel', 'Với phòng chờ chung, sân hiên và nhà hàng, Golden Sun Hotel tọa lạc ở trung tâm Hà Nội, cách Nhà thờ Thánh Joseph 7 phút đi bộ. Khách sạn 3 sao này có quầy lễ tân 24 giờ và bàn bán tour. Chỗ nghỉ này cung cấp bếp chung, dịch vụ phòng và dịch vụ thu', 1, '33 Hang Quat Street, Hoan Kiem, Hanoi, Vietnam', 3, 6, '2024-03-18', NULL, '350814582.jpg', 2),
(16, 'aaa', 'Hotel 1', 2, 'Ấp bến chò , xã thạnh đức , huyện gò dầu , tỉnh tây ninh', 4, 3, '2024-03-20', NULL, 'ec8a957cebad4439a4f3808ab209ff58.png', 6);

-- --------------------------------------------------------

--
-- Table structure for table `image`
--

CREATE TABLE `image` (
  `id` int(11) NOT NULL,
  `name` varchar(250) DEFAULT NULL,
  `room_id` int(11) NOT NULL,
  `created` date DEFAULT NULL,
  `updated` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `image`
--

INSERT INTO `image` (`id`, `name`, `room_id`, `created`, `updated`) VALUES
(1, '116389679.jpg', 1, '2024-03-18', NULL),
(2, '170981538.jpg', 1, '2024-03-18', NULL),
(3, '116423796.jpg', 1, '2024-03-18', NULL),
(4, '530509879.jpg', 2, '2024-03-18', NULL),
(5, '530509853.jpg', 2, '2024-03-18', NULL),
(6, '530509849.jpg', 2, '2024-03-18', NULL),
(7, '532166745.jpg', 3, '2024-03-18', NULL),
(8, '532166731.jpg', 3, '2024-03-18', NULL),
(9, '240618397.jpg', 4, '2024-03-18', NULL),
(10, '239795567.jpg', 4, '2024-03-18', NULL),
(11, '499395093.jpg', 5, '2024-03-18', NULL),
(12, '497633364.jpg', 5, '2024-03-18', NULL),
(13, '524710480.jpg', 6, '2024-03-18', NULL),
(14, '524710483.jpg', 6, '2024-03-18', NULL),
(15, '499890170.jpg', 7, '2024-03-18', NULL),
(16, '499890187.jpg', 7, '2024-03-18', NULL),
(17, '485298360.jpg', 8, '2024-03-18', NULL),
(18, '231310818.jpg', 8, '2024-03-18', NULL),
(19, '485298329.jpg', 9, '2024-03-18', NULL),
(20, '231310874.jpg', 9, '2024-03-18', NULL),
(21, '279146821.jpg', 10, '2024-03-18', NULL),
(22, '279146843.jpg', 10, '2024-03-18', NULL),
(23, '406097912.jpg', 11, '2024-03-18', NULL),
(24, '168322185.jpg', 11, '2024-03-18', NULL),
(25, '168322181.jpg', 12, '2024-03-18', NULL),
(26, '168322181.jpg', 12, '2024-03-18', NULL),
(27, '477960691.jpg', 13, '2024-03-18', NULL),
(28, '477959722.jpg', 13, '2024-03-18', NULL),
(29, '477960092.jpg', 14, '2024-03-18', NULL),
(30, '477960698.jpg', 14, '2024-03-18', NULL),
(31, '484852982.jpg', 15, '2024-03-18', NULL),
(32, '484852985.jpg', 15, '2024-03-18', NULL),
(33, '484852972.jpg', 16, '2024-03-18', NULL),
(34, '484851975.jpg', 16, '2024-03-18', NULL),
(35, '484852992.jpg', 17, '2024-03-18', NULL),
(36, '484852979.jpg', 18, '2024-03-18', NULL),
(37, '484852973.jpg', 18, '2024-03-18', NULL),
(38, '484852976.jpg', 19, '2024-03-18', NULL),
(39, '531232689.jpg', 19, '2024-03-18', NULL),
(40, '531232689.jpg', 20, '2024-03-18', NULL),
(41, '531232689.jpg', 21, '2024-03-18', NULL),
(42, '531232689.jpg', 22, '2024-03-18', NULL),
(43, '531232689.jpg', 23, '2024-03-18', NULL),
(44, '531232689.jpg', 24, '2024-03-18', NULL),
(45, '531232689.jpg', 25, '2024-03-18', NULL),
(46, '531232689.jpg', 26, '2024-03-18', NULL),
(47, '531232689.jpg', 27, '2024-03-18', NULL),
(48, '531232689.jpg', 28, '2024-03-18', NULL),
(49, '531232689.jpg', 29, '2024-03-18', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `kindofroom`
--

CREATE TABLE `kindofroom` (
  `id` int(11) NOT NULL,
  `bed` varchar(250) DEFAULT NULL,
  `quality` varchar(250) DEFAULT NULL,
  `balcony` tinyint(1) DEFAULT NULL,
  `orther` varchar(250) DEFAULT NULL,
  `created` date DEFAULT NULL,
  `updated` date DEFAULT NULL,
  `room_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kindofroom`
--

INSERT INTO `kindofroom` (`id`, `bed`, `quality`, `balcony`, `orther`, `created`, `updated`, `room_id`) VALUES
(1, '2', 'Tot', 1, 'Xe đưa đón sân bay,Phòng gia đình', '2024-03-18', NULL, 1),
(2, '3', 'Tot', 1, 'Xe đưa đón sân bay,Phòng gia đình,Phòng không hút thuốc', '2024-03-18', NULL, 2),
(3, '1', 'Tot', 1, 'Xe đưa đón sân bay,Phòng gia đình,Phòng không hút thuốc', '2024-03-18', NULL, 3),
(4, '4', 'Tot', 1, 'Xe đưa đón sân bay,Phòng gia đình,Phòng không hút thuốc', '2024-03-18', NULL, 4),
(5, '1', 'Tot', 1, 'Xe đưa đón sân bay,Phòng gia đình,Phòng không hút thuốc, free wifi', '2024-03-18', NULL, 5),
(6, '2', 'Tot', 1, 'Xe đưa đón sân bay,Phòng gia đình,Phòng không hút thuốc, free wifi', '2024-03-18', NULL, 6),
(7, '3', 'Tot', 1, 'Xe đưa đón sân bay,Phòng gia đình,Phòng không hút thuốc, free wifi', '2024-03-18', NULL, 7),
(8, '1', 'Tot', 0, 'Xe đưa đón sân bay,Phòng gia đình,Phòng không hút thuốc, free wifi', '2024-03-18', NULL, 8),
(9, '2', 'Tot', 0, 'Xe đưa đón sân bay,Phòng gia đình,Phòng không hút thuốc, free wifi', '2024-03-18', NULL, 9),
(10, '3', 'Tot', 0, 'Xe đưa đón sân bay,Phòng gia đình,Phòng không hút thuốc, free wifi', '2024-03-18', NULL, 10),
(11, '1', 'Tot', 0, 'Xe đưa đón sân bay,Phòng gia đình,Phòng không hút thuốc, free wifi', '2024-03-18', '2024-03-19', 11),
(12, '2', 'Tot', 0, 'Xe đưa đón sân bay,Phòng gia đình,Phòng không hút thuốc, free wifi', '2024-03-18', NULL, 12),
(13, '2', 'Tot', 0, 'Xe đưa đón sân bay,Phòng gia đình,Phòng không hút thuốc, free wifi', '2024-03-18', NULL, 13),
(14, '3', 'Tot', 0, 'Xe đưa đón sân bay,Phòng gia đình,Phòng không hút thuốc, free wifi', '2024-03-18', NULL, 14),
(15, '1', 'Tot', 0, 'Xe đưa đón sân bay,Phòng gia đình,Phòng không hút thuốc, free wifi', '2024-03-18', NULL, 15),
(16, '2', 'Tot', 1, 'Xe đưa đón sân bay,Phòng gia đình,Phòng không hút thuốc, free wifi', '2024-03-18', NULL, 16),
(17, '1', 'Tot', 1, 'Xe đưa đón sân bay,Phòng gia đình,Phòng không hút thuốc, free wifi', '2024-03-18', NULL, 17),
(18, '2', 'Tot', 1, 'Xe đưa đón sân bay,Phòng gia đình,Phòng không hút thuốc, free wifi', '2024-03-18', NULL, 18),
(19, '1', 'Tot', 1, 'Xe đưa đón sân bay,Phòng gia đình,Phòng không hút thuốc, free wifi', '2024-03-18', NULL, 19),
(20, '2', 'Tot', 0, 'Xe đưa đón sân bay,Phòng gia đình,Phòng không hút thuốc, free wifi', '2024-03-18', NULL, 20),
(21, '2', 'Tot', 0, 'Xe đưa đón sân bay,Phòng gia đình,Phòng không hút thuốc, free wifi', '2024-03-18', NULL, 21),
(22, '2', 'Tot', 1, 'Xe đưa đón sân bay,Phòng gia đình,Phòng không hút thuốc, free wifi', '2024-03-18', NULL, 22),
(23, '1', 'Tot', 1, 'Xe đưa đón sân bay,Phòng gia đình,Phòng không hút thuốc, free wifi', '2024-03-18', NULL, 23),
(24, '2', 'Tot', 1, 'Xe đưa đón sân bay,Phòng gia đình,Phòng không hút thuốc, free wifi', '2024-03-18', NULL, 24),
(25, '2', 'Tot', 1, 'Xe đưa đón sân bay,Phòng gia đình,Phòng không hút thuốc, free wifi', '2024-03-18', NULL, 25),
(26, '2', 'Tot', 1, 'Xe đưa đón sân bay,Phòng gia đình,Phòng không hút thuốc, free wifi', '2024-03-18', NULL, 26),
(27, '2', 'Tot', 1, 'Xe đưa đón sân bay,Phòng gia đình,Phòng không hút thuốc, free wifi', '2024-03-18', NULL, 27),
(28, '2', 'Tot', 1, 'Xe đưa đón sân bay,Phòng gia đình,Phòng không hút thuốc, free wifi', '2024-03-18', NULL, 28),
(29, '2', 'Tot', 1, 'Xe đưa đón sân bay,Phòng gia đình,Phòng không hút thuốc, free wifi', '2024-03-18', NULL, 29);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(250) DEFAULT NULL,
  `created` date DEFAULT NULL,
  `updated` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `name`, `created`, `updated`) VALUES
(1, 'ROLE_ADMIN', '2024-02-28', '2024-02-28'),
(2, 'ROLE_HOTEL', '2024-02-28', '2024-02-28'),
(3, 'ROLE_USER', '2024-02-28', '2024-02-29');

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `id` int(11) NOT NULL,
  `content` varchar(250) DEFAULT NULL,
  `fixed_quantity` int(11) DEFAULT NULL,
  `hotel_id` int(11) NOT NULL,
  `created` date DEFAULT NULL,
  `updated` date DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`id`, `content`, `fixed_quantity`, `hotel_id`, `created`, `updated`, `status`) VALUES
(1, '01->10', 10, 1, '2024-03-18', NULL, 1),
(2, '11->20', 10, 1, '2024-03-18', NULL, 1),
(3, '21->30', 10, 1, '2024-03-18', NULL, 1),
(4, '31->40', 10, 1, '2024-03-18', NULL, 1),
(5, '01->10', 10, 2, '2024-03-18', NULL, 1),
(6, '11->20', 10, 2, '2024-03-18', NULL, 1),
(7, '21->30', 10, 2, '2024-03-18', NULL, 1),
(8, '01->10', 10, 3, '2024-03-18', NULL, 1),
(9, '11->20', 10, 3, '2024-03-18', NULL, 1),
(10, '21->30', 10, 3, '2024-03-18', NULL, 1),
(11, '<p>01-&gt;10</p>', 10, 4, '2024-03-18', '2024-03-19', NULL),
(12, '11->20', 10, 4, '2024-03-18', NULL, 1),
(13, '01->10', 10, 5, '2024-03-18', NULL, 1),
(14, '11->20', 10, 5, '2024-03-18', NULL, 1),
(15, '01->10', 10, 6, '2024-03-18', NULL, 1),
(16, '11->20', 10, 6, '2024-03-18', NULL, 1),
(17, '01->10', 10, 7, '2024-03-18', NULL, 1),
(18, '11->20', 10, 7, '2024-03-18', NULL, 1),
(19, '01->10', 10, 8, '2024-03-18', NULL, 1),
(20, '11->20', 10, 8, '2024-03-18', NULL, 1),
(21, '01->10', 10, 9, '2024-03-18', NULL, 1),
(22, '11->20', 10, 9, '2024-03-18', NULL, 1),
(23, '01->10', 10, 10, '2024-03-18', NULL, 1),
(24, '11->20', 10, 10, '2024-03-18', NULL, 1),
(25, '01->10', 10, 11, '2024-03-18', NULL, 1),
(26, '01->10', 10, 12, '2024-03-18', NULL, 1),
(27, '01->10', 10, 13, '2024-03-18', NULL, 1),
(28, '01->10', 10, 14, '2024-03-18', NULL, 1),
(29, '01->10', 10, 15, '2024-03-18', NULL, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`),
  ADD KEY `role_id` (`role_id`);

--
-- Indexes for table `area`
--
ALTER TABLE `area`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id`),
  ADD KEY `account_id` (`account_id`);

--
-- Indexes for table `book_room`
--
ALTER TABLE `book_room`
  ADD PRIMARY KEY (`id`),
  ADD KEY `book_id` (`book_id`),
  ADD KEY `date_id` (`date_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `date_price`
--
ALTER TABLE `date_price`
  ADD PRIMARY KEY (`id`),
  ADD KEY `room_id` (`room_id`);

--
-- Indexes for table `evaluate`
--
ALTER TABLE `evaluate`
  ADD PRIMARY KEY (`id`),
  ADD KEY `account_id` (`account_id`),
  ADD KEY `hotel_id` (`hotel_id`);

--
-- Indexes for table `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`id`),
  ADD KEY `account_id` (`account_id`),
  ADD KEY `category_id` (`category_id`),
  ADD KEY `area_id` (`area_id`);

--
-- Indexes for table `image`
--
ALTER TABLE `image`
  ADD PRIMARY KEY (`id`),
  ADD KEY `room_id` (`room_id`);

--
-- Indexes for table `kindofroom`
--
ALTER TABLE `kindofroom`
  ADD PRIMARY KEY (`id`),
  ADD KEY `room_id` (`room_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`id`),
  ADD KEY `hotel_id` (`hotel_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `area`
--
ALTER TABLE `area`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `book`
--
ALTER TABLE `book`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `book_room`
--
ALTER TABLE `book_room`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `date_price`
--
ALTER TABLE `date_price`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `evaluate`
--
ALTER TABLE `evaluate`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `hotel`
--
ALTER TABLE `hotel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `image`
--
ALTER TABLE `image`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT for table `kindofroom`
--
ALTER TABLE `kindofroom`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `room`
--
ALTER TABLE `room`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `account_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

--
-- Constraints for table `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `book_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`);

--
-- Constraints for table `book_room`
--
ALTER TABLE `book_room`
  ADD CONSTRAINT `book_room_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  ADD CONSTRAINT `book_room_ibfk_2` FOREIGN KEY (`date_id`) REFERENCES `date_price` (`id`);

--
-- Constraints for table `date_price`
--
ALTER TABLE `date_price`
  ADD CONSTRAINT `date_price_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`);

--
-- Constraints for table `evaluate`
--
ALTER TABLE `evaluate`
  ADD CONSTRAINT `evaluate_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `evaluate_ibfk_2` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`);

--
-- Constraints for table `hotel`
--
ALTER TABLE `hotel`
  ADD CONSTRAINT `hotel_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `hotel_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  ADD CONSTRAINT `hotel_ibfk_3` FOREIGN KEY (`area_id`) REFERENCES `area` (`id`);

--
-- Constraints for table `image`
--
ALTER TABLE `image`
  ADD CONSTRAINT `image_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`);

--
-- Constraints for table `kindofroom`
--
ALTER TABLE `kindofroom`
  ADD CONSTRAINT `kindofroom_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`);

--
-- Constraints for table `room`
--
ALTER TABLE `room`
  ADD CONSTRAINT `room_ibfk_1` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 18, 2023 lúc 06:16 AM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlybangiay`
--

DELIMITER $$
--
-- Thủ tục
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_CapNhatSoLuongDaBan` (IN `in_productID` INT, IN `in_soLuongBanThem` INT)   BEGIN
	UPDATE SoLuongDaBan SET soLuongDaBan = soLuongDaBan + in_soLuongBanThem WHERE productID = in_productID;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_CapNhatTongBanHang` (IN `in_sell_ID` INT, IN `in_banHangThem` FLOAT)   BEGIN
	UPDATE TongChiTieuBanHang SET TongBanHang = TongBanHang + in_banHangThem WHERE userID = in_sell_ID;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_CapNhatTongChiTieu` (IN `in_userID` INT, IN `in_chiTieuThem` FLOAT)   BEGIN
	UPDATE TongChiTieuBanHang SET TongChiTieu = TongChiTieu + in_chiTieuThem WHERE userID = in_userID;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account`
--

CREATE TABLE `account` (
  `uID` int(11) NOT NULL,
  `user` char(10) DEFAULT NULL,
  `pass` char(10) DEFAULT NULL,
  `isSell` bit(1) DEFAULT NULL,
  `isAdmin` bit(1) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `account`
--

INSERT INTO `account` (`uID`, `user`, `pass`, `isSell`, `isAdmin`, `email`) VALUES
(1, 'minhnhaque', '123', b'1', b'1', 'minhnhaque@gmail.com'),
(2, 'linhle', '123456', b'0', b'0', 'linhle@gmail.com'),
(3, 'kimanh', '123456', b'0', b'0', 'kimanh@gmail.com'),
(1019, 'nhungle', '123456', b'1', b'1', 'nhungle@gmail.com'),
(1020, 'minh259', '123', b'1', b'1', 'minh258@gmail.com'),
(1021, 'minh23', '123', b'0', b'0', 'nak25092003@gmail.com'),
(1023, 'minh2003', '123', b'0', b'0', 'nak25092003@gmail.com'),
(1024, 'kimhoa', '123', b'0', b'0', '1050080049@sv.hcmunre.edu.vn');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cart`
--

CREATE TABLE `cart` (
  `accountID` int(11) NOT NULL,
  `productID` int(11) NOT NULL,
  `amount` int(11) DEFAULT NULL,
  `maCart` int(11) NOT NULL,
  `size` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `cart`
--

INSERT INTO `cart` (`accountID`, `productID`, `amount`, `maCart`, `size`) VALUES
(1, 46, 1, 10, 'medium'),
(1, 39, 2, 11, 'medium');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `cid` int(11) NOT NULL,
  `cname` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`cid`, `cname`) VALUES
(1, 'GIAY ADIDAS '),
(2, 'GIAY NIKE'),
(3, 'GIAY MLB'),
(4, 'GIAY CONVERSE');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `invoice`
--

CREATE TABLE `invoice` (
  `maHD` int(11) NOT NULL,
  `accountID` int(11) DEFAULT NULL,
  `tongGia` float DEFAULT NULL,
  `ngayXuat` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `invoice`
--

INSERT INTO `invoice` (`maHD`, `accountID`, `tongGia`, `ngayXuat`) VALUES
(1, 1021, 1059.3, '2023-12-03 00:00:00'),
(2, 1021, 1059.3, '2023-12-03 00:00:00'),
(3, 1021, 880, '2023-12-03 00:00:00'),
(4, 1021, 217.8, '2023-12-03 00:00:00'),
(5, 1023, 1259.5, '2023-12-03 00:00:00'),
(6, 1024, 2398, '2023-12-04 00:00:00'),
(7, 1021, 930.6, '2023-12-05 00:00:00'),
(8, 1, 1721.5, '2023-12-05 00:00:00'),
(9, 1, 2601.5, '2023-12-06 00:00:00'),
(10, 1019, 3407.8, '2023-12-07 00:00:00'),
(11, 1019, 3407.8, '2023-12-07 00:00:00'),
(15, 1, 1721.5, '2023-12-16 00:00:00');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `image` varchar(500) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `title` varchar(500) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `cateID` int(11) NOT NULL,
  `sell_ID` int(11) NOT NULL,
  `model` varchar(50) DEFAULT NULL,
  `color` varchar(50) DEFAULT NULL,
  `delivery` varchar(50) DEFAULT NULL,
  `image2` varchar(500) DEFAULT NULL,
  `image3` varchar(500) DEFAULT NULL,
  `image4` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`id`, `name`, `image`, `price`, `title`, `description`, `cateID`, `sell_ID`, `model`, `color`, `delivery`, `image2`, `image3`, `image4`) VALUES
(19, 'Giày Nike Air Max SC Nam- Đen Trắng', 'image1/1.jpg', 190, 'Giày Nike Air Max SC Nam- Đen Trắng', 'Đôi giày Nike Air Max SC Nam - Đen Trắng thể hiện sự kết hợp tinh tế giữa da tổng hợp và vải, tạo điểm nhấn với hai tông màu đen và trắng. Thiết kế êm ái, chất liệu chắc chắn, và đế Air Max độc đáo giúp mang lại sự thoải mái cho mọi hoạt động. Logo Nike nổi bật và phù hợp cho cả thể thao và phong cách hàng ngày.', 2, 1, 'G68', 'Black', 'Ho Chi Minh', 'image1/2.jpg', 'image1/3.jpg', 'image1/4.jpg'),
(20, 'Giày Nike Zoom Structure 24 White Red Swoosh', 'image2/1.jpeg', 500, 'Giày Nike Zoom Structure 24 White Red Swoosh', 'Đôi giày Nike Zoom Structure 24 White Red Swoosh kết hợp màu trắng và đỏ rực rỡ, tạo điểm nhấn sáng tạo trên thiết kế hiện đại. Với công nghệ Zoom, đế giày linh hoạt và êm ái, giúp cung cấp sự hỗ trợ tối ưu cho bước chạy. Chất liệu cao cấp và đường may tỉ mỉ tạo nên đôi giày bền bỉ và thoải mái. Logo Swoosh của Nike nổi bật, thể hiện phong cách độc đáo và phù hợp cho cả hoạt động thể thao và sử dụng hàng ngày.', 2, 1, 'G68', 'White', 'Hà Nội', 'image2/2.jpeg', 'image2/3.jpeg', 'image2/4.jpeg'),
(21, 'Giày Nike Air Max AP Nam - Đen', 'image3/1.jpg', 690, 'Giày Nike Air Max AP Nam - Đen', 'Đôi giày Nike Air Max AP Nam - Đen là sự kết hợp tinh tế giữa thiết kế hiện đại và sự thoải mái. Với màu sắc đen tối, đôi giày này mang đến vẻ đẹp cổ điển và đẳng cấp. Chất liệu vải và da tổng hợp kết hợp với đế giữa Air Max, tạo nên sự êm ái và linh hoạt khi di chuyển. Đường may chắc chắn và logo Nike ấn tượng tạo điểm nhấn thời trang. Phù hợp cho cả các hoạt động thể thao và sử dụng hàng ngày, đôi giày này thể hiện phong cách vừa vặn và tiện ích cho mọi hoàn cảnh.', 2, 1, 'G68', 'Black', 'Long An', 'image3/2.jpg', 'image3/3.jpg', 'image3/4.jpg'),
(24, 'Giày Nike Air Zoom Structure 24 Nam - Trắng', 'image4/1.jpg', 390, 'Giày Nike Air Zoom Structure 24 Nam - Trắng', 'Giày Nike Air Zoom Structure 24 Nam - Trắng mang đậm dấu ấn của sự hiện đại và phong cách. Với tông màu trắng tinh khôi, đây là sự kết hợp tuyệt vời giữa thiết kế tinh tế và chất liệu đa dạng như da tổng hợp và vải, tạo nên sự thoải mái và thoáng khí. Đế giày được trang bị công nghệ Zoom mang lại sự linh hoạt và hỗ trợ tối đa cho bàn chân. Với đường may chắc chắn và logo Nike thể hiện rõ nét trên thân giày, đôi giày này không chỉ phục vụ tốt cho các hoạt động thể thao mà còn là điểm nhấn thời trang hoàn hảo cho mọi hoàn cảnh sử dụng hàng ngày.', 2, 1, 'G68', 'White', 'Long An', 'image4/2.jpg', 'image4/3.jpg', 'image4/4.jpg'),
(28, 'Giày Adidas Superstar Millencon \'Cloud White\' ', 'image5/1.jpeg', 800, 'Giày Adidas Superstar Millencon \'Cloud White\' ', 'Đôi giày Adidas Superstar Millencon \'Cloud White\' là phiên bản nâng cấp của dòng Superstar kinh điển. Với gam màu \'Cloud White\' thanh lịch, đôi giày này thể hiện sự tinh tế trong thiết kế và chất liệu. Vamp da bò và đế cao su mang đến sự bền bỉ và thoải mái, trong khi vẫn giữ được vẻ ngoài thời trang và hiện đại. Đặc trưng với ngôi sao ba sọc phản quang trên bên ngoài, đôi giày này là biểu tượng với phong cách đa dụng, phù hợp cho cả hoạt động thể thao và phong cách hàng ngày.', 1, 1, 'G68', 'White', 'Long An', 'image5/2.jpeg', 'image5/3.jpeg', 'image5/4.jpeg'),
(29, 'Giày Puma Smash V2 L Nam Xám', 'image6/1.jpg', 590, 'Giày Puma Smash V2 L Nam Xám', 'Đôi giày Puma Smash V2 L Nam màu xám là sự kết hợp hoàn hảo giữa phong cách và tiện ích. Với thiết kế đơn giản nhưng sang trọng, chúng phản ánh sự thanh lịch với màu sắc xám truyền thống. Chất liệu da tổng hợp chắc chắn tạo nên sự bền bỉ và dễ dàng vệ sinh. Đế giày cao su mang lại độ bám và thoải mái cho mọi hoạt động. Đây là lựa chọn hoàn hảo cho phong cách thời trang đa dụng, phù hợp cho cả các hoạt động thể thao nhẹ và sử dụng hàng ngày.', 2, 1, 'G68', 'Gray', 'Long An', 'image6/2.jpg', 'image6/3.jpg', 'image6/4.jpg'),
(31, 'Giày Nike Air Zoom Structure 23 Nam - Xám', 'image7/1.jpg', 90, 'Giày Nike Air Zoom Structure 23 Nam - Xám', '\r\nĐôi giày Nike Air Zoom Structure 23 Nam màu xám là sự kết hợp tinh tế giữa công nghệ và thiết kế đẳng cấp. Với gam màu xám tối, đôi giày này mang đến vẻ ngoài hiện đại và thể thao. Chất liệu vải và da tổng hợp tạo cảm giác thoải mái và linh hoạt cho bàn chân. Công nghệ Air Zoom trong đế giữa mang lại sự ổn định và hỗ trợ linh hoạt cho bước chạy. Với đường may chắc chắn và logo Nike nổi bật, đôi giày này không chỉ phục vụ tốt cho hoạt động thể thao mà còn là điểm nhấn phong cách trong sự thoải mái hàng ngày.', 2, 1, 'G68', 'Gray', 'Long An', 'image7/2.jpg', 'image7/3.jpg', 'image7/4.jpg'),
(32, 'Giày Nike Air Max AP Nam - Xám Xanh', 'image8/1.jpg', 690, 'Giày Nike Air Max AP Nam - Xám Xanh', 'Đôi giày Nike Air Max AP Nam với tông màu xám xanh tạo nên sự phá cách và tinh tế. Sử dụng chất liệu da tổng hợp và vải cao cấp, giày mang lại cảm giác êm ái và thoải mái cho đôi chân. Thiết kế đường may tỉ mỉ và công nghệ Air Max trong đế giữa giúp giảm chấn tốt, tăng cường độ êm ái và sự linh hoạt khi di chuyển. Với màu xám xanh tinh tế, đây không chỉ là một sản phẩm thể thao mà còn là biểu tượng phong cách, phù hợp cho nhiều hoạt động và dễ dàng phối hợp với trang phục hàng ngày.', 2, 1, 'G68', 'Gray', 'Long An', 'image8/2.jpg', 'image8/3.jpg', 'image8/4.jpg'),
(38, 'Giày MLB Chunky Liner Basic \'Boston Red Sox -Yellow ', 'image9/1.jpeg', 100, 'Giày MLB Chunky Liner Basic \'Boston Red Sox -Yellow ', '......', 3, 1, 'G39', 'White', 'Ho Chi Minh', 'image9/2.jpeg', 'image9/3.jpeg', 'image9/4.jpeg'),
(39, 'Giày Converse Chuck Taylor All Star 1970s Archive Paint Splatte [ 170804c ]', 'image10/1.jpeg', 399, 'Giày Converse Chuck Taylor All Star 1970s Archive Paint Splatte [ 170804c ]', 'Đôi giày Converse Chuck Taylor All Star 1970s Archive Paint Splatter (mã sản phẩm 170804c) là một biểu tượng của phong cách cá nhân và sự sáng tạo. Với thiết kế độc đáo, với các vết sơn nhiều màu được phân bổ ngẫu nhiên trên phần thân giày, đôi giày này tạo nên điểm nhấn nghệ thuật rất riêng. Chất liệu chất lượng cao và đế cao su cổ điển đảm bảo độ bền và thoải mái khi sử dụng hàng ngày. Sự kết hợp giữa vẻ đẹp cổ điển và phong cách hiện đại làm cho đôi giày này trở thành một lựa chọn tuyệt vời để thể hiện phong cách riêng của bạn và làm mới bộ sưu tập giày của bạn.\r\n\r\n\r\n\r\n\r\n\r\n', 4, 1, 'G89', 'Yellow', 'Hà Nội', 'image10/2.jpeg', 'image10/3.jpeg', 'image10/4.jpeg'),
(40, 'GIÀY ADIDAS ADVANTAGE BASE NAM - TRẮNG XANH', 'image11/1.jpg', 140, 'GIÀY ADIDAS ADVANTAGE BASE NAM - TRẮNG XANH', 'Đôi giày Adidas Advantage Base Nam với tông màu trắng và xanh là sự kết hợp tinh tế giữa phong cách và thoải mái. Thiết kế đơn giản với màu sắc trang nhã, với phần upper làm từ chất liệu da tổng hợp cao cấp và đế giày cao su mang lại độ bền và ổn định. Đôi giày này không chỉ thể hiện phong cách thời trang mà còn cung cấp sự thoải mái và linh hoạt cho đôi chân, phù hợp cho nhiều hoạt động hàng ngày và là điểm nhấn tinh tế trong bộ sưu tập giày của bạn.', 1, 1, 'G76', 'White', 'Nha Trang', 'image11/2.jpg', 'image11/3.webp', 'image11/4.webp'),
(42, 'GIÀY ADIDAS SUPER COURT NỮ TRẮNG FULL', 'image12/1.webp', 190, 'GIÀY ADIDAS SUPER COURT NỮ TRẮNG FULL', 'Đôi giày Adidas Super Court Nữ trắng full là sự kết hợp tinh tế giữa phong cách và thoải mái. Với màu trắng tinh khôi, đôi giày này thường có thiết kế đơn giản nhưng sang trọng. Chất liệu chủ yếu từ da tổng hợp hoặc da nâu, phần upper thường có các đường may tỉ mỉ và logo Adidas nổi bật. Đế giày cao su mang lại độ bền và độ bám tốt, phù hợp cho cả các hoạt động thể thao nhẹ và sử dụng hàng ngày. Đôi giày này thường được lựa chọn bởi vẻ đẹp cổ điển, dễ dàng kết hợp với nhiều trang phục khác nhau và là một phần không thể thiếu trong tủ giày của phái đẹp.', 1, 1, 'G76', 'White', 'Nha Trang', 'image12/2.jpg', 'image12/3.jpg', 'image12/4.jpg'),
(43, 'Giày Converse Chuck Taylor All Star Black [101010 ]', 'image13/1.jpeg', 255, 'Giày Converse Chuck Taylor All Star Black [101010 ]', '.......', 4, 1, 'G89', 'Black', 'Nha Trang', 'image13/2.jpeg', 'image13/3.jpeg', 'image13/4.jpeg'),
(44, 'Converse Chuck Taylor All Star 70s High Sunflower Yellow [162054C]', 'image14/1.jpeg', 198, 'Converse Chuck Taylor All Star 70s High Sunflower Yellow [162054C]', 'Converse Chuck Taylor All Star Crate Knit đã trở lại để mang sự cổ điển đến gần hơn với một tương lai không lãng phí. Kiểu dáng Chuck Taylor với phần đế cao su được làm từ Công nghệ Nike Grind kết hợp với xốp Nike và cao su tái chế. Ngoài ra, upper được làm từ chất vải Polyester với công nghệ dệt kỹ thuật tiên tiến, sợi vải được dệt từ 75% poly tái chế và 25% nilon spandex (loại sợi nhân tạo có khả năng co giãn 4 chiều). ', 4, 1, 'G87', 'Yellow', 'Nha Trang', 'image14/2.jpeg', 'image14/3.jpeg', 'image14/4.jpeg'),
(45, 'Giày New Blance 530 White Silver [GR530AD]', 'image15/1.jpeg', 800, 'Giày New Blance 530 White Silver [GR530AD]', '......', 3, 1, 'G68', 'White', 'Nha Trang', 'image15/2.jpeg', 'image15/3.jpeg', 'image15/4.jpeg'),
(46, 'GIÀY MLB BIGBALL CHUNKY A NEW YORK YANKEES 3ASHC101N-50BKS', 'image16/1.jpeg', 767, 'GIÀY MLB BIGBALL CHUNKY A NEW YORK YANKEES 3ASHC101N-50BKS', '.......', 3, 1, 'G45', 'Black', 'Nha Trang', 'image16/2.jpeg', 'image16/3.jpeg', 'image16/4.jpeg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `review`
--

CREATE TABLE `review` (
  `accountID` int(11) DEFAULT NULL,
  `productID` int(11) DEFAULT NULL,
  `contentReview` varchar(500) DEFAULT NULL,
  `dateReview` date DEFAULT NULL,
  `maReview` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `review`
--

INSERT INTO `review` (`accountID`, `productID`, `contentReview`, `dateReview`, `maReview`) VALUES
(2, 19, 'đã mua lần thứ 2', '2023-12-01', 14),
(2, 20, 'quá bền cảm ơn', '2023-12-02', 15),
(2, 21, 'giày chất liệu tốt', '2023-12-02', 16),
(1021, 40, 'giày xịn', '2023-11-29', 17),
(3, 29, 'giày đẹp hơn trên ảnh', '2023-11-30', 19),
(3, 31, 'cho shop 5 sao', '2023-12-02', 20),
(1023, 46, 'giày đẹp, giá tốt', '2023-12-07', 21),
(1019, 46, 'giày đẹp, giá thành hợp lý', '2023-12-07', 22),
(1019, 20, 'giày đẹp, giá tốt', '2023-12-07', 23);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `soluongdaban`
--

CREATE TABLE `soluongdaban` (
  `productID` int(11) DEFAULT NULL,
  `soLuongDaBan` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `soluongdaban`
--

INSERT INTO `soluongdaban` (`productID`, `soLuongDaBan`) VALUES
(31, 5),
(28, 4),
(19, 25),
(20, 11),
(21, 7),
(24, 10),
(46, 61),
(45, 17),
(44, 14),
(32, 2),
(39, 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `supplier`
--

CREATE TABLE `supplier` (
  `idSupplier` int(11) NOT NULL,
  `nameSupplier` varchar(100) DEFAULT NULL,
  `phoneSupplier` varchar(50) DEFAULT NULL,
  `emailSupplier` varchar(200) DEFAULT NULL,
  `addressSupplier` varchar(200) DEFAULT NULL,
  `cateID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `supplier`
--

INSERT INTO `supplier` (`idSupplier`, `nameSupplier`, `phoneSupplier`, `emailSupplier`, `addressSupplier`, `cateID`) VALUES
(1, 'ADDIDAS', '0999999999', 'ctyADDIDAS@gmail.com', 'HCM', 1),
(2, 'NIKE', '0988888888', 'ctyNIKE@gmail.com', 'HN', 2),
(3, 'MLB', '0912345678', 'ctyMLB@gmail.com', 'HCM', 3),
(4, 'CONVERSE', '0987654310', 'ctyCONVERSE@gmail.com', 'HN', 4);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tongchitieubanhang`
--

CREATE TABLE `tongchitieubanhang` (
  `userID` int(11) DEFAULT NULL,
  `TongChiTieu` float DEFAULT NULL,
  `TongBanHang` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `tongchitieubanhang`
--

INSERT INTO `tongchitieubanhang` (`userID`, `TongChiTieu`, `TongBanHang`) VALUES
(1, 6044.5, 15862),
(1023, 1259.5, 0),
(1024, 2398, 0),
(1021, 930.6, 0),
(1019, 6815.6, 0);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`uID`);

--
-- Chỉ mục cho bảng `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`maCart`),
  ADD KEY `FK_Cart_Account` (`accountID`),
  ADD KEY `FK_Cart_Product` (`productID`);

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`cid`);

--
-- Chỉ mục cho bảng `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`maHD`),
  ADD KEY `FK_Invoice_Account` (`accountID`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_Product_Account` (`sell_ID`),
  ADD KEY `FK_Product_Category` (`cateID`);

--
-- Chỉ mục cho bảng `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`maReview`),
  ADD KEY `FK_Review_Account` (`accountID`),
  ADD KEY `FK_Review_Product` (`productID`);

--
-- Chỉ mục cho bảng `soluongdaban`
--
ALTER TABLE `soluongdaban`
  ADD KEY `FK_SoLuongDaBan_Product` (`productID`);

--
-- Chỉ mục cho bảng `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`idSupplier`),
  ADD KEY `FK_Supplier_Category` (`cateID`);

--
-- Chỉ mục cho bảng `tongchitieubanhang`
--
ALTER TABLE `tongchitieubanhang`
  ADD KEY `FK_TongChiTieuBanHang_Account` (`userID`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `account`
--
ALTER TABLE `account`
  MODIFY `uID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1028;

--
-- AUTO_INCREMENT cho bảng `cart`
--
ALTER TABLE `cart`
  MODIFY `maCart` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT cho bảng `invoice`
--
ALTER TABLE `invoice`
  MODIFY `maHD` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT cho bảng `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT cho bảng `review`
--
ALTER TABLE `review`
  MODIFY `maReview` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT cho bảng `supplier`
--
ALTER TABLE `supplier`
  MODIFY `idSupplier` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `FK_Cart_Account` FOREIGN KEY (`accountID`) REFERENCES `account` (`uID`),
  ADD CONSTRAINT `FK_Cart_Product` FOREIGN KEY (`productID`) REFERENCES `product` (`id`);

--
-- Các ràng buộc cho bảng `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `FK_Invoice_Account` FOREIGN KEY (`accountID`) REFERENCES `account` (`uID`);

--
-- Các ràng buộc cho bảng `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK_Product_Account` FOREIGN KEY (`sell_ID`) REFERENCES `account` (`uID`),
  ADD CONSTRAINT `FK_Product_Category` FOREIGN KEY (`cateID`) REFERENCES `category` (`cid`);

--
-- Các ràng buộc cho bảng `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `FK_Review_Account` FOREIGN KEY (`accountID`) REFERENCES `account` (`uID`),
  ADD CONSTRAINT `FK_Review_Product` FOREIGN KEY (`productID`) REFERENCES `product` (`id`);

--
-- Các ràng buộc cho bảng `soluongdaban`
--
ALTER TABLE `soluongdaban`
  ADD CONSTRAINT `FK_SoLuongDaBan_Product` FOREIGN KEY (`productID`) REFERENCES `product` (`id`);

--
-- Các ràng buộc cho bảng `supplier`
--
ALTER TABLE `supplier`
  ADD CONSTRAINT `FK_Supplier_Category` FOREIGN KEY (`cateID`) REFERENCES `category` (`cid`);

--
-- Các ràng buộc cho bảng `tongchitieubanhang`
--
ALTER TABLE `tongchitieubanhang`
  ADD CONSTRAINT `FK_TongChiTieuBanHang_Account` FOREIGN KEY (`userID`) REFERENCES `account` (`uID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 13, 2022 at 05:51 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `inixtraining`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_detail_kelas`
--

CREATE TABLE `tb_detail_kelas` (
  `id_detail_kls` int(10) NOT NULL,
  `id_kls` int(10) NOT NULL,
  `id_pst` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_detail_kelas`
--

INSERT INTO `tb_detail_kelas` (`id_detail_kls`, `id_kls`, `id_pst`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 1, 4),
(5, 1, 5),
(6, 1, 6),
(7, 1, 7),
(8, 1, 8),
(9, 1, 9),
(10, 1, 10),
(11, 1, 11),
(12, 1, 12),
(13, 1, 13),
(14, 1, 14),
(15, 1, 15),
(18, 2, 1),
(19, 2, 2),
(20, 2, 3),
(21, 2, 4),
(22, 2, 5),
(23, 2, 6),
(24, 2, 7),
(25, 2, 8),
(26, 2, 9),
(27, 2, 10),
(28, 2, 11),
(29, 2, 12),
(30, 2, 13),
(31, 2, 14),
(32, 2, 15),
(33, 3, 1),
(34, 3, 2),
(35, 3, 3),
(36, 3, 4),
(37, 3, 5),
(38, 3, 6),
(39, 3, 7),
(40, 3, 8),
(41, 3, 9),
(42, 3, 10),
(43, 3, 11),
(44, 3, 12),
(45, 3, 13),
(46, 3, 14),
(47, 3, 15),
(48, 4, 1),
(49, 4, 2),
(50, 4, 3),
(51, 4, 4),
(52, 4, 5),
(53, 4, 6),
(54, 4, 7),
(55, 4, 8),
(56, 4, 9),
(57, 4, 10),
(58, 4, 11),
(59, 4, 12),
(60, 4, 13),
(61, 4, 14),
(62, 4, 15),
(63, 5, 1),
(64, 5, 2),
(65, 5, 3),
(66, 5, 4),
(67, 5, 5),
(68, 5, 6),
(69, 5, 7),
(70, 5, 8),
(71, 5, 9),
(72, 5, 10),
(73, 5, 11),
(74, 5, 12),
(75, 5, 13),
(76, 5, 14),
(77, 5, 15),
(78, 6, 1),
(79, 6, 2),
(80, 6, 3),
(81, 6, 4),
(82, 6, 5),
(83, 6, 6),
(84, 6, 7),
(85, 6, 8),
(86, 6, 9),
(87, 6, 10),
(88, 6, 11),
(89, 6, 12),
(90, 6, 13),
(91, 6, 14),
(92, 6, 15),
(93, 7, 1),
(94, 7, 2),
(95, 7, 3),
(96, 7, 4),
(97, 7, 5),
(98, 7, 6),
(99, 7, 7),
(100, 7, 8),
(101, 7, 9),
(102, 7, 10),
(103, 7, 11),
(104, 7, 12),
(105, 7, 13),
(106, 7, 14),
(107, 7, 15),
(108, 8, 1),
(109, 8, 2),
(110, 8, 3),
(111, 8, 4),
(112, 8, 5),
(113, 8, 6),
(114, 8, 7),
(115, 8, 8),
(116, 8, 9),
(117, 8, 10),
(118, 8, 11),
(119, 8, 12),
(120, 8, 13),
(121, 8, 14),
(122, 8, 15),
(123, 9, 1),
(124, 9, 2),
(125, 9, 3),
(126, 9, 4),
(127, 9, 5),
(128, 9, 6),
(129, 9, 7),
(130, 9, 8),
(131, 9, 9),
(132, 9, 10),
(133, 9, 11),
(134, 9, 12),
(135, 9, 13),
(136, 9, 14),
(137, 9, 15),
(138, 10, 1),
(139, 10, 2),
(140, 10, 3),
(141, 10, 4),
(142, 10, 5),
(143, 10, 6),
(144, 10, 7),
(145, 10, 8),
(146, 10, 9),
(147, 10, 10),
(148, 10, 11),
(149, 10, 12),
(150, 10, 13),
(151, 10, 14),
(152, 10, 15),
(153, 11, 1),
(154, 11, 2),
(155, 11, 3),
(156, 11, 4),
(157, 11, 5),
(158, 11, 6),
(159, 11, 7),
(160, 11, 8),
(161, 11, 9),
(162, 11, 10),
(163, 11, 11),
(164, 11, 12),
(165, 11, 13),
(166, 11, 14),
(167, 11, 15),
(168, 12, 1),
(169, 12, 2),
(170, 12, 3),
(171, 12, 4),
(172, 12, 5),
(173, 12, 6),
(174, 12, 7),
(175, 12, 8),
(176, 12, 9),
(177, 12, 10),
(178, 12, 11),
(179, 12, 12),
(180, 12, 13),
(181, 12, 14),
(182, 12, 15),
(183, 13, 1),
(184, 13, 2),
(185, 13, 3),
(186, 13, 4),
(187, 13, 5),
(188, 13, 6),
(189, 13, 7),
(190, 13, 8),
(191, 13, 9),
(192, 13, 10),
(193, 13, 11),
(194, 13, 12),
(195, 13, 13),
(196, 13, 14),
(197, 13, 15),
(198, 14, 1),
(199, 14, 2),
(200, 14, 3),
(201, 14, 4),
(202, 14, 5),
(203, 14, 6),
(204, 14, 7),
(205, 14, 8),
(206, 14, 9),
(207, 14, 10),
(208, 14, 11),
(209, 14, 12),
(210, 14, 13),
(211, 14, 14),
(212, 14, 15),
(213, 15, 1),
(214, 15, 2),
(215, 15, 3),
(216, 15, 4),
(217, 15, 5),
(218, 15, 6),
(219, 15, 7),
(220, 15, 8),
(221, 15, 9),
(222, 15, 10),
(223, 15, 11),
(224, 15, 12),
(225, 15, 13),
(226, 15, 14),
(227, 15, 15),
(228, 16, 1),
(229, 16, 2),
(230, 16, 3),
(231, 16, 4),
(232, 16, 5),
(233, 16, 6),
(234, 16, 7),
(235, 16, 8),
(236, 16, 9),
(237, 16, 10),
(238, 16, 11),
(239, 16, 12),
(240, 16, 13),
(241, 16, 14),
(242, 16, 15),
(243, 17, 1),
(244, 17, 2),
(245, 17, 3),
(246, 17, 4),
(247, 17, 5),
(248, 17, 6),
(249, 17, 7),
(250, 17, 8),
(251, 17, 9),
(252, 17, 10),
(253, 17, 11),
(254, 17, 12),
(255, 17, 13),
(256, 17, 14),
(257, 17, 15),
(258, 18, 1),
(259, 18, 2),
(260, 18, 3),
(261, 18, 4),
(262, 18, 5),
(263, 18, 6),
(264, 18, 7),
(265, 18, 8),
(266, 18, 9),
(267, 18, 10),
(268, 18, 11),
(269, 18, 12),
(270, 18, 13),
(271, 18, 14),
(272, 18, 15),
(273, 19, 1),
(274, 19, 2),
(275, 19, 3),
(276, 19, 4),
(277, 19, 5),
(278, 19, 6),
(279, 19, 7),
(280, 19, 8),
(281, 19, 9),
(282, 19, 10),
(283, 19, 11),
(284, 19, 12),
(285, 19, 13),
(286, 19, 14),
(287, 19, 15),
(288, 20, 1),
(289, 20, 2),
(290, 20, 3),
(291, 20, 4),
(292, 20, 5),
(293, 20, 6),
(294, 20, 7),
(295, 20, 8),
(296, 20, 9),
(297, 20, 10),
(298, 20, 11),
(299, 20, 12),
(300, 20, 13),
(301, 20, 14),
(302, 20, 15),
(303, 22, 3),
(305, 22, 6),
(306, 22, 1),
(307, 22, 2),
(308, 22, 2),
(313, 26, 25);

-- --------------------------------------------------------

--
-- Table structure for table `tb_instansi`
--

CREATE TABLE `tb_instansi` (
  `id_instansi` int(11) NOT NULL,
  `nama_instansi` int(11) NOT NULL,
  `alamat_instansi` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tb_instruktur`
--

CREATE TABLE `tb_instruktur` (
  `id_ins` int(10) NOT NULL,
  `nama_ins` varchar(50) NOT NULL,
  `email_ins` varchar(50) NOT NULL,
  `hp_ins` varchar(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_instruktur`
--

INSERT INTO `tb_instruktur` (`id_ins`, `nama_ins`, `email_ins`, `hp_ins`) VALUES
(1, 'Rondi Hidayat', 'rondi@inixindo.co.id', '6244454789'),
(2, 'Yusuf Rizal', 'yusuf@inixindo.co.id', '6246842589'),
(3, 'Ermenegilda Natalia', 'natalia@inixindo.co.id', '6244456428'),
(4, 'Marinos Fedelma', 'marinos@inxindo.co.id', '6244357159'),
(5, 'Floella Nikolina', 'floella@inxindo.co.id', '6243325649'),
(6, 'Narayan Ianus', 'narayan@inixindo.co.id', '6244369621'),
(7, 'Caroline Josefina', 'caroline@inxindo.co.id', '6246394276'),
(8, 'Afan Vlastimil', 'afan@maybank.co.id', '6246394111'),
(9, 'Aditi Zharko', 'aditi@maybank.co.id', '6246394646'),
(10, 'Idris Elba', 'elbas@maybank.co.id', '6244222223'),
(19, 'Ryan Gosling', 'ryan@llland.co.id', '628887123'),
(20, 'Heung Ming Son', 'son@spurs.com', '62787878'),
(21, 'Heimdall Stormcaller', 'heimdall@hyur.co.jp', '6248445');

-- --------------------------------------------------------

--
-- Table structure for table `tb_kelas`
--

CREATE TABLE `tb_kelas` (
  `id_kls` int(10) NOT NULL,
  `tgl_mulai_kls` date NOT NULL,
  `tgl_akhir_kls` date NOT NULL,
  `id_ins` int(10) NOT NULL,
  `id_mat` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_kelas`
--

INSERT INTO `tb_kelas` (`id_kls`, `tgl_mulai_kls`, `tgl_akhir_kls`, `id_ins`, `id_mat`) VALUES
(1, '2022-02-09', '2022-03-01', 1, 1),
(2, '2022-03-02', '2022-03-22', 2, 2),
(3, '2022-03-22', '2022-04-15', 3, 3),
(4, '2022-04-15', '2022-04-30', 4, 4),
(5, '2022-04-30', '2022-05-15', 5, 5),
(6, '2022-05-15', '2022-05-30', 6, 6),
(7, '2022-06-01', '2022-06-23', 7, 7),
(8, '2022-06-24', '2022-07-10', 8, 8),
(9, '2022-07-11', '2022-07-26', 9, 9),
(10, '2022-07-27', '2022-08-15', 10, 10),
(11, '2022-02-09', '2022-03-01', 1, 1),
(12, '2022-03-02', '2022-03-22', 2, 2),
(13, '2022-03-22', '2022-04-15', 3, 3),
(14, '2022-04-15', '2022-04-30', 4, 4),
(15, '2022-04-30', '2022-05-15', 5, 5),
(16, '2022-05-15', '2022-05-30', 6, 6),
(17, '2022-06-01', '2022-06-23', 7, 7),
(18, '2022-06-24', '2022-07-10', 8, 8),
(19, '2022-07-11', '2022-07-26', 9, 9),
(20, '2022-07-27', '2022-08-15', 10, 10),
(22, '2022-03-12', '2022-03-29', 1, 7),
(26, '2028-01-01', '2028-01-15', 1, 9);

-- --------------------------------------------------------

--
-- Table structure for table `tb_materi`
--

CREATE TABLE `tb_materi` (
  `id_mat` int(10) NOT NULL,
  `nama_mat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_materi`
--

INSERT INTO `tb_materi` (`id_mat`, `nama_mat`) VALUES
(1, 'Oracle Database with SQL'),
(2, 'Android Programming with Java'),
(3, 'Fullstack Developer with MERN'),
(4, 'CCNA Certification'),
(5, 'Web Development with Java Spring'),
(6, 'Data Science Bootcamp'),
(7, 'Devops Bootcamp'),
(8, 'OCA Certification Bootcamp'),
(9, 'CEH Certification Bootcamp'),
(10, '.NET Bootcamp');

-- --------------------------------------------------------

--
-- Table structure for table `tb_peserta`
--

CREATE TABLE `tb_peserta` (
  `id_pst` int(10) NOT NULL,
  `nama_pst` varchar(50) NOT NULL,
  `email_pst` varchar(50) NOT NULL,
  `hp_pst` varchar(13) NOT NULL,
  `instansi_pst` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_peserta`
--

INSERT INTO `tb_peserta` (`id_pst`, `nama_pst`, `email_pst`, `hp_pst`, `instansi_pst`) VALUES
(1, 'Bagus Indiarto Pratomo', 'bagus@jago.co.id', '69625524848', 'Bank Jago'),
(2, 'Muhammad Jadid', 'jadid@maybank.co.id', '62784524848', 'Maybank'),
(3, 'Septian G', 'septian@maybank.co.id', '62784425871', 'Maybank'),
(4, 'Chairani Tiara', 'chairani@maybank.co.id', '62784448593', 'Maybank'),
(5, 'Rizal Cerdas', 'rizal@maybank.co.id', '62784351027', 'Maybank'),
(6, 'Jeremy Mandei', 'jeremy@maybank.co.id', '62784433351', 'Maybank'),
(7, 'Adi Yusuf', 'adi@maybank.co.id', '62784433654', 'Maybank'),
(8, 'Rusheila Donna', 'rusheila@maybank.co.id', '62784412345', 'Maybank'),
(9, 'Steady', 'steady@maybank.co.id', '62784454321', 'Maybank'),
(10, 'Nicholas P', 'nicholas@maybank.co.id', '62784435426', 'Maybank'),
(11, 'Oktasanti Putri', 'nicholas@maybank.co.id', '62784435426', 'Maybank'),
(12, 'Kia Dzaky', 'kia@maybank.co.id', '62784432222', 'Maybank'),
(13, 'Anindhito Irmandharu', 'anindhito@maybank.co.id', '62784444444', 'Maybank'),
(14, 'Febryna', 'febryna@maybank.co.id', '62784448888', 'Maybank'),
(15, 'Farid Pridiatama', 'farid@maybank.co.id', '62786664444', 'Maybank'),
(22, 'Suisei a', 'test@test.com', '628787878', 'ASDADSsad'),
(24, 'HEHEHEH', 'HEHEHEH@HEHEH.HE', '987897987', 'HEHEHEH'),
(25, 'Rin Okabe', 'rin@ocbc.co.id', '628787877', 'OCBC'),
(27, 'Bruno Fernandes', 'bruno@mufc.com', '62666666', 'Manchester United');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_detail_kelas`
--
ALTER TABLE `tb_detail_kelas`
  ADD PRIMARY KEY (`id_detail_kls`),
  ADD KEY `id_kls` (`id_kls`),
  ADD KEY `id_pst` (`id_pst`);

--
-- Indexes for table `tb_instansi`
--
ALTER TABLE `tb_instansi`
  ADD PRIMARY KEY (`id_instansi`);

--
-- Indexes for table `tb_instruktur`
--
ALTER TABLE `tb_instruktur`
  ADD PRIMARY KEY (`id_ins`);

--
-- Indexes for table `tb_kelas`
--
ALTER TABLE `tb_kelas`
  ADD PRIMARY KEY (`id_kls`),
  ADD KEY `id_ins` (`id_ins`),
  ADD KEY `id_mat` (`id_mat`);

--
-- Indexes for table `tb_materi`
--
ALTER TABLE `tb_materi`
  ADD PRIMARY KEY (`id_mat`);

--
-- Indexes for table `tb_peserta`
--
ALTER TABLE `tb_peserta`
  ADD PRIMARY KEY (`id_pst`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_detail_kelas`
--
ALTER TABLE `tb_detail_kelas`
  MODIFY `id_detail_kls` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=315;

--
-- AUTO_INCREMENT for table `tb_instansi`
--
ALTER TABLE `tb_instansi`
  MODIFY `id_instansi` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tb_instruktur`
--
ALTER TABLE `tb_instruktur`
  MODIFY `id_ins` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `tb_kelas`
--
ALTER TABLE `tb_kelas`
  MODIFY `id_kls` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `tb_materi`
--
ALTER TABLE `tb_materi`
  MODIFY `id_mat` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `tb_peserta`
--
ALTER TABLE `tb_peserta`
  MODIFY `id_pst` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_detail_kelas`
--
ALTER TABLE `tb_detail_kelas`
  ADD CONSTRAINT `tb_detail_kelas_ibfk_1` FOREIGN KEY (`id_kls`) REFERENCES `tb_kelas` (`id_kls`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_detail_kelas_ibfk_2` FOREIGN KEY (`id_pst`) REFERENCES `tb_peserta` (`id_pst`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tb_kelas`
--
ALTER TABLE `tb_kelas`
  ADD CONSTRAINT `tb_kelas_ibfk_1` FOREIGN KEY (`id_ins`) REFERENCES `tb_instruktur` (`id_ins`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_kelas_ibfk_2` FOREIGN KEY (`id_mat`) REFERENCES `tb_materi` (`id_mat`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

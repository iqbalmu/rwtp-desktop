-- phpMyAdmin SQL Dump
-- version 5.1.1deb5ubuntu1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 30, 2023 at 08:58 PM
-- Server version: 8.0.33-0ubuntu0.22.04.2
-- PHP Version: 8.1.2-1ubuntu2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_rwt_3`
--

-- --------------------------------------------------------

--
-- Table structure for table `harga`
--

CREATE TABLE `harga` (
  `rental` int NOT NULL,
  `sewa` int NOT NULL,
  `paket` int NOT NULL,
  `kelas` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `harga`
--

INSERT INTO `harga` (`rental`, `sewa`, `paket`, `kelas`) VALUES
(100000, 50000, 25000, 'ekonomi'),
(150000, 70000, 25000, 'eksekutif');

-- --------------------------------------------------------

--
-- Table structure for table `log_transaksi`
--

CREATE TABLE `log_transaksi` (
  `no_transaksi` varchar(36) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `log_transaksi`
--

INSERT INTO `log_transaksi` (`no_transaksi`) VALUES
('0c0797a4-336e-4bd1-9da1-a273609c7e13'),
('858de2dc-f9cb-4c34-8c17-5ec7965841ad'),
('f59fc059-24e0-4226-8fb1-c38530f2cc17');

-- --------------------------------------------------------

--
-- Table structure for table `mobil`
--

CREATE TABLE `mobil` (
  `id_mobil` int NOT NULL,
  `id_sopir` int NOT NULL,
  `nopol` varchar(12) NOT NULL,
  `jenis_mobil` varchar(20) NOT NULL,
  `kelas` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `status` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `mobil`
--

INSERT INTO `mobil` (`id_mobil`, `id_sopir`, `nopol`, `jenis_mobil`, `kelas`, `status`) VALUES
(1, 13, 'GQ 1646 NE', 'Reborn', 'Eksekutif', 'Maintanance'),
(4, 5, 'ON 2907 QI', 'Inova', 'Ekonomi', 'Ready'),
(5, 11, 'YQ 1127 GR', 'Reborn', 'Eksekutif', 'Ready'),
(6, 3, 'EC 5437 SF', 'Inova', 'Ekonomi', 'Maintanance'),
(7, 6, 'DJ 6572 CP', 'Panther', 'Eksekutif', 'Rental'),
(8, 9, 'OC 2808 PU', 'Reborn', 'Eksekutif', 'Ready'),
(9, 1, 'OH 0357 CE', 'Inova', 'Ekonomi', 'Ready'),
(10, 7, 'VD 3438 KM', 'Inova', 'Ekonomi', 'Ready'),
(17, 10, 'BA 2123 EP', 'Panther', 'Eksekutif', 'Maintanance');

-- --------------------------------------------------------

--
-- Table structure for table `paket`
--

CREATE TABLE `paket` (
  `id_paket` int NOT NULL,
  `no_transaksi` varchar(36) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `nopol` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `nama_pengirim` varchar(50) NOT NULL,
  `hp_pengirim` varchar(13) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `nama_penerima` varchar(50) NOT NULL,
  `hp_penerima` varchar(13) DEFAULT NULL,
  `tujuan` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `kuantitas` int DEFAULT NULL,
  `bayar` int DEFAULT NULL,
  `keterangan` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `date` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `paket`
--

INSERT INTO `paket` (`id_paket`, `no_transaksi`, `nopol`, `nama_pengirim`, `hp_pengirim`, `nama_penerima`, `hp_penerima`, `tujuan`, `kuantitas`, `bayar`, `keterangan`, `date`) VALUES
(1, '0c0797a4-336e-4bd1-9da1-a273609c7e13', 'OC 2808 PU', 'Iqbal', '0831982378', 'iqhkladn', '098901321', 'padang', 3, 90000, '', '2023-06-23 07:16:38');

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `id_pelanggan` varchar(36) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `kategori` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `no_telp` varchar(15) NOT NULL,
  `alamat` text NOT NULL,
  `isMember` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`id_pelanggan`, `nama`, `kategori`, `no_telp`, `alamat`, `isMember`) VALUES
('2c3c41ba-1fb1-404a-9c21-5e64fe643093', 'Rifqi', 'mahasiswa', '0978687568686', 'padang', 0),
('36781263812412', 'iqbal', 'Mahasiswa', '08689698689', 'asldhaslkd', 0),
('b405f524-e67b-444d-ab35-48247253e85b', 'iahsdosand', 'mahasiswa', '9876543456788', 'apsdnl', 0);

-- --------------------------------------------------------

--
-- Table structure for table `rental_transaksi`
--

CREATE TABLE `rental_transaksi` (
  `id_rental` int NOT NULL,
  `id_pelanggan` varchar(36) DEFAULT NULL,
  `nopol` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `id_user` int NOT NULL,
  `date` timestamp NOT NULL,
  `lama_rental` int NOT NULL,
  `bayar` int NOT NULL,
  `keterangan` text NOT NULL,
  `no_transaksi` varchar(36) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `rental_date` date DEFAULT NULL,
  `return_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `rental_transaksi`
--

INSERT INTO `rental_transaksi` (`id_rental`, `id_pelanggan`, `nopol`, `id_user`, `date`, `lama_rental`, `bayar`, `keterangan`, `no_transaksi`, `rental_date`, `return_date`) VALUES
(1, '36781263812412', 'DJ 6572 CP', 1, '2023-06-23 07:19:25', 5, 750000, '', 'f59fc059-24e0-4226-8fb1-c38530f2cc17', '2023-06-26', '2023-07-01');

-- --------------------------------------------------------

--
-- Table structure for table `sewa_transaksi`
--

CREATE TABLE `sewa_transaksi` (
  `id_sewa` int NOT NULL,
  `id_pelanggan` varchar(36) NOT NULL,
  `nopol` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `id_user` int NOT NULL,
  `date` date NOT NULL,
  `jadwal` varchar(25) NOT NULL,
  `kursi` varchar(25) NOT NULL,
  `tujuan` text NOT NULL,
  `no_transaksi` varchar(36) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `harga` double DEFAULT NULL,
  `keterangan` text,
  `sewa_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `sewa_transaksi`
--

INSERT INTO `sewa_transaksi` (`id_sewa`, `id_pelanggan`, `nopol`, `id_user`, `date`, `jadwal`, `kursi`, `tujuan`, `no_transaksi`, `harga`, `keterangan`, `sewa_date`) VALUES
(1, '2c3c41ba-1fb1-404a-9c21-5e64fe643093', 'OH 0357 CE', 1, '2023-06-23', 'Pagi 10:00 WIB', '[2, 1]', 'pekanbaru', '858de2dc-f9cb-4c34-8c17-5ec7965841ad', 320000, '', '2023-06-20');

-- --------------------------------------------------------

--
-- Table structure for table `sopir`
--

CREATE TABLE `sopir` (
  `id_sopir` int NOT NULL,
  `nama_sopir` varchar(50) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `alamat` text NOT NULL,
  `ktp` varchar(19) NOT NULL,
  `sim` varchar(16) NOT NULL,
  `status` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `sopir`
--

INSERT INTO `sopir` (`id_sopir`, `nama_sopir`, `phone`, `alamat`, `ktp`, `sim`, `status`) VALUES
(1, 'Joys', '087867281928', 'Pekanbaru', '9878683928432312', '98786839284323', 'Utama'),
(3, 'Freya Valdez', '08428126235210', '222-1619 Sit St.', '6286029679655483', '63742521210375', 'utama'),
(4, 'Adara Patrick', '08608641108165', 'P.O. Box 104, 7254 Elit Rd.', '0186194194861584', '41345379567711', 'cadangan'),
(5, 'Cullen Grimes', '08549439345934', '735-7389 Rutrum Ave', '5314485637367741', '17624534588638', 'utama'),
(6, 'Virginia Morrow', '08733559743778', '1425 Orci. St.', '8851408176532987', '35748483868346', 'utama'),
(7, 'Duncan Nunez', '08425143563274', '139-1757 In, Av.', '0470970849551349', '21204749917111', 'utama'),
(8, 'Constance Walton', '08767583880106', 'Ap #268-2731 Et Rd.', '0243224331666301', '75004225306825', 'utama'),
(9, 'Cooper Wooten', '08642366942113', '827-3838 Non, St.', '8836337683994829', '55187832456608', 'cadangan'),
(10, 'Cedric William', '08765608233326', 'Ap #812-2094 Ultrices Road', '5785037157781403', '64549182200118', 'cadangan'),
(11, 'Steel Macdonald', '08423476452728', '4872 Ridiculus Avenue', '7717538512260410', '53332663769529', 'cadangan'),
(12, 'Gil Lara', '08642276962415', '172-1016 In Rd.', '1914514852204450', '57356147141134', 'cadangan'),
(13, 'Troy Bond', '08651325922381', 'Ap #349-8655 Ligula. Rd.', '4517702781600427', '29949751510265', 'utama'),
(15, 'Oliver Beard', '08455654212211', '304-3133 Aliquam Rd.', '3138352464259657', '29816081376777', 'cadangan');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` int NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(32) NOT NULL,
  `level` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `username`, `password`, `level`) VALUES
(1, 'admin', 'admin', 'admin'),
(2, 'owner', 'owner', 'owner');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `log_transaksi`
--
ALTER TABLE `log_transaksi`
  ADD PRIMARY KEY (`no_transaksi`);

--
-- Indexes for table `mobil`
--
ALTER TABLE `mobil`
  ADD PRIMARY KEY (`id_mobil`),
  ADD UNIQUE KEY `nopol` (`nopol`),
  ADD KEY `id_sopir` (`id_sopir`);

--
-- Indexes for table `paket`
--
ALTER TABLE `paket`
  ADD PRIMARY KEY (`id_paket`),
  ADD KEY `nopol_2` (`nopol`),
  ADD KEY `no_transaksi` (`no_transaksi`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`);

--
-- Indexes for table `rental_transaksi`
--
ALTER TABLE `rental_transaksi`
  ADD PRIMARY KEY (`id_rental`),
  ADD KEY `nopol` (`nopol`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `no_transaksi` (`no_transaksi`),
  ADD KEY `id_pelanggan` (`id_pelanggan`);

--
-- Indexes for table `sewa_transaksi`
--
ALTER TABLE `sewa_transaksi`
  ADD PRIMARY KEY (`id_sewa`),
  ADD KEY `id_pelanggan` (`id_pelanggan`),
  ADD KEY `id_mobil` (`nopol`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `no_transaksi` (`no_transaksi`);

--
-- Indexes for table `sopir`
--
ALTER TABLE `sopir`
  ADD PRIMARY KEY (`id_sopir`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `mobil`
--
ALTER TABLE `mobil`
  MODIFY `id_mobil` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `paket`
--
ALTER TABLE `paket`
  MODIFY `id_paket` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `rental_transaksi`
--
ALTER TABLE `rental_transaksi`
  MODIFY `id_rental` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `sewa_transaksi`
--
ALTER TABLE `sewa_transaksi`
  MODIFY `id_sewa` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `sopir`
--
ALTER TABLE `sopir`
  MODIFY `id_sopir` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `mobil`
--
ALTER TABLE `mobil`
  ADD CONSTRAINT `mobil_ibfk_1` FOREIGN KEY (`id_sopir`) REFERENCES `sopir` (`id_sopir`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `paket`
--
ALTER TABLE `paket`
  ADD CONSTRAINT `paket_FK` FOREIGN KEY (`no_transaksi`) REFERENCES `log_transaksi` (`no_transaksi`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `paket_ibfk_1` FOREIGN KEY (`nopol`) REFERENCES `mobil` (`nopol`);

--
-- Constraints for table `rental_transaksi`
--
ALTER TABLE `rental_transaksi`
  ADD CONSTRAINT `rental_transaksi_FK` FOREIGN KEY (`no_transaksi`) REFERENCES `log_transaksi` (`no_transaksi`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `rental_transaksi_FK_1` FOREIGN KEY (`nopol`) REFERENCES `mobil` (`nopol`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `rental_transaksi_FK_2` FOREIGN KEY (`id_pelanggan`) REFERENCES `pelanggan` (`id_pelanggan`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `rental_transaksi_FK_3` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sewa_transaksi`
--
ALTER TABLE `sewa_transaksi`
  ADD CONSTRAINT `sewa_transaksi_FK` FOREIGN KEY (`id_pelanggan`) REFERENCES `pelanggan` (`id_pelanggan`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sewa_transaksi_FK_1` FOREIGN KEY (`nopol`) REFERENCES `mobil` (`nopol`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sewa_transaksi_FK_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sewa_transaksi_FK_3` FOREIGN KEY (`no_transaksi`) REFERENCES `log_transaksi` (`no_transaksi`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th1 19, 2022 lúc 03:21 PM
-- Phiên bản máy phục vụ: 10.4.18-MariaDB
-- Phiên bản PHP: 8.0.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `book_db`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chapters`
--

CREATE TABLE `chapters` (
  `id` bigint(20) NOT NULL,
  `chapter_code` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `chapter_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `introduction` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `page` bigint(20) NOT NULL,
  `translator_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chapters`
--

INSERT INTO `chapters` (`id`, `chapter_code`, `chapter_name`, `introduction`, `page`, `translator_name`) VALUES
(1, 'c2', 'Chapter 2', 'intro of chapter 2...', 20, 'baotg'),
(2, 'c3', 'Chapter 3', 'intro of chapter 3...', 20, 'baotg'),
(5, 'c4', 'Chapter 4', 'intro of chapter 4...', 20, 'baotg'),
(6, 'c5', 'Chapter 5', 'intro of chapter 5...', 20, 'baotg'),
(7, 'c6', 'Chapter 6', 'intro of chapter 6...', 20, 'baotg'),
(8, 'c8', 'Chapter 8', 'introduction of chapter 8', 10, 'baotg'),
(12, 'C100', 'Chapter 100', 'intro of chapter 100', 100, 'baotg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `lessons`
--

CREATE TABLE `lessons` (
  `id` bigint(20) NOT NULL,
  `introduction` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lesson_code` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `lesson_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `page` bigint(20) NOT NULL,
  `chapter_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `lessons`
--

INSERT INTO `lessons` (`id`, `introduction`, `lesson_code`, `lesson_name`, `page`, `chapter_id`) VALUES
(1, 'intro of lesson 1', 'l2', 'lesson 1', 5, 1),
(7, 'introductron of lesson 9', 'l9', 'lesson 9', 4, 8),
(8, 'introduction of lesson 9', 'l10', 'lesson 10', 4, 6),
(10, 'intro of lesson 3', 'L100', 'lesson3', 10, 12),
(11, 'intro of lesson 3', 'L101', 'lesson101', 10, 12),
(12, 'intro of lesson 3', 'l3', 'lesson3', 10, 12);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chapters`
--
ALTER TABLE `chapters`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_bertnd6gclha7n4oxw5r6c40m` (`chapter_code`);

--
-- Chỉ mục cho bảng `lessons`
--
ALTER TABLE `lessons`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_7jvsiodwrlie5ajrom650ofuw` (`lesson_code`),
  ADD KEY `FKmb78vk1f2oljr16oj1hpo45ma` (`chapter_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `chapters`
--
ALTER TABLE `chapters`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT cho bảng `lessons`
--
ALTER TABLE `lessons`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `lessons`
--
ALTER TABLE `lessons`
  ADD CONSTRAINT `FKmb78vk1f2oljr16oj1hpo45ma` FOREIGN KEY (`chapter_id`) REFERENCES `chapters` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

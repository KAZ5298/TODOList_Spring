-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- ホスト: 127.0.0.1
-- 生成日時: 2024-08-19 17:14:55
-- サーバのバージョン： 10.4.32-MariaDB
-- PHP のバージョン: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- データベース: `todo`
--

-- --------------------------------------------------------

--
-- テーブルの構造 `todo_items`
--

CREATE TABLE `todo_items` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `item_name` varchar(100) NOT NULL,
  `registration_date` date NOT NULL,
  `expire_date` date NOT NULL,
  `finished_date` date NOT NULL COMMENT 'NULLのとき、未完了とする',
  `is_deleted` tinyint(4) DEFAULT 0 COMMENT '0:未削除\r\n1:削除済み',
  `create_date_time` datetime DEFAULT current_timestamp(),
  `update_date_time` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- テーブルのデータのダンプ `todo_items`
--

INSERT INTO `todo_items` (`id`, `user_id`, `item_name`, `registration_date`, `expire_date`, `finished_date`, `is_deleted`, `create_date_time`, `update_date_time`) VALUES
(1, 1, 'item1', '2024-08-20', '2024-08-20', '0000-00-00', 0, '2024-08-20 00:13:44', '2024-08-20 00:13:44'),
(2, 2, 'item2', '2024-08-20', '2024-08-21', '0000-00-00', 0, '2024-08-20 00:13:44', '2024-08-20 00:13:44'),
(3, 3, 'item3', '2024-08-20', '2024-08-23', '0000-00-00', 0, '2024-08-20 00:13:44', '2024-08-20 00:13:44'),
(4, 1, 'item4', '2024-08-20', '2024-08-20', '2024-08-20', 0, '2024-08-20 00:13:44', '2024-08-20 00:13:44');

--
-- ダンプしたテーブルのインデックス
--

--
-- テーブルのインデックス `todo_items`
--
ALTER TABLE `todo_items`
  ADD PRIMARY KEY (`id`);

--
-- ダンプしたテーブルの AUTO_INCREMENT
--

--
-- テーブルの AUTO_INCREMENT `todo_items`
--
ALTER TABLE `todo_items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

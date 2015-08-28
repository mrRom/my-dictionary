CREATE TABLE `word` (
  `word_id` int(11) NOT NULL AUTO_INCREMENT,
  `word_name` varchar(45) COLLATE utf8_bin NOT NULL,
  `word_translation` varchar(45) COLLATE utf8_bin NOT NULL,
  `usage_example` varchar(150) COLLATE utf8_bin DEFAULT NULL,
  `d_user_name` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`word_id`)
) ENGINE=InnoDB AUTO_INCREMENT DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `d_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) COLLATE utf8_bin NOT NULL,
  `user_password` varchar(60) COLLATE utf8_bin NOT NULL,
  `user_email` varchar(45) COLLATE utf8_bin NOT NULL,
  `user_access` int(11) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`),
  UNIQUE KEY `user_password_UNIQUE` (`user_password`),
  UNIQUE KEY `user_email_UNIQUE` (`user_email`)
) ENGINE=InnoDB AUTO_INCREMENT DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `text` (
  `text_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(250) NOT NULL,
  `text` TEXT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`text_id`));
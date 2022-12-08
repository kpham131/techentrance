CREATE TABLE `Jobs` (
  `id` varchar(100) NOT NULL,
  `ref` varchar(300) NOT NULL,
  `contents` mediumtext,
  `name` varchar(100) DEFAULT NULL,
  `locations` varchar(100) DEFAULT NULL,
  `company` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `skills` (
  `id` varchar(100) NOT NULL,
  `skill` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user_jobs` (
  `user_id` varchar(100) NOT NULL,
  `job_id` varchar(100) NOT NULL,
  PRIMARY KEY (`user_id`,`job_id`),
  KEY `job_id` (`job_id`),
  CONSTRAINT `user_jobs_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `user_jobs_ibfk_2` FOREIGN KEY (`job_id`) REFERENCES `jobs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `USERS` (
  `id` varchar(100) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone_number` varchar(10) DEFAULT NULL,
  `looking_for` varchar(100) DEFAULT NULL,
  `gpa` decimal(3,2) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `password` varchar(50) NOT NULL,
  `session_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `phone_number` (`phone_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user_skill` (
  `user_id` varchar(100) NOT NULL,
  `skill_id` varchar(100) NOT NULL,
  PRIMARY KEY (`user_id`,`skill_id`),
  KEY `skill_id` (`skill_id`),
  CONSTRAINT `user_skill_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `user_skill_ibfk_2` FOREIGN KEY (`skill_id`) REFERENCES `skills` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

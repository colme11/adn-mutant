CREATE TABLE `dna` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dna` varchar(1000) NOT NULL,
  `is_mutant` tinyint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dna_UNIQUE` (`dna`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8
CREATE TABLE `mutants`.`dna` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `dna` VARCHAR(1000) NOT NULL,
  `is_mutant` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `dna_UNIQUE` (`dna` ASC));
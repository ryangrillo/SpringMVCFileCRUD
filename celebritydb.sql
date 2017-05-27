-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema celebritydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `celebritydb` ;

-- -----------------------------------------------------
-- Schema celebritydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `celebritydb` DEFAULT CHARACTER SET utf8 ;
USE `celebritydb` ;

-- -----------------------------------------------------
-- Table `celebrity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `celebrity` ;

CREATE TABLE IF NOT EXISTS `celebrity` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(25) NOT NULL,
  `job_title` VARCHAR(45) NULL,
  `image` VARCHAR(500) NULL,
  `quote` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SET SQL_MODE = '';
GRANT USAGE ON *.* TO user@localhost;
 DROP USER user@localhost;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'user'@'localhost' IDENTIFIED BY 'user';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'user'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `celebrity`
-- -----------------------------------------------------
START TRANSACTION;
USE `celebritydb`;
INSERT INTO `celebrity` (`id`, `name`, `job_title`, `image`, `quote`) VALUES (1, 'Jenn Schiffer', 'Front End Developer', 'https://i.ytimg.com/vi/wewAC5X_CZ8/maxresdefault.jpg', 'I’m basically an expert at California Style Sheets, even if I’m an East Coast developer with back-end development training.');
INSERT INTO `celebrity` (`id`, `name`, `job_title`, `image`, `quote`) VALUES (2, 'Steve Jobs', 'former CEO of Apple', 'https://blogs-images.forbes.com/jacquelynsmith/files/2012/11/0430_billionaires-steve-jobs_650x4551.jpg', 'You can stay outside all day, In the sun all day, Tossing a ball around, Or you can sit at your computer and do something that matters');
INSERT INTO `celebrity` (`id`, `name`, `job_title`, `image`, `quote`) VALUES (3, 'Mark Zuckerberk', 'CEO of Facebook', 'http://media4.s-nbcnews.com/i/newscms/2014_06/160931/cms-140204-mark-zuckerberg-830a_4a06d9aadb59b235fded0eb36ff49a1e.jpg', 'In a world that\'s changing really quickly, the only strategy that is guaranteed to fail is not taking risks');
INSERT INTO `celebrity` (`id`, `name`, `job_title`, `image`, `quote`) VALUES (4, 'Ada Lovelace', 'computer scientist', 'https://metrouk2.files.wordpress.com/2013/10/ay95895977ada-lovelace-engl.jpg', 'If you can\'t give me poetry, can\'t you give me poetical science?');
INSERT INTO `celebrity` (`id`, `name`, `job_title`, `image`, `quote`) VALUES (5, 'Peter Thiel', 'Tech Venture Capitalist', 'https://upload.wikimedia.org/wikipedia/commons/9/9f/Peter_Thiel_by_Dan_Taylor.jpg', 'The next Bill Gates will not start an operating system. The next Larry Page won\'t start a search engine. The next Mark Zuckerberg won\'t start a social network company. If you are copying these people, you are not learning from them.');
INSERT INTO `celebrity` (`id`, `name`, `job_title`, `image`, `quote`) VALUES (6, 'Bill Gates', 'CEO of Microsoft', 'https://pbs.twimg.com/profile_images/558109954561679360/j1f9DiJi.jpeg', 'It seduces smart people into thinking they can\'t lose.');

COMMIT;


-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema triviatime
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `triviatime` ;

-- -----------------------------------------------------
-- Schema triviatime
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `triviatime` DEFAULT CHARACTER SET utf8mb3 ;
USE `triviatime` ;

-- -----------------------------------------------------
-- Table `triviatime`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `triviatime`.`category` (
  `CategoryID` INT NOT NULL,
  `CategoryName` VARCHAR(45) NULL DEFAULT NULL,
  `Description` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`CategoryID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `triviatime`.`questions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `triviatime`.`questions` (
  `QuestionID` INT NOT NULL,
  `Questions` VARCHAR(1000) NULL DEFAULT NULL,
  `category_CategoryID` INT NOT NULL,
  PRIMARY KEY (`QuestionID`),
  INDEX `fk_questions_category_idx` (`category_CategoryID` ASC),
  CONSTRAINT `fk_questions_category`
    FOREIGN KEY (`category_CategoryID`)
    REFERENCES `triviatime`.`category` (`CategoryID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `triviatime`.`answers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `triviatime`.`answers` (
  `AnswerID` INT NOT NULL,
  `Answer` VARCHAR(1000) NULL DEFAULT NULL,
  `questions_QuestionID` INT NOT NULL,
  `correct` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`AnswerID`),
  INDEX `fk_answers_questions1_idx` (`questions_QuestionID` ASC),
  CONSTRAINT `fk_answers_questions1`
    FOREIGN KEY (`questions_QuestionID`)
    REFERENCES `triviatime`.`questions` (`QuestionID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `triviatime`.`leaderboard`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `triviatime`.`leaderboard` (
  `LeaderboardID` INT NOT NULL,
  `Name` VARCHAR(45) NULL DEFAULT NULL,
  `Category` VARCHAR(45) NULL DEFAULT NULL,
  `Score` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`LeaderboardID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

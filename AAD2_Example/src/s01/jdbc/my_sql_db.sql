CREATE SCHEMA `LabDB` DEFAULT CHARACTER SET utf8 ;
USE `LabDB`;

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE TABLE IF NOT EXISTS `LabDB`.`Classes` (
  `ClassName` VARCHAR(15) NOT NULL,
  `Location` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`ClassName`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `LabDB`.`Students` (
  `RollNo` VARCHAR(6) NOT NULL,
  `FullName` VARCHAR(50) NOT NULL,
  `Birthday` DATETIME NULL DEFAULT NULL,
  `Address` VARCHAR(100) NULL DEFAULT NULL,
  `ClassName` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`RollNo`),
  INDEX `fk_Students_Classes_idx` (`ClassName` ASC),
  CONSTRAINT `fk_Students_Classes`
    FOREIGN KEY (`ClassName`)
    REFERENCES `LabDB`.`Classes` (`ClassName`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `LabDB`.`Subjects` (
  `SubjectID` INT(11) NOT NULL,
  `SubjectName` VARCHAR(50) NOT NULL,
  `TheoryNo` INT(11) NULL DEFAULT NULL,
  `ParticeNo` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`SubjectID`),
  UNIQUE INDEX `SubjectName_UNIQUE` (`SubjectName` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
PACK_KEYS = Default;

CREATE TABLE IF NOT EXISTS `LabDB`.`Marks` (
  `Mark` FLOAT(11) NULL DEFAULT NULL,
  `SubjectID` INT(11) NOT NULL,
  `RollNo` VARCHAR(6) NOT NULL,
  INDEX `fk_Marks_Subjects1_idx` (`SubjectID` ASC),
  INDEX `fk_Marks_Students1_idx` (`RollNo` ASC),
  PRIMARY KEY (`SubjectID`, `RollNo`),
  CONSTRAINT `fk_Marks_Subjects1`
    FOREIGN KEY (`SubjectID`)
    REFERENCES `LabDB`.`Subjects` (`SubjectID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Marks_Students1`
    FOREIGN KEY (`RollNo`)
    REFERENCES `LabDB`.`Students` (`RollNo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO Classes(ClassName, Location)
    VALUES('T1107G', 'FAT3'),
        ('T1105L', 'FAT1'),
        ('T1109L', 'FAT1'),
        ('C1105I', 'FAT3');
INSERT INTO Students(RollNo, FullName, Birthday, Address, ClassName)
    VALUES('C00111', 'Nguyen Van A', '1987-12-12', 'Ha Noi', 'T1107G'),
        ('C00112', 'Nguyen Van B', '1987-12-12', 'Ha Noi', 'T1107G'),
        ('C00113', 'Nguyen Van C', '1987-12-12', 'Ha Noi', 'T1107G'),
        ('C00114', 'Nguyen Van D', '1987-12-12', 'Ha Noi', 'T1107G'),
        ('A00111', 'Tran Thi X', '1987-12-12', 'Ha Noi', 'T1105L'),
        ('A00112', 'Tran Thi Y', '1987-12-12', 'Ha Noi', 'T1105L'),
        ('A00113', 'Pham Van O', '1987-12-12', 'Ha Noi', 'T1105L'),
        ('A00114', 'Pham Van P', '1987-12-12', 'Ha Noi', 'T1105L'),
        ('C00115', 'Pham Van Q', '1987-12-12', 'Ha Noi', 'C1105I'),
        ('C00116', 'Pham Van R', '1987-12-12', 'Ha Noi', 'C1105I'),
        ('C00117', 'Tran Thi Z', '1987-12-12', 'Ha Noi', 'C1105I');
INSERT INTO Subjects(SubjectID, SubjectName, TheoryNo, ParticeNo)
    VALUES('CF', 'Computer Fundamental', 5, 5),
        ('BDWS', 'Building Dinamic Website', 13, 13),
        ('EPC', 'Elementary Programing with C', 14, 14),
        ('MSSQL', 'Development Database with MS SQL Server', 13, 11),
        ('ADF1', 'Application Development Fundamental 1', 9, 9);
INSERT INTO Marks(RollNo, SubjectID, Mark)
    VALUES('C00111', 'CF', 14),
        ('C00112', 'CF', 9),
        ('C00113', 'CF', 22),
        ('C00114', 'CF', 11),
        ('C00115', 'CF', 7),
        ('C00116', 'CF', 20),
        ('C00117', 'CF', 24),
        ('A00111', 'CF', 23.5),
        ('A00112', 'CF', 14.75),
        ('A00113', 'CF', 16),
        ('A00114', 'CF', 18.5),
        ('C00111', 'EPC', 14),
        ('C00112', 'EPC', 9),
        ('C00113', 'EPC', 22),
        ('C00114', 'BDWS', 11),
        ('C00115', 'MSSQL', 7),
        ('C00116', 'BDWS', 20),
        ('C00117', 'MSSQL', 24),
        ('A00111', 'BDWS', 23.5),
        ('A00112', 'EPC', 14.75),
        ('A00113', 'MSSQL', 16),
        ('A00114', 'EPC', 18.5);

USE `LabDB`;
DROP procedure IF EXISTS `spInsertClasses`;
DELIMITER $$
USE `LabDB`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `spInsertClasses`(IN ClassName varchar(15), IN Location varchar(100))
BEGIN
	INSERT INTO Classes(ClassName, Location) VALUES(ClassName, Location);
END$$
DELIMITER ;

USE `LabDB`;
DROP procedure IF EXISTS `spDeleteClasses`;
DELIMITER $$
USE `LabDB`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `spDeleteClasses`(IN ClassName varchar(15))
BEGIN
	DELETE FROM Marks WHERE RollNo IN (SELECT RollNo FROM Students WHERE Students.ClassName=ClassName);
	DELETE FROM Students WHERE Students.ClassName=ClassName;
	DELETE FROM Classes WHERE Classes.ClassName=ClassName;
END$$
DELIMITER ;
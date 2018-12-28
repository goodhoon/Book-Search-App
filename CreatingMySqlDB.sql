/*
 * Author: KHP
 */


CREATE TABLE BOOKS
(BOOK_ID    INT NOT NULL PRIMARY KEY,
title      VARCHAR(50),
author     VARCHAR(50),
ISBN       VARCHAR(20),
publisher  VARCHAR(50),
status 	   VARCHAR(15)
);

INSERT INTO `books` (`book_ID`,`title`,`author`,`ISBN`,`publisher`,`status`) VALUES (1001,'Java for dummies','Doua Lowe','9781119247791','John Wiley & Sons Inc','Available');
INSERT INTO `books` (`book_ID`,`title`,`author`,`ISBN`,`publisher`,`status`) VALUES (1002,'SQL All-In-One for Dummies','Allen G. Taylor','9780470929964','John Wiley & Sons Inc','Available');
INSERT INTO `books` (`book_ID`,`title`,`author`,`ISBN`,`publisher`,`status`) VALUES (1003,'MySQL','Paul Dubois','9780321833877','Pearson Education (US)','On loan');
INSERT INTO `books` (`book_ID`,`title`,`author`,`ISBN`,`publisher`,`status`) VALUES (1004,'SQL','Chris Fehilv','0321118030','Peachoit Press','Available');
INSERT INTO `books` (`book_ID`,`title`,`author`,`ISBN`,`publisher`,`status`) VALUES (1005,'From Access to SQL server','Russell Sinclair','9781430211310','Apress','Available');
INSERT INTO `books` (`book_ID`,`title`,`author`,`ISBN`,`publisher`,`status`) VALUES (1006,'Oracle SQL : jumpstart with examples','Gavin Powell','1555583237','Elsevier Digital Press','On loan');
INSERT INTO `books` (`book_ID`,`title`,`author`,`ISBN`,`publisher`,`status`) VALUES (1007,'Learning SQL','Alan Beaulieu','0596007272','O\'Reilly Media','On loan');
INSERT INTO `books` (`book_ID`,`title`,`author`,`ISBN`,`publisher`,`status`) VALUES (1008,'Oracle PL/SQL programming','Steven Feuerstein','9781565921429','O\'Reilly & Associates','Available');
INSERT INTO `books` (`book_ID`,`title`,`author`,`ISBN`,`publisher`,`status`) VALUES (1009,'Beginning T-SQL 2012','Scott Shaw','9781430237051','Apress','Available');
INSERT INTO `books` (`book_ID`,`title`,`author`,`ISBN`,`publisher`,`status`) VALUES (1010,'Joe Celko\'s SQL for smarties advanced SQL programm','Joe Celko','9780123693792','Morgan Kaufmann','Lost');
INSERT INTO `books` (`book_ID`,`title`,`author`,`ISBN`,`publisher`,`status`) VALUES (1011,'SQL : the complete reference','James R Groff','0072225599','Osborne/McGraw-Hill','On loan');
INSERT INTO `books` (`book_ID`,`title`,`author`,`ISBN`,`publisher`,`status`) VALUES (1012,'Beginning SQL server 2000 programming','Robin Dewson','9781430208044','Wrox Press','Available');
INSERT INTO `books` (`book_ID`,`title`,`author`,`ISBN`,`publisher`,`status`) VALUES (1013,'Oracle PL/SQL best practices','Steven Feuerstein','9780596001216','Cambridge Mass. : O\'Reilly','Available');
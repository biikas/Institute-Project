/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 8.0.23 : Database - CONNECTNPAY_TEMPLATE
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

USE CONNECTNPAY_TEMPLATE;

/*Table structure for table `USER_GROUP` */

DROP TABLE IF EXISTS `USER_GROUP`;

CREATE TABLE `USER_GROUP` (
                              `ID` bigint NOT NULL AUTO_INCREMENT,
                              `CREATED_DATE` datetime NOT NULL,
                              `LAST_MODIFED_DATE` datetime DEFAULT NULL,
                              `ACTIVE` char(1) NOT NULL,
                              `DESCRIPTION` varchar(255) DEFAULT NULL,
                              `NAME` varchar(80) NOT NULL,
                              `REMARKS` varchar(255) DEFAULT NULL,
                              `CREATED_BY` bigint DEFAULT NULL,
                              `LAST_MODIFIED_BY` bigint DEFAULT NULL,
                              `PARENT_ID` bigint DEFAULT NULL,
                              PRIMARY KEY (`ID`),
                              KEY `FK8lmiibrbnk2q2yhkr5pr6r34v` (`CREATED_BY`),
                              KEY `FKqwdo3g1a9j6x8gbm02nx623v2` (`LAST_MODIFIED_BY`),
                              KEY `FKsgeq9br1ngbsdbt0ces70cfqw` (`PARENT_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `USER_GROUP` */

insert  into `USER_GROUP`(`ID`,`CREATED_DATE`,`LAST_MODIFED_DATE`,`ACTIVE`,`DESCRIPTION`,`NAME`,`CREATED_BY`,`LAST_MODIFIED_BY`,`PARENT_ID`) values
(1,'2021-01-05 13:26:47','2021-01-05 13:39:57','Y','Super user of this product','Super Admin',1,1,NULL),
(2,'2021-02-01 12:52:21','2021-02-01 13:04:05','Y','Customer service','Customer service',1,1,1),
(3,'2021-02-01 13:14:32',NULL,'Y','Finance','Finance',1,NULL,1),
(4,'2021-02-01 13:18:09',NULL,'Y','Business','Business',1,NULL,1),
(5,'2021-02-01 13:21:11',NULL,'Y','Tech support','Tech support',1,NULL,1),
(6,'2021-02-01 13:23:44',NULL,'Y','Client','Client',1,NULL,1);

/*Table structure for table `USER_MENU` */

DROP TABLE IF EXISTS `USER_MENU`;

CREATE TABLE `USER_MENU` (
                             `ID` bigint NOT NULL AUTO_INCREMENT,
                             `ACTION` varchar(50) DEFAULT NULL,
                             `ACTIVE` char(1) DEFAULT NULL,
                             `DESCRIPTION` varchar(100) DEFAULT NULL,
                             `ICON` varchar(50) DEFAULT NULL,
                             `ICON_ACTIVE` varchar(100) DEFAULT NULL,
                             `IS_MULTILEVEL` char(1) NOT NULL,
                             `NAME` varchar(100) DEFAULT NULL,
                             `NAV_TYPE` varchar(100) DEFAULT NULL,
                             `PARENT_ID` bigint DEFAULT NULL,
                             `PATH` varchar(255) DEFAULT NULL,
                             `SORT_ORDER` int DEFAULT NULL,
                             PRIMARY KEY (`ID`),
                             KEY `FKp8p2no6yelmtjyld70otsom5i` (`PARENT_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

/*Data for the table `USER_MENU` */

insert  into `USER_MENU`(`ID`,`ACTION`,`ACTIVE`,`DESCRIPTION`,`ICON`,`ICON_ACTIVE`,`IS_MULTILEVEL`,`NAME`,`NAV_TYPE`,`PARENT_ID`,`PATH`,`SORT_ORDER`) values
(1,'DASHBOARD','Y','Dashboard','home','home-fill','N','Dashboard','side',NULL,'dashboard',1),
(2,'GROUP_PERMISSION','Y','Group Permissions','certificate','certificate-fill','N','Group Permissions','side',NULL,'user-group',2),
(3,'VIEW_GROUP_PERMISSION','Y','View Group Permissions',NULL,NULL,'N','View Group Permissions',NULL,2,NULL,0),
(4,'CREATE_GROUP_PERMISSION','Y','Create Group Permissions',NULL,NULL,'N','Create Group Permissions',NULL,2,NULL,0),
(5,'MANAGE_GROUP_PERMISSION','Y','Manage Group Permissions',NULL,NULL,'N','manage Group Permissions',NULL,2,NULL,0),
(6,'USER','Y','User','users','users-fill','N','Users','side',NULL,'user',3),
(7,'VIEW_USER','Y','View User',NULL,NULL,'N','View User',NULL,3,NULL,0),
(8,'CREATE_USER','Y','Create User',NULL,NULL,'N','Create User',NULL,3,NULL,0),
(9,'MANAGE_USER','Y','Manage User',NULL,NULL,'N','Manage User',NULL,3,NULL,0);

/*Table structure for table `USER_GROUP_PERMISSION` */

DROP TABLE IF EXISTS `USER_GROUP_PERMISSION`;

CREATE TABLE `USER_GROUP_PERMISSION` (
                                         `ID` bigint NOT NULL AUTO_INCREMENT,
                                         `CREATED_DATE` datetime NOT NULL,
                                         `LAST_MODIFIED_DATE` datetime DEFAULT NULL,
                                         `ENABLED` char(1) DEFAULT NULL,
                                         `CREATED_BY` bigint DEFAULT NULL,
                                         `LAST_MODIFIED_BY` bigint DEFAULT NULL,
                                         `USER_GROUP_ID` bigint NOT NULL,
                                         `USER_MENU_ID` bigint NOT NULL,
                                         PRIMARY KEY (`ID`),
                                         KEY `FK1rrx4wnddo3fl79mqiwxy2bvc` (`CREATED_BY`),
                                         KEY `FK45ld1130memok2yptnjs1h92t` (`LAST_MODIFIED_BY`),
                                         KEY `FKpvewqags2qqto6e9afvwadde` (`USER_GROUP_ID`),
                                         KEY `FKsnbutkx23w1hdg74hcyusic3t` (`USER_MENU_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=172 DEFAULT CHARSET=utf8;

/*Data for the table `USER_GROUP_PERMISSION` */

insert  into `USER_GROUP_PERMISSION`(`ID`,`CREATED_DATE`,`LAST_MODIFED_DATE`,`ENABLED`,`CREATED_BY`,`LAST_MODIFIED_BY`,`USER_GROUP_ID`,`USER_MENU_ID`) values
(1,'2021-01-29 21:54:45',NULL,'Y',1,1,1,1),
(2,'2021-01-29 21:54:45',NULL,'Y',1,1,1,2),
(3,'2021-01-29 21:54:45',NULL,'Y',1,1,1,3),
(4,'2021-01-29 21:54:45',NULL,'Y',1,1,1,4),
(5,'2021-01-29 21:54:45',NULL,'Y',1,1,1,5),
(6,'2021-01-29 21:54:45',NULL,'Y',1,1,1,6),
(7,'2021-01-29 21:54:45',NULL,'Y',1,1,1,7),
(8,'2021-01-29 21:54:45',NULL,'Y',1,1,1,8),
(9,'2021-01-29 21:54:45',NULL,'Y',1,1,1,9);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS *notification/;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

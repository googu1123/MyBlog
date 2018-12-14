
CREATE DATABASE myblog;

CREATE TABLE `tb_board` (
  `seq` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid` varchar(30) NOT NULL DEFAULT '',
  `username` varchar(50) NOT NULL DEFAULT '',
  `title` varchar(250) NOT NULL DEFAULT '',
  `category` char(1) NOT NULL DEFAULT '',
  `contents` longtext,
  `filename` varchar(250) NULL DEFAULT '',
  `regdate` datetime DEFAULT NULL,
  `modifyDate` datetime DEFAULT NULL,
  `readcnt` int(11) DEFAULT NULL,
  `replycnt` int(11) DEFAULT NULL,
   PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8;


CREATE TABLE `tb_user` (
 `seq` int(10) NOT NULL AUTO_INCREMENT,
 `userid` varchar(50) NOT NULL,
 `pwd` varchar(128) NOT NULL,
 `username` varchar(50) DEFAULT NULL,
 `email` varchar(150) DEFAULT NULL,
 `grade` char(1) NOT NULL,
 `regdate` datetime DEFAULT NULL,
 `last_login` datetime DEFAULT NULL,
 PRIMARY KEY (`seq`),
 KEY `userid` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


insert into tb_user (userid, pwd, username, email , grade, regdate, last_login)
value
('googu1123', '1123', '관리자', 'googu1123@gmail.com', '9', now(), now());


CREATE TABLE `tb_category` (
 `seq` int(10) NOT NULL AUTO_INCREMENT,
 `code` varchar(10) NOT NULL,
 `codename` varchar(128) NOT NULL,
 `sortnum` int(10) NOT NULL,
 `regdate` datetime DEFAULT NULL,
 PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

insert into tb_category (codename, sortnum, regdate) values ('JAVA',1,now());
insert into tb_category (codename, sortnum, regdate) values ('LINUX',2,now());
insert into tb_category (codename, sortnum, regdate) values ('DB',3,now());
insert into tb_category (codename, sortnum, regdate) values ('ANDROID',4,now());
insert into tb_category (codename, sortnum, regdate) values ('Mac OS X',5,now());
insert into tb_category (codename, sortnum, regdate) values ('기타',6,now());
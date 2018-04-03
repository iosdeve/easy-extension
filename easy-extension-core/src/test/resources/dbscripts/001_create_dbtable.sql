CREATE TABLE `app` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appName` varchar(100) DEFAULT NULL,
  `actionClass` varchar(100) DEFAULT NULL,
  `appPath` varchar(100) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `created` date DEFAULT NULL,
  `lastUpdated` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

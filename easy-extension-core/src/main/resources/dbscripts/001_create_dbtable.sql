Create Table If Not Exists `app` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appName` varchar(100) DEFAULT NULL,
  `actionClass` varchar(100) DEFAULT NULL,
  `appPath` varchar(100) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `lastUpdated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
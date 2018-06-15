CREATE TABLE `app` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appName` varchar(100) DEFAULT NULL,
  `appDesc` varchar(800) DEFAULT NULL,
  `actionClass` varchar(100) DEFAULT NULL,
  `appPath` varchar(100) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `lastUpdated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

CREATE TABLE `files` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appId` int(11) DEFAULT NULL,
  `fileName` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `filesize` int(11) DEFAULT NULL,
  `filePath` varchar(150) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `lastUpdated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

CREATE TABLE `nodetable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `referId` int(11) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `lastUpdated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;


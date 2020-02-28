--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `idUser` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` VALUES (2,'john'),(1,'bob');

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `idItem` int(11) NOT NULL,
  `idUser` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `game` varchar(45) DEFAULT NULL,
  `expirationDate` date DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`idItem`),
  KEY `FK_idItem_idUser` (`idUser`),
  CONSTRAINT `FK_idItem_idUser` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `item`
--

INSERT INTO `item` VALUES (1,1,'item1','game1','2012-08-12',1),(2,1,'item2','game2','2012-08-29',2),(3,2,'item3','game1','2012-08-20',3);

--
-- Table structure for table `property`
--

DROP TABLE IF EXISTS `property`;
CREATE TABLE `property` (
  `idProperty` int(11) NOT NULL,
  `idItem` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `value` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idProperty`),
  KEY `FK_idProperty_idItem` (`idItem`),
  CONSTRAINT `FK_idProperty_idItem` FOREIGN KEY (`idItem`) REFERENCES `item` (`idItem`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `property`
--

INSERT INTO `property` VALUES (1,1,'name1','value1'),(2,1,'name2','value2'),(3,2,'name3','value3');
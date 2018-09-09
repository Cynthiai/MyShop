create database store28;
use store28;
/**
 * 用户表
 */

CREATE TABLE `user` (
	`uid` varchar(32) NOT NULL,
	 `username` varchar(20) DEFAULT NULL,
	 `password` varchar(100) DEFAULT NULL,
	 `name` varchar(20) DEFAULT NULL,
	 `email` varchar(30) DEFAULT NULL,
	 `telephone` varchar(20) DEFAULT NULL,
	 `birthday` date DEFAULT NULL,
	 `sex` varchar(10) DEFAULT NULL,
	 `state` int(11) DEFAULT NULL,
	 `code` varchar(64) DEFAULT NULL,
	 PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 * 类目表
 */
CREATE TABLE `category` (
  `cid` varchar(32) NOT NULL,
  `cname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `category` VALUES ('1','手机数码'),
('172934bd636d485c98fd2d3d9cccd409','运动户外'),
('2','电脑办公'),('3','家具家居'),('4','鞋靴箱包'),('5','图书音像'),
('59f56ba6ccb84cb591c66298766b83b5','aaaa'),('6','母婴孕婴'),
('afdba41a139b4320a74904485bdb7719','汽车用品');

/**
 * 商品表
 */
CREATE TABLE `product` (
		  `pid` varchar(32) NOT NULL,
		  `pname` varchar(50) DEFAULT NULL,
		  `market_price` double DEFAULT NULL,
		  `shop_price` double DEFAULT NULL,
		  `pimage` varchar(200) DEFAULT NULL,
		  `pdate` date DEFAULT NULL,
		  `is_hot` int(11) DEFAULT NULL,
		  `pdesc` varchar(255) DEFAULT NULL,
		  `pflag` int(11) DEFAULT NULL,
		  `cid` varchar(32) DEFAULT NULL,
		  PRIMARY KEY (`pid`),
		  KEY `sfk_0001` (`cid`),
		  CONSTRAINT `sfk_0001` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8;



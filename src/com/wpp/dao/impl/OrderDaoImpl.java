package com.wpp.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.dbutils.QueryRunner;

import com.wpp.dao.OrderDao;
import com.wpp.domain.OrderItem;
import com.wpp.domain.Orders;
import com.wpp.utils.DataSourceUtils;

public class OrderDaoImpl implements OrderDao{

	@Override
	public void addOrder(Orders or) throws Exception {
		
		QueryRunner qr = new QueryRunner();
		Date date = or.getOrdertime();
		DateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");   //创建一个格式化日期对象
		String time = simpleDateFormat.format(date); 
		String sql="insert into orders values(?,?,?,?,?,?,?,?)";
		
			qr.update(DataSourceUtils.getConnection(),sql, or.getOid(),time,or.getTotal(),
					or.getState(),or.getAddress(),or.getName(),
					or.getTelephone(),or.getUser().getUid());
	}

	@Override
	public void addItem(OrderItem item) throws Exception {
		
		/**
		 * `itemid` varchar(32) NOT NULL,
		  `count` int(11) DEFAULT NULL,
		  `subtotal` double DEFAULT NULL,
		  `pid` varchar(32) DEFAULT NULL,
		  `oid` varchar(32) DEFAULT NULL,
		 */
		QueryRunner qr = new QueryRunner();
		
		String sql="insert into orderitem values (?,?,?,?,?)";
		
		qr.update(DataSourceUtils.getConnection(),sql, item.getItemid(),item.getCount(),item.getSubtotal(),
				item.getProduct().getPid(),item.getOrders().getOid());
		
	}

}

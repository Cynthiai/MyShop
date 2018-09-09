package com.wpp.service.impl;

import java.util.List;


import com.wpp.dao.OrderDao;
import com.wpp.dao.impl.OrderDaoImpl;
import com.wpp.domain.OrderItem;
import com.wpp.domain.Orders;
import com.wpp.service.OrderService;
import com.wpp.utils.DataSourceUtils;

public class OrderServiceImpl implements OrderService{

	/**
	 * 添加订单、订单项
	 * @throws Exception 
	 */
	@Override
	public void addOrder(Orders or) throws Exception  {
		
		//开启事务
		try {
			DataSourceUtils.startTransaction();
			//添加订单
			OrderDao od=new OrderDaoImpl();
			od.addOrder(or);
			//添加订单项
			List<OrderItem> items = or.getItems();
			for(OrderItem item : items) {
				
				od.addItem(item);
			}
			DataSourceUtils.commitAndClose();
		} catch (Exception e) {
			DataSourceUtils.rollbackAndClose();
			e.printStackTrace();
			throw e;
		}
		
	}

}

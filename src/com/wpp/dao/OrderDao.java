package com.wpp.dao;

import com.wpp.domain.OrderItem;
import com.wpp.domain.Orders;

public interface OrderDao {

	void addOrder(Orders or) throws Exception;

	void addItem(OrderItem item) throws Exception;

}

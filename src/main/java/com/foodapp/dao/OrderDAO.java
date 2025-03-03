package com.foodapp.dao;

import java.util.ArrayList;

import com.foodapp.model.Order;

public interface OrderDAO {
	
		int insertOrder(Order order);
	    Order fetchOrderById(int orderId);
	    ArrayList<Order> fetchAllOrders();
	    int updateOrder(Order order);
	    int deleteOrder(int orderId);
	}


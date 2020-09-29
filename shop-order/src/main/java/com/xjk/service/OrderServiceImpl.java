package com.xjk.service;

import com.xjk.mapper.OrderDao;
import com.xjk.shopcommon.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xujunkai
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;


    @Override
    public void save(Order order) {

        orderDao.save(order);
    }
}

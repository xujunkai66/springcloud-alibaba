package com.xjk.mapper;

import com.xjk.shopcommon.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xujunkai
 */

public interface OrderDao extends JpaRepository<Order,Integer> {
}

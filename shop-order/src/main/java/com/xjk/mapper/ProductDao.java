package com.xjk.mapper;

import com.xjk.shopcommon.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xujunkai
 */
public interface ProductDao extends JpaRepository<Product,Integer> {
}

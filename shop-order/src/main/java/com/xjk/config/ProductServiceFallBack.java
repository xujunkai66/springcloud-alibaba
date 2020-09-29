package com.xjk.config;

import com.xjk.service.ProductService;
import com.xjk.shopcommon.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Feign对Sentinel的支持的容错类（不包含具体错误）
 * 容错类要求必须实现被容错的接口,并为每个方法实现容错方案
 * @author xujunkai
 */
@Component
@Slf4j
public class ProductServiceFallBack implements ProductService {
    @Override
    public Product findByPid(Integer pid) {
        Product product = new Product();
        product.setPid(-1);
        return product;
    }
}

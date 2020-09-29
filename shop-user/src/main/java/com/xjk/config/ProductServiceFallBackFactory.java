package com.xjk.config;

import com.xjk.service.ProductService;
import com.xjk.shopcommon.entity.Product;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * feign对Sentinel的支持的容错类（拿到具体的错误）
 * @author xujunkai
 */
@Component
public class ProductServiceFallBackFactory implements
        FallbackFactory<ProductService> {
    @Override
    public ProductService create(Throwable throwable) {
        return new ProductService() {
            @Override
            public Product findByPid(Integer pid) {
                throwable.printStackTrace();
                Product product = new Product();
                product.setPid(-2);
                return product;
            }
        };
    }
}

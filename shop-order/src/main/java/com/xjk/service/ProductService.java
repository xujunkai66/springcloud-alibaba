package com.xjk.service;

import com.xjk.config.FeignConfiguration;
import com.xjk.config.ProductServiceFallBackFactory;
import com.xjk.shopcommon.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xujunkai
 */
//value用于指定调用nacos下哪个微服务
//fallback用于指定容错类
@FeignClient(value="service-product",
        configuration = FeignConfiguration.class,
        //fallback = ProductServiceFallBack.class
        fallbackFactory = ProductServiceFallBackFactory.class
)
public interface ProductService {
    //指定调用提供者的哪个方法
    //@FeignClient+@GetMapping 就是一个完整的请求路径 http://service-product/product/{pid}
    @GetMapping(value = "/product/{pid}")
    Product findByPid(@PathVariable("pid") Integer pid);
}

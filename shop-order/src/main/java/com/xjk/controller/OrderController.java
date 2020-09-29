package com.xjk.controller;

import com.alibaba.fastjson.JSON;
import com.xjk.service.OrderService;
import com.xjk.service.ProductService;
import com.xjk.shopcommon.entity.Order;
import com.xjk.shopcommon.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author xujunkai
 */
@RestController

public class OrderController {
    private final Logger log = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrderService orderService;
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ProductService productService;


    //准备买1件商品
    @GetMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid,@RequestHeader("Authorization") String authToken) {
//        log.info(">>客户下单，这时候要调用商品微服务查询商品信息");

      /*  //从nacos中获取服务地址
        //自定义规则实现随机挑选服务
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        int index = new Random().nextInt(instances.size());
        ServiceInstance serviceInstance =
                discoveryClient.getInstances("service-product").get(index);
        String url = serviceInstance.getHost() + ":" +
                serviceInstance.getPort();
        log.info(">>从nacos中获取到的微服务地址为:" + url);*/

/*        //直接使用微服务名字， 从nacos中获取服务地址
        String url = "service-product";
        //通过restTemplate调用商品微服务
        Product product = restTemplate.getForObject(
                "http://"+url+"/product/" + pid, Product.class);*/

        log.info("接收到{}号商品的下单请求,接下来调用商品微服务查询此商品信息", pid);
        //通过fegin调用商品微服务
        Product product = productService.findByPid(pid);
//        Product product = new Product();
//        product.setPid(3333333);
        if (product.getPid() == -1) {
            Order order = new Order();
            order.setPname("下单失败");
            return order;
        }
        log.info("查询到{}号商品的信息,内容是:{}", pid, JSON.toJSONString(product));

        //下单(创建订单)
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        orderService.save(order);
        log.info("创建订单成功,订单信息为{}", JSON.toJSONString(order));
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return order;
    }
}

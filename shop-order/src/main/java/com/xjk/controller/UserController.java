package com.xjk.controller;

import com.xjk.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author xujunkai
 */
@RestController

public class UserController {
    private final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrderService orderService;
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    //private ProductService productService;


    //准备买1件商品
    @GetMapping("/user/login")
    public String login() {
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

        log.info("接收到{}号商品的下单请求,接下来调用商品微服务查询此商品信息" );


        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "order";
    }

}

package com.xjk.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.xjk.service.OrderServiceImpl3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * sentinel
 *
 * @author xujunkai
 */
@RestController
@Slf4j
public class OrderController3 {
    @Autowired
    private OrderServiceImpl3 orderServiceImpl3;

    @RequestMapping("/order/message1")
    public String message1() {
        String message = orderServiceImpl3.message();
        return message;
    }

    int i = 0;

    @RequestMapping("/order/message2")
    public String message2() {
        i++;
        //异常比例为0.333
        if (i % 3 == 0) {
            throw new RuntimeException();
        }

        return "message2";
    }

    @RequestMapping("/order/message3")
    @SentinelResource("message3")//注意这里必须使用这个注解标识,热点规则不生效
    public String message3(String name, Integer age) {
        return name + age;
    }

}


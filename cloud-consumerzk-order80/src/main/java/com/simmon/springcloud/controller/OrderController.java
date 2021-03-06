package com.simmon.springcloud.controller;

import com.simmon.springcloud.entities.Payment;
import com.simmon.springcloud.entities.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author: simmon
 * @description: TODO
 * @date: 2020/7/29 18:02
 */
@RestController
@Slf4j
public class OrderController {

    public static final String PAYMENT_URL = "http://cloud-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/consumer/payment/create")
    public ResponseResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, ResponseResult.class);
    }

    @GetMapping("/consumer/payment/{id}")
    public ResponseResult<Payment> getPayment(@PathVariable Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/" + id, ResponseResult.class);
    }

    @GetMapping("/consumer/payment/zk")
    public String getZk() {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/zk", String.class);
    }

}

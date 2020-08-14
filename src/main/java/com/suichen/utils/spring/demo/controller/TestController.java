package com.suichen.utils.spring.demo.controller;

import com.suichen.utils.spring.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public void test(@RequestBody Map<String, String> map) {
        System.out.println();
    }

    @RequestMapping(value = "/transaction")
    public void testService() {
        transactionService.insertData();
    }
}

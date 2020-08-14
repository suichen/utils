package com.suichen.utils.spring.openapi.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Think on 2018/6/12.
 */
@RestController
public class CheckStatusApi {
    @RequestMapping("/check_status")
    public String checkStatus() {
        return "success";
    }

    @RequestMapping("/check_status3")
    public String checkStatus3() {
        return "success";
    }

}

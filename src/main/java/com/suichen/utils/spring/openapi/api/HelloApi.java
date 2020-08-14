package com.suichen.utils.spring.openapi.api;

import com.suichen.utils.spring.openapi.framework.OpenApi;
import com.suichen.utils.spring.openapi.framework.OpenApiParams;
import com.suichen.utils.spring.openapi.framework.OpenApiType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Think on 2017/8/5.
 */
@RestController
public class HelloApi {
    @OpenApi(OpenApiType.helloOpenApi)
    @ResponseBody
    public void helloApi(@OpenApiParams(value = "hello", required = false) String hello) {
        System.out.println("heelllo value is "+hello);
    }
}

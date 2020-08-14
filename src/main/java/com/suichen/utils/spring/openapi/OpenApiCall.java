package com.suichen.utils.spring.openapi;


import com.suichen.utils.spring.openapi.framework.OpenApiResult;
import com.suichen.utils.spring.openapi.framework.OpenApiService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Think on 2017/7/29.
 */
@Controller
public class OpenApiCall {
    @RequestMapping("/demo/openapi/{openType}.json")
    @ResponseBody
    public OpenApiResult call(@PathVariable(value = "openType") String openType,
                              @RequestParam(required = false) Map<String, Object> pathParams,
                              @RequestBody(required = false) Map<String, Object> bodyParams,
                              @RequestHeader(required = false) Map<String, Object> headerParams,
                              HttpServletRequest httpServletRequest) {


        openApiService.call(openType, pathParams, bodyParams, headerParams, httpServletRequest.getMethod());
        return null;
    }

    @Resource
    private OpenApiService openApiService;
}

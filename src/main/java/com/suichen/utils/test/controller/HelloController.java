package com.suichen.utils.test.controller;

import com.suichen.utils.test.service.HelloServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/test")
public class HelloController {
    @RequestMapping("/helloAop")
    public void testAop() {
        helloService.testAop();
    }

    @RequestMapping("/test/cookie")
    public String cookie(@RequestParam("browser") String browser, HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession();
        Object sessionBrowser = httpSession.getAttribute("browser");
        if (sessionBrowser == null) {
            System.out.println("不存在session，设置browser= " + browser);
            httpSession.setAttribute("browser", browser);
        } else {
            System.out.println("存在session，browser= " + sessionBrowser.toString());
        }
        //class org.springframework.session.web.http.SessionRepositoryFilter$SessionRepositoryRequestWrapper
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + ":" + cookie.getValue());
            }
        }

        Cookie cookie = new Cookie("mixCtpSession", "123");
        cookie.setPath("/");
        response.addCookie(cookie);
        return "OK";
    }

    @Resource
    private HelloServiceImpl helloService;
}

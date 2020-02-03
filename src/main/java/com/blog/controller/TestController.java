package com.blog.controller;

import com.blog.common.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shaow
 * @description 测试控制层
 * @createTime 2/3/2020 2:45 PM
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/hello")
    public Result hello(){
        return Result.success("helloworld");
    }
}

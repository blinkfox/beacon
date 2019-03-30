package com.blinkfox.beacon.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 首页的控制器类.
 *
 * @author chenjiayin on 2019/3/20.
 */
@Api(tags = "首页接口")
@RequestMapping
@Controller
public class IndexController {

    /**
     * 转向首页index.html.
     *
     * @return index.html
     */
    @GetMapping
    @ApiOperation(value = "转向首页的接口")
    public String index() {
        return "index.html";
    }

    /**
     * say hello world.
     *
     * @return string
     */
    @GetMapping("/hello")
    @ResponseBody
    @ApiOperation(value = "Say Hello的示例接口")
    public ResponseEntity<String> say() {
        return ResponseEntity.ok("Hello Beacon!");
    }

}

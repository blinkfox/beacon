package com.blinkfox.beacon.controller;

import com.blinkfox.beacon.exception.BeaconException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 首页的控制器类.
 *
 * @author chenjiayin on 2019/3/20.
 */
@RequestMapping
@Controller
public class IndexController {

    /**
     * 转向首页index.html.
     *
     * @return index.html
     */
    @GetMapping
    public String index() {
        return "index.html";
    }

    /**
     * say hello world.
     *
     * @return string
     */
    @ResponseBody
    @GetMapping("/hello")
    public ResponseEntity<String> say() {
        return ResponseEntity.ok("Hello Beacon!");
    }

    /**
     * say hello world.
     *
     * @param name 名称
     * @return string
     */
    @ResponseBody
    @GetMapping("/exception")
    public ResponseEntity<String> exception(@RequestParam("name") String name) {
        if (StringUtils.isEmpty(name)) {
            throw new BeaconException("测试异常情况.");
        }
        return ResponseEntity.ok("Hello " + name);
    }

}

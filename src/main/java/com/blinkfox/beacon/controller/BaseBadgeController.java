package com.blinkfox.beacon.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * 处理徽章 Badge 请求相关的通用基础控制器类.
 *
 * @author blinkfox on 2019-03-31.
 */
public class BaseBadgeController {

    /**
     * message 没找到的提示的常量字符串.
     */
    static final String MSG_NOT_FOUND = "message not found";

    /**
     * 构造方法.
     */
    protected BaseBadgeController() {}

    /**
     * 该实例全局的 HttpHeaders，设置返回内容为 svg.
     */
    protected static HttpHeaders svgHeader;

    static {
        svgHeader = new HttpHeaders();
        svgHeader.setContentType(MediaType.valueOf("image/svg+xml"));
    }

}

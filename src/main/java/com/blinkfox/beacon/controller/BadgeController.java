package com.blinkfox.beacon.controller;

import com.blinkfox.beacon.bean.Badge;
import com.blinkfox.beacon.service.BadgeService;
import com.blinkfox.beacon.utils.ColorKit;
import com.blinkfox.beacon.utils.StyleKit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 徽章相关操作的控制器.
 *
 * @author blinkfox on 2019-03-28.
 */
@Api(tags = "徽章接口")
@RestController
@RequestMapping("/badge")
public class BadgeController {

    @Resource
    private BadgeService badgeService;

    /**
     * 该实例全局的 HttpHeaders，设置返回内容为 svg.
     */
    private static HttpHeaders svgHeader;

    static {
        svgHeader = new HttpHeaders();
        svgHeader.setContentType(MediaType.valueOf("image/svg+xml"));
    }

    /**
     * 使用`-`的URL风格制作 svg 徽章的接口.
     * <p>如：/badge/Hello-social-green.svg?style=social&labelColor=333</p>
     *
     * @param label 徽章左边的标签
     * @param message 徽章左边的信息
     * @param color 徽章右边的颜色
     * @param labelColor 徽章左边的颜色
     * @param style 徽章风格
     * @return svg字符串
     */
    @ApiOperation(value = "使用`-`的URL风格制作徽章的接口", notes = "通过 label, message, color 等参数来生成徽章的接口.")
    @GetMapping("/{label}-{message}-{color}.svg")
    public ResponseEntity<String> getBadgeByDash(@PathVariable("label") String label,
            @PathVariable("message") String message,
            @PathVariable(value = "color", required = false) String color,
            @RequestParam(value = "labelColor", required = false,
                    defaultValue = ColorKit.DEFAULT_LABEL_COLOR) String labelColor,
            @RequestParam(value = "style", required = false, defaultValue = StyleKit.DEFAULT_STYLE) String style) {
        return this.getSvgBadge(label, message, color, labelColor, style);
    }

    /**
     * 使用`/`的URL风格制作 svg 徽章的接口.
     * <p>如：/badge/Hello/my-social/green.svg?style=flat-square&labelColor=333</p>
     *
     * @param label 徽章左边的标签
     * @param message 徽章左边的信息
     * @param color 徽章右边的颜色
     * @param labelColor 徽章左边的颜色
     * @param style 徽章风格
     * @return svg字符串
     */
    @ApiOperation(value = "使用`/`的URL风格制作徽章的接口", notes = "通过 label, message, color 等参数来生成徽章的接口.")
    @GetMapping("/{label}/{message}/{color}.svg")
    public ResponseEntity<String> getBadgeBySlash(@PathVariable("label") String label,
            @PathVariable("message") String message,
            @PathVariable(value = "color", required = false) String color,
            @RequestParam(value = "labelColor", required = false,
                   defaultValue = ColorKit.DEFAULT_LABEL_COLOR) String labelColor,
            @RequestParam(value = "style", required = false, defaultValue = StyleKit.DEFAULT_STYLE) String style) {
        return this.getSvgBadge(label, message, color, labelColor, style);
    }

    /**
     * 将客户端接口传递过来参数进行封装，并调用 servce 生成 svg 徽章返回给客户端.
     *
     * @param label 徽章左边的标签
     * @param message 徽章左边的信息
     * @param color 徽章右边的颜色
     * @param labelColor 徽章左边的颜色
     * @param style 徽章风格
     * @return svg字符串
     */
    private ResponseEntity<String> getSvgBadge(String label, String message, String color,
            String labelColor, String style) {
        return new ResponseEntity<>(badgeService.generate(new Badge()
                .setLabel(label)
                .setMessage(message)
                .setColor(color)
                .setLabelColor(labelColor)
                .setStyle(style)), svgHeader, HttpStatus.OK);
    }

}

package com.blinkfox.beacon.service;

import com.blinkfox.beacon.bean.Badge;
import com.blinkfox.beacon.utils.ColorKit;
import com.blinkfox.beacon.utils.StyleKit;
import com.blinkfox.beacon.utils.TemplateKit;
import com.blinkfox.beacon.utils.TextWidthKit;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * 徽章生成相关的Service.
 *
 * @author blinkfox on 2019-03-30.
 */
@Service
public class BadgeService {

    /**
     * svg 文件后缀名.
     */
    private static final String SVG = ".svg";

    /**
     * 根据 Badge 相关参数构建生成徽章.
     *
     * @param badge Badge对象
     * @return 徽章的svg字符串
     */
    public String generate(Badge badge) {
        // 分别计算该徽章 label 和 message 的16进制颜色值、徽章的风格以及徽章左右文本所需要占的宽度.
        // 并将这些值存放到 map 中作为参数上下文传递给beetl, 从而渲染出svg模板.
        Map<String, Object> contextMap = new HashMap<>(8);
        contextMap.put("label", badge.getLabel());
        contextMap.put("message", badge.getMessage());
        contextMap.put("labelColor", ColorKit.getHexLabelColor(badge.getLabelColor()));
        contextMap.put("color", ColorKit.getHexColor(badge.getColor()));
        contextMap.put("leftWidth", TextWidthKit.calculate(badge.getLabel()));
        contextMap.put("rightWidth", TextWidthKit.calculate(badge.getMessage()));

        return TemplateKit.render(StyleKit.getStyle(badge.getStyle()) + SVG, contextMap);
    }

}

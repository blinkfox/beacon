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
        // 分别计算并设置该徽章 label 和 message 的16进制颜色值、徽章的风格以及徽章左右文本所需要占的宽度.
        badge.setLabelColor(ColorKit.getHexLabelColor(badge.getLabelColor()))
                .setColor(ColorKit.getHexColor(badge.getColor()))
                .setStyle(StyleKit.getStyle(badge.getStyle()))
                .setLeftWidth(TextWidthKit.calculate(badge.getLabel()))
                .setRightWidth(TextWidthKit.calculate(badge.getMessage()));

        // 将badge对象封装到Map中
        Map<String, Object> paramMap = new HashMap<>(4);
        paramMap.put("b", badge);
        return TemplateKit.render(badge.getStyle() + SVG, paramMap);
    }

}

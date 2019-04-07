package com.blinkfox.beacon.service;

import com.blinkfox.beacon.bean.Badge;
import com.blinkfox.beacon.consts.Const;
import com.blinkfox.beacon.utils.ColorKit;
import com.blinkfox.beacon.utils.ImageKit;
import com.blinkfox.beacon.utils.StyleKit;
import com.blinkfox.beacon.utils.TemplateKit;
import com.blinkfox.beacon.utils.TextWidthKit;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 徽章生成相关的Service.
 *
 * @author blinkfox on 2019-03-30.
 */
@Service
public class BadgeService {

    /**
     * 根据 Badge 相关参数构建生成徽章.
     * <p>使用了 caffeine 对接 SpringCache，cache 的 key 是 badge 对象的 toString() 的值.</p>
     *
     * @param badge Badge对象
     * @return 徽章的svg字符串
     */
    @Cacheable(cacheNames = "badge", key = "#badge.toString()")
    public String generate(Badge badge) {
        // 分别计算该徽章 label 和 message 的16进制颜色值、徽章的风格.
        // 并将这些值存放到 map 中作为参数上下文传递给beetl, 从而渲染出svg模板.
        Map<String, Object> contextMap = new HashMap<>(8);
        contextMap.put("label", badge.getLabel());
        contextMap.put("message", badge.getMessage());
        contextMap.put("labelColor", ColorKit.getHexLabelColor(badge.getLabelColor()));
        contextMap.put("color", ColorKit.getHexColor(badge.getColor()));

        // 计算 logo 的Base64 链接，及logo 所占宽度、和内边宽度，并总计出左右两边的总宽度.
        int labelWidth = TextWidthKit.calculate(badge.getLabel());
        String logo = ImageKit.getSvgLogoLink(badge.getLogo(), badge.getLogoTheme());
        int logoWidth = logo == null ? 0 : 14;
        int logoPadding = labelWidth == 0 || logo == null ? 0 : 3;
        contextMap.put("logo", logo);
        contextMap.put("logoWidth", logoWidth);
        contextMap.put("logoPadding", logoPadding);
        contextMap.put("leftWidth", labelWidth == 0 && logoWidth > 0 ? logoWidth + 10
                : labelWidth + logoWidth + logoPadding);
        contextMap.put("rightWidth", TextWidthKit.calculate(badge.getMessage()));

        // 渲染 对应 style 的 svg 模板，并直接返回结果.
        return TemplateKit.render(StyleKit.getStyle(badge.getStyle()) + Const.SVG, contextMap);
    }

}

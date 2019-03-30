package com.blinkfox.beacon.utils;

import java.util.HashSet;
import java.util.Set;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 徽章风格的常量和相关操作的工具类.
 *
 * @author blinkfox on 2019-03-30.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StyleKit {

    /**
     * 将 `flat` 作为默认的徽章风格.
     */
    public static final String DEFAULT_STYLE = "flat";

    /**
     * 存放各个徽章风格名称的 Set 集合，这里约定徽章风格的名称与 `svg` 模板名称保持一致.
     */
    private static final Set<String> styleSet = new HashSet<>();

    static {
        styleSet.add("flat");
    }

    /**
     * 获取有效的 svg 模板的名称，即也是徽章风格的名称.
     *
     * @param style 前端传过来的徽章风格的名称
     * @return 有效的徽章风格名称
     */
    public static String getStyle(String style) {
        return styleSet.contains(style) ? style : DEFAULT_STYLE;
    }

}

package com.blinkfox.beacon.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 颜色处理相关的工具类.
 *
 * @author blinkfox on 2019-03-29.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ColorKit {

    /**
     * 用于判断是否是3位或者6位的16进制颜色值（不含#）的正则表达式模式.
     */
    private static final Pattern colorPattern = Pattern.compile("[0-9a-fA-F]{6}|[0-9a-fA-F]{3}");

    /**
     * 将 `#555` 作为徽章 label 的默认颜色.
     */
    public static final String DEFAULT_LABEL_COLOR = "#555";

    /**
     * 将 `#4c1` 作为徽章 message 的默认颜色.
     */
    public static final String DEFAULT_COLOR = "#4c1";

    /**
     * 红色的key.
     */
    public static final String RED = "red";

    /**
     * 用红色来表示错误的颜色.
     */
    private static final String RED_COLOR = "#e05d44";

    /**
     * 存放颜色别称和其对应的颜色值之间映射关系的Map.
     */
    private static final Map<String, String> colorMap = new HashMap<>();

    static {
        colorMap.put("brightgreen", DEFAULT_COLOR);
        colorMap.put("green", "#97ca00");
        colorMap.put("yellow", "#dfb317");
        colorMap.put("yellowgreen", "#a4a61d");
        colorMap.put("orange", "#fe7d37");
        colorMap.put("red", RED_COLOR);
        colorMap.put("blue", "#007ec6");
        colorMap.put("grey", DEFAULT_LABEL_COLOR);
        colorMap.put("lightgrey", "#9f9f9f");
        colorMap.put("success", DEFAULT_COLOR);
        colorMap.put("informational", "#007ec6");
        colorMap.put("warn", "#dfb317");
        colorMap.put("error", RED_COLOR);
        colorMap.put("critical", RED_COLOR);
        colorMap.put("important", "#fe7d37");
        colorMap.put("inactive", "#9f9f9f");
        colorMap.put("blueviolet", "#7500da");
    }

    /**
     * 根据颜色别称获取其对应的 Label 的 16 进制的颜色值.
     *
     * @param alias 16进制颜色值或者颜色的别称
     * @return 16进制的颜色值
     */
    public static String getHexLabelColor(String alias) {
        return getHexColor(alias, DEFAULT_LABEL_COLOR);
    }

    /**
     * 根据颜色别称获取其对应的 Message 的 16进制的颜色值.
     *
     * @param alias 16进制颜色值或者颜色的别称
     * @return 16进制的颜色值
     */
    public static String getHexColor(String alias) {
        return getHexColor(alias, DEFAULT_COLOR);
    }

    /**
     * 根据别称和默认值获取其对应的16进制的颜色值，如果相关条件不满足，则直接返回默认值.
     *
     * @param alias 16进制颜色值或者颜色的别称
     * @return 16进制的颜色值
     */
    private static String getHexColor(String alias, String defaultColor) {
        if (alias == null || "".equals(alias)) {
            return defaultColor;
        }

        // 如果该颜色别称是在预定义好的颜色值，就直接返回该颜色值.
        String color = colorMap.get(alias.toLowerCase());
        if (color != null) {
            return color;
        }

        // 如果该别称符合16进制的颜色值，说明就是颜色，直接返回该16进制的颜色值，否则返回默认颜色即可.
        return colorPattern.matcher(alias).matches() ? "#" + alias : defaultColor;
    }

}

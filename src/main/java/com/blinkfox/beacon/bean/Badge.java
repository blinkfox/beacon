package com.blinkfox.beacon.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 用来表示徽章相关属性的实体类.
 *
 * @author blinkfox on 2019-03-30.
 */
@Getter
@Setter
@Accessors(chain = true)
public class Badge {

    /**
     * 标签文本，即徽章左边的文字.
     */
    private String label;

    /**
     * 标签对应的信息内容，即徽章右边的文字.
     */
    private String message;

    /**
     * 徽章左边标签的颜色，默认值是`#555`.
     */
    private String labelColor;

    /**
     * 徽章信息内容的主颜色，这里指的是徽章右边的颜色.
     */
    private String color;

    /**
     * 徽章的样式风格，默认值是`flat`.
     */
    private String style;

}

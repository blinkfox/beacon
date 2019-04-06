package com.blinkfox.beacon.utils;

import com.blinkfox.beacon.consts.Const;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;

/**
 * svg 图片相关的工具类.
 *
 * @author blinkfox on 2019-04-05.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ImageKit {

    /**
     * logo 颜色之白色.
     */
    public static final String WHITE = "white";

    /**
     * logo 颜色之黑色.
     */
    public static final String BLACK = "black";

    /**
     * 将 fontawesome logo 的Base64 的 href 链接全部存入到内存中，
     * 其中 key 为 logo icon 名称，value 为处理为 Base64之后的 href 的值.
     *
     * <p>注：由于总的图标所占空间还比较小，现在的内存完全够用，这里是全部的图标，不用做成 LRU缓存.</p>
     */
    static final Map<String, String> logoMap = new HashMap<>();

    /**
     * svg 中引用 svg Base64 的 href 形式的前缀常量.
     */
    private static final String XLINK_HREF = "data:image/svg+xml;base64,";

    /**
     * 斜线分隔符.
     */
    private static final String SLASH = "/";

    static {
        loadAllSvgLogos("logos/*/*.svg");
    }

    static void loadAllSvgLogos(String pattern) {
        try {
            // 读取到项目中的所有 svg logo 文件，将logo 文件中的内容全部转换为 href 可引用的 base64 字符串，
            // 然后将文件名作为 key，该 Base64 的 href 字符串的作为 value 存放到 logoMap 中.
            Resource[] svgResources = ResourcePatternUtils.getResourcePatternResolver(
                    new PathMatchingResourcePatternResolver()).getResources(pattern);
            for (Resource resource: svgResources) {
                try (InputStream in = resource.getInputStream()) {
                    logoMap.put(getSvgName(resource.getURL().getPath()),
                            XLINK_HREF + Base64.getEncoder().encodeToString(IOUtils.toByteArray(in)));
                }
            }
            log.info("加载读取所有的 svg logo 成功.");
        } catch (IOException e) {
            log.error("初始化读取 svg logos 出错，请检查！", e);
        }
    }

    /**
     * 根据 font awesome 中的 icon 图标，获取其对应的 Base64 形式页面可直接引用的 href 链接.
     * <p>如：data:image/svg+xml;base64,PD94bWwgdmVyc2lv ... L3N2Zz4=</p>
     *
     * @param logo fontawesome 的 icon 名称
     * @param logoColor LOGO的颜色
     */
    public static String getSvgLogoLink(String logo, String logoColor) {
        // 如果 logo 为空，则直接返回 null.
        if (logo == null || "".equals(logo)) {
            return null;
        }

        // 设置logo颜色只有黑白两种，并拼接字符串作为key去查找该logo对应的 base64 的 image link.
        return logoMap.get(new StringBuilder(BLACK.equals(logoColor) ? BLACK : WHITE)
                .append(SLASH).append(logo).append(Const.SVG).toString());
    }

    /**
     * 根据 svg 的 path 路径字符串获取其对应的包含父目录的 svg 名称.
     * <p>如输入：`/logos/white/github.svg`：`white/github.svg`</p>
     *
     * @param path svg路径
     * @return 含父目录的svg名称
     */
    private static String getSvgName(String path) {
        String[] nameArr = path.split(File.separator);
        int len = nameArr.length;
        return nameArr[len - 2] + SLASH + nameArr[len - 1];
    }

}

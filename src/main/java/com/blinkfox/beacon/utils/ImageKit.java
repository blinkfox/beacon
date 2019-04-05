package com.blinkfox.beacon.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
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
     * 将 fontawesome logo 的Base64 的 href 链接全部存入到内存中，
     * 其中 key 为 logo icon 名称，value 为处理为 Base64之后的 href 的值.
     *
     * <p>注：由于总的图标所占空间还比较小，现在的内存完全够用，这里是全部的图标，不用做成 LRU缓存.</p>
     */
    private static final Map<String, String> logoMap = new HashMap<>();

    /**
     * svg 中引用 svg Base64 的 href 形式的前缀常量.
     */
    private static final String XLINK_HREF = "data:image/svg+xml;base64,";

    static {
        init();
    }

    public static void init() {
        try {
            // 先创建用来存放所有 svg logo 的临时文件目录.
            File svgFileDir = new File(FileUtils.getTempDirectoryPath() + File.separator + "beacon-logos");
            FileUtils.forceMkdir(svgFileDir);

            // 读取到项目中的所有 svg logo 文件，将logo 文件中的内容全部转换为 href 可引用的 base64 字符串，
            // 然后将文件名作为 key，该 Base64 的 href 字符串的作为 value 存放到 logoMap 中.
            Resource[] svgResources = ResourcePatternUtils.getResourcePatternResolver(new PathMatchingResourcePatternResolver())
                    .getResources("logos/*.svg");
            for (Resource resource: svgResources) {
                try (InputStream in = resource.getInputStream()) {
                    logoMap.put(FilenameUtils.removeExtension(resource.getFilename()),
                            XLINK_HREF + Base64.getEncoder().encodeToString(IOUtils.toByteArray(in)));
                }
            }
            log.info("加载读取所有的 svg logo 成功， Map为: {}.", logoMap);
        } catch (IOException e) {
            log.error("初始化读取 svg logos 出错，请检查！", e);
        }
    }

    /**
     * 将font awesome 中的 icon 图标转换成 Base64 形式页面可直接引用的 href 链接.
     * <p>如：data:image/svg+xml;base64,PD94bWwgdmVyc2lv ... L3N2Zz4=</p>
     *
     * @param logo fontawesome 的 icon 名称
     */
    public static String svg2Base64Href(String logo) {
        return logo == null || "".equals(logo) ? null : logoMap.get(logo);
    }

    public static void main(String[] args) {
        log.info("开始...");
    }

}

package com.blinkfox.beacon.utils;

import com.blinkfox.beacon.exception.BeaconException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.DefaultResourceLoader;

/**
 * 加载并计算文本字符宽度的工具类.
 *
 * @author blinkfox on 2019-03-27.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TextWidthKit {

    /**
     * 各个字符及其对应宽度的资源文件名.
     */
    private static final String FILE_PATH = "char-width.txt";

    /**
     * 默认字符的宽度，通常是非ASCII字符的宽度的值.
     */
    private static final int DEFAULT_WIDTH = 11;

    /**
     * 用来存放每个 ASCII 字符串及其对应宽度的Map.
     */
    private static final Map<String, Integer> charWidthMap = new HashMap<>(256);

    static {
        // 加载 resources 目录下字符及其对应宽度的资源配置文件.
        loadCharWidthFile(FILE_PATH);
    }

    /**
     * 根据资源文件路径加载字符及其对应宽度的资源配置文件.
     *
     * @param filePath 文件路径名
     */
    static void loadCharWidthFile(String filePath) {
        try {
            // 读取jar包中的资源文件流，并转换为本地文件，方便后续的读写操作.
            File file = new File(FileUtils.getTempDirectory().getAbsolutePath() + File.separator +  filePath);
            FileUtils.forceMkdirParent(file);
            log.info("文件目录:{}", file.getAbsolutePath());
            FileUtils.copyInputStreamToFile(new DefaultResourceLoader().getResource(filePath).getInputStream(), file);

            // 按行读取文件中的内容，并将字符作为key，宽度作为value存放到charWidthMap集合中，完成后删除临时文件.
            FileUtils.readLines(file, StandardCharsets.UTF_8).forEach(s ->
                    charWidthMap.put(s.substring(0, 1), Integer.parseInt(substringAfterLast(s))));
            FileUtils.deleteQuietly(file);
        } catch (IOException e) {
            log.error("读取各字符及其对应的宽度的资源文件失败！", e);
            throw new BeaconException("读取各字符及其对应的宽度的资源文件失败！", e);
        }
    }

    /**
     * 截取出某字符串中英文冒号之后的字符串.
     *
     * @param str 待截取字符串
     * @return 截取后的字符串
     */
    private static String substringAfterLast(String str) {
        int pos = str.lastIndexOf(':');
        return (pos == -1 || pos == str.length() - 1) ? "" : str.substring(pos + 1);
    }

    /**
     * 计算字符串文本在 svg 中所占的宽度，包含了冗余的宽度.
     *
     * @param text 文本
     * @return 宽度值
     */
    public static int calculate(String text) {
        if (text == null || "".equals(text)) {
            return 0;
        }

        int width = 0;
        for (int i = 0, len = text.length(); i < len; i++) {
            Integer w = charWidthMap.get(String.valueOf(text.charAt(i)));
            width += (w == null ? DEFAULT_WIDTH : w);
        }
        return width % 2 == 0 ? width + 11 : width + 10;
    }

}

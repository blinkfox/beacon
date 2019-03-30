package com.blinkfox.beacon.utils;

import java.io.IOException;
import java.util.Map;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

/**
 * svg 模板生成工具类，这里使用高性能的 beetl 来输出模板.
 *
 * @author blinkfox on 2019-03-30.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TemplateKit {

    /**
     * Beetl 的 GroupTemplate 模版.
     */
    private static GroupTemplate groupTemplate;

    static {
        // 使用 beetl 初始化配置模板路径下的 svg 模板资源.
        try {
            Configuration cfg = Configuration.defaultConfiguration();
            cfg.setStatementStart("@");
            cfg.setStatementEnd(null);
            cfg.getResourceMap().put("autoCheck", "false");
            groupTemplate = new GroupTemplate(new ClasspathResourceLoader("/templates/svg/"), cfg);
        } catch (IOException e) {
            log.error("使用 beetl 初始化配置 svg 模版出错！", e);
        }
    }

    /**
     * 根据模版文件的相对路径名及其对应的上下文参数来渲染模版.
     *
     * @param filePath 文件路径
     * @param context 上下文参数
     * @return 渲染的字符串结果
     */
    public static String render(String filePath, Map<String, Object> context) {
        Template template = groupTemplate.getTemplate(filePath);
        template.binding(context);
        return template.render();
    }

}

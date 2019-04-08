package com.blinkfox.beacon.config;

import com.blinkfox.beacon.utils.TemplateKit;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 项目启动之后执行的类.
 *
 * @author blinkfox on 2019-04-08.
 */
@Slf4j
@Component
public class StartupRunner implements ApplicationRunner {

    /**
     * Callback used to run the bean.
     *
     * @param args incoming application arguments
     */
    @Override
    public void run(ApplicationArguments args) {
        // 初始化 beetl 模板和 svg icon 资源.
        TemplateKit.initTemplate();
        log.info("Beacon 服务已启动成功.");
    }

}

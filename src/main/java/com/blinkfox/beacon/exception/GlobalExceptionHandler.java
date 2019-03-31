package com.blinkfox.beacon.exception;

import com.blinkfox.beacon.bean.Badge;
import com.blinkfox.beacon.controller.BaseBadgeController;
import com.blinkfox.beacon.service.BadgeService;
import com.blinkfox.beacon.utils.ColorKit;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类.
 *
 * @author blinkfox on 2019-03-31.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends BaseBadgeController {

    @Resource
    private BadgeService badgeService;

    /**
     * 统一处理请求参数相关的异常的方法.
     *
     * @param e 异常实例
     * @return 异常提示的Badge
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, MissingPathVariableException.class,
            IllegalArgumentException.class})
    public ResponseEntity<String> handleRequestException(Exception e) {
        log.error("【异常处理】请求服务接口相关的参数不对，请检查!", e);
        return new ResponseEntity<>(badgeService.generate(new Badge()
                .setLabel(Integer.toString(HttpStatus.BAD_REQUEST.value()))
                .setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .setColor(ColorKit.RED)), svgHeader, HttpStatus.OK);
    }

    /**
     * 统一处理 Exception 的方法.
     *
     * @param e 异常实例
     * @return 异常提示的Badge
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        log.error("【异常处理】请求服务接口发生了异常，请检查!", e);
        return new ResponseEntity<>(badgeService.generate(new Badge()
                .setLabel(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .setColor(ColorKit.RED)), svgHeader, HttpStatus.OK);
    }

}

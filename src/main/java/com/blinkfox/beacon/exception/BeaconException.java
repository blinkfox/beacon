package com.blinkfox.beacon.exception;

/**
 * 本服务自定义的运行时异常.
 *
 * @author blinkfox on 2019-03-30.
 */
public class BeaconException extends RuntimeException {

    /**
     * 根据 Message 来构造异常实例.
     *
     * @param message 消息
     * @param e Throwable实例
     */
    public BeaconException(String message, Throwable e) {
        super(message, e);
    }

}

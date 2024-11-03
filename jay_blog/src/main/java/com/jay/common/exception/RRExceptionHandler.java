package com.jay.common.exception;

import com.jay.util.Data;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Created by xiang.wei on 2018/10/13
 *
 * @author xiang.wei
 */
@RestControllerAdvice
public class RRExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理自定义异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RRException.class)
    public Data handleRRException(RRException e) {
        return Data.failure(e.getCode(), e.getMsg());
    }

    /**
     * 404 异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Data handleNoFoundException(Exception e) {
        logger.error(e.getMessage(), e);
        return Data.failure(404, "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Data handleDuplicateKeyException(DuplicateKeyException e) {
        logger.error(e.getMessage(), e);
        return Data.failure("数据库中已存在该记录");
    }

    @ExceptionHandler(AuthorizationException.class)
    public Data handleAuthorizationException(AuthorizationException e) {
        logger.error(e.getMessage(), e);
        return Data.failure("没有权限，请联系管理员授权");
    }

    @ExceptionHandler(Exception.class)
    public Data handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return Data.failure(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Data handleArgumentException(IllegalArgumentException e) {
        logger.error(e.getMessage(), e);
        return Data.failure(e.getMessage());
    }
}

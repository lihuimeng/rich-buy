package com.niuzhuang.richbuy.common.mvc;

import com.niuzhuang.richbuy.common.dto.ActionResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: huimeng.li
 * @Description:
 * @Date: 2020/4/18 17:36
 */
@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandleAdvice {
    @ExceptionHandler
    public ActionResult handler(HttpServletRequest req, HttpServletResponse res, Exception e) {
        log.info("Restful Http请求发生异常...");



        return new ActionResult();
    }
}

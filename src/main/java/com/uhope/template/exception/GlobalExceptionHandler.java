package com.uhope.template.exception;

import com.uhope.base.constants.Constant;
import com.uhope.base.exception.NotLoginException;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.template.utils.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * 全局异常处理
 * 一般情况下，方法都有异常处理机制，但不能排除有个别异常没有处理，导致返回到前台，因此在这里做一个异常拦截，统一处理那些未被处理过的异常
 *
 * @author xiepuyao
 * @date Created on 2018/5/23
 */
@ControllerAdvice
@RestController
@RequestMapping
public class GlobalExceptionHandler extends AbstractErrorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public GlobalExceptionHandler(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @Value("${server.error.path:${error.path:/error}}")
    private static String errorPath;


    /**
     * sql异常
     *
     * @param req
     * @param rsp
     * @param ex
     * @return
     * @throws Exception
     */
    @ExceptionHandler(SQLException.class)
    public Result<String> sqlException(HttpServletRequest req, HttpServletResponse rsp, Exception ex) {
        LOGGER.error("!!! request uri:{} from {} server exception:{}", req.getRequestURI(), RequestUtil.getIpAddress(req), ex == null ? null : ex);
        return ResponseMsgUtil.builderResponse(Constant.RESULT_CODE_EXCEPTION, ex == null ? null : ex.getMessage(), null);
    }


    /**
     * 500错误.
     *
     * @param req
     * @param rsp
     * @param ex
     * @return
     * @throws Exception
     */
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Result<String> serverError(HttpServletRequest req, HttpServletResponse rsp, Exception ex) {
        LOGGER.error("!!! request uri:{} from {} server exception:{}", req.getRequestURI(), RequestUtil.getIpAddress(req), ex == null ? null : ex);
        return ResponseMsgUtil.exception();
    }


    /**
     * 404的拦截.
     *
     * @param request
     * @param response
     * @param ex
     * @return
     * @throws Exception
     */
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result<String> notFound(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        LOGGER.error("!!! request uri:{} from {} not found exception:{}", request.getRequestURI(), RequestUtil.getIpAddress(request), ex);
        return ResponseMsgUtil.builderResponse(Constant.RESULT_CODE_NO_EXISTS, "请求的资源不存在!", null);
    }

    /**
     * 未登录
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = NotLoginException.class)
    public Result<String> notLoginExceptionHandler(HttpServletRequest request, Exception ex) {
        LOGGER.error("!!! request uri:{} from {} not found exception:{}", RequestUtil.getIpAddress(request), ex);
        return ResponseMsgUtil.builderResponse(Constant.RESULT_CODE_NOT_LOGIN, ex.getMessage(), null);
    }

    /**
     * 重写/error请求
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "${server.error.path:${error.path:/error}}")
    public Result<String> handleErrors(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = getStatus(request);
        if (status == HttpStatus.NOT_FOUND) {
            return notFound(request, response, null);
        }
        return ResponseMsgUtil.exception();
    }


    @Override
    public String getErrorPath() {
        return errorPath;
    }
}

package com.uhope.template.config;

import com.uhope.base.constants.Constant;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Feign配置
 * 使用FeignClient进行服务间的调用，传递headers信息
 *
 * @author ChenBin on 2018/09/04
 */
@Configuration
public class FeignConfig implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //添加token
        requestTemplate.header(Constant.HTTP_HEADER_ACCESS_TOKEN, request.getHeader(Constant.HTTP_HEADER_ACCESS_TOKEN));
    }

}
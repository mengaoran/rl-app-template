package com.uhope.template.filter;

import com.uhope.base.constants.Constant;
import com.uhope.base.enums.EnumEnvType;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

/**
 * 再过滤器中添加token
 *
 * @author ChenBin on 2018/09/04
 */
public class AddTokenFilter implements Filter {

    /**
     * superAdmin
     */
    private static final String DEFAULT_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJsb2dpblRpbWUiOjE1MzAyNjA4NzUzNTgsImFwcElkIjoiMTIzNDU2IiwidXNlcklkIjoiOGQ2YmQ1NmMyNmIzMTFlOGFiMTFhYTZhMGNhYmU4YmEifQ.wiAEKBPrRVxLXgrMsfiZMRz-VSyO8qDONnLfncTYXbc";

    private String profilesActive;

    public AddTokenFilter(String profilesActive) {
        super();
        this.profilesActive = profilesActive;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // do nothing
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (profilesActive == null || !EnumEnvType.DEV.toString().equalsIgnoreCase(profilesActive)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        filterChain.doFilter(new CustomeizedRequest((HttpServletRequest) servletRequest), servletResponse);
    }

    @Override
    public void destroy() {
        // do nothing

    }

    private class CustomeizedRequest extends HttpServletRequestWrapper {

        /**
         * Constructs a request object wrapping the given request.
         *
         * @param request
         * @throws IllegalArgumentException if the request is null
         */
        public CustomeizedRequest(HttpServletRequest request) {
            super(request);
        }


        @Override
        public String getHeader(String name) {
            if (!Constant.HTTP_HEADER_ACCESS_TOKEN.equalsIgnoreCase(name)) {
                return super.getHeader(name);
            }
            String token = super.getHeader(name);
            return StringUtils.isNotBlank(token) ? token : DEFAULT_TOKEN;
        }
    }

}

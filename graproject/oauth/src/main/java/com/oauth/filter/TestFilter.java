package com.oauth.filter;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author pb
 */
@WebFilter(urlPatterns = "/oauth/token",filterName = "TokenFilter")
public class TestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入");
        filterChain.doFilter(servletRequest,servletResponse);
        if(servletResponse instanceof HttpServletResponse){
            HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;

        }

        System.out.println("退出");
    }
}

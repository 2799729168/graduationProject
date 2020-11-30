package com.oauth.filter;

import com.alibaba.fastjson.JSONObject;
import com.oauth.utils.MyRequestWrapper;
import com.oauth.utils.MyResponseWrapper;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author pb
 */
@WebFilter(urlPatterns = "/oauth/token",filterName = "TokenFilter")
public class CookieFilter implements Filter {
    private Jedis jedis;
    @Override
    public void init(FilterConfig filterConfig){
        jedis = new JedisPool().getResource();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String contentS = "";
        MyRequestWrapper myRequestWrapper = null;
        MyResponseWrapper myResponseWrapper = null;
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        if(servletRequest instanceof HttpServletRequest){
            request = (HttpServletRequest) servletRequest;
            myRequestWrapper = new MyRequestWrapper(request);
        }
        if(servletResponse instanceof HttpServletResponse){
            response = (HttpServletResponse) servletResponse;
            myResponseWrapper = new MyResponseWrapper(response);
        }
//        插入cookie
        String username = myRequestWrapper.getParameter("username");
        response.addCookie(new Cookie("username",username));
        filterChain.doFilter(myRequestWrapper,myResponseWrapper);
//        获取数据
        myResponseWrapper.getBuffer().flush();
        byte[] bytes = myResponseWrapper.getBuffer().toByteArray();
//        使用传入的response写入数据，自己封装过的写入传不到前端
        contentS = new String(bytes,"UTF-8");
        String access_token = null;
        try {
            JSONObject jsonObject = (JSONObject) JSONObject.parse(contentS);
            access_token = (String) jsonObject.get("access_token");
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap<String, String> token = new HashMap<>();
        token.put(username,access_token);
        jedis.hmset("access_token",token);
        servletResponse.getOutputStream().write(bytes);
        servletResponse.flushBuffer();
    }
}

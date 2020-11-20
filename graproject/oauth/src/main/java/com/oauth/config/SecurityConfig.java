package com.oauth.config;

import com.oauth.service.UserDetailService;
import org.apache.catalina.core.ApplicationFilterChain;
import org.apache.tomcat.websocket.server.WsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

/**
 * @author pb
 */
@EnableWebSecurity
@Configuration
//开启申明式资源权限
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    @Bean
//    public UserDetailsService userDetailsService(){
//        return new UserDetailService();
//    }

    /**
     * @param http
     *security基本配置
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//所有资源必须授权后访问
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
//关闭跨站请求防护
                .and().csrf().disable();
//        http.authorizeRequests()
//                .antMatchers("/r/**").authenticated()
//                .anyRequest().permitAll()
//                .and()
//                .formLogin()
//                .successForwardUrl("/success")
//                .and().csrf().disable();
    }

    /**
     * 配置密码编码器和用户
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailService()).passwordEncoder(passwordEncoder());
    }

    //AuthenticationManager对象在OAuth2认证服务中要使用，提取放入IOC容器中

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

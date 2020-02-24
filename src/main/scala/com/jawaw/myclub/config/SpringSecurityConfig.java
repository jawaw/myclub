package com.jawaw.myclub.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jawaw.myclub.vo.ResponseVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.cors.CorsUtils;

import java.io.PrintWriter;


@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("opSecurityServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new Pbkdf2PasswordEncoder("hadiaOo0QdhaIlm8"));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/register", "/banop","/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/", "/index", "/admin/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
                .and().rememberMe().tokenValiditySeconds(86400).key("remember-me-key")
                .and().formLogin().loginProcessingUrl("/login").permitAll()
                .successHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    ObjectMapper om = new ObjectMapper();
                    out.write(om.writeValueAsString(ResponseVO.success(authentication.getAuthorities())));
                })
                .failureHandler((request, response, exception) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    ObjectMapper om = new ObjectMapper();
                    out.write(om.writeValueAsString(ResponseVO.failure("", exception.getMessage())));
                })
                .and().logout().logoutUrl("/logout").permitAll()
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    ObjectMapper om = new ObjectMapper();
                    out.write(om.writeValueAsString(ResponseVO.success("注销成功")));
                })
                .and()
                .sessionManagement().invalidSessionUrl("/login")
                .and().cors().disable().csrf().disable();
    }


}

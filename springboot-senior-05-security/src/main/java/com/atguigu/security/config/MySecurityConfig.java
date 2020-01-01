package com.atguigu.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //定制请求的授权规则
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");

        //开启自动配置的登录功能，如果没有权限自动来到springSecurity的登录页面
        // 1、自动产生一个登录页面 /login
        // 2、认证失败重定向到 /login?error
        // 3、默认post形式的 /login 代表处理登录
        // 4、一点定制 loginPage，那么 /userLogin 的post请求就是处理登录
        http.formLogin().usernameParameter("user").passwordParameter("pwd").loginPage("/userLogin");

        //开启自动配置的注销功能
        // 1、访问 /logout 表示用户注销，清空session
        // 2、注销成功会到 /login?logou 可以修改此规则
        http.logout().logoutSuccessUrl("/"); //注销成功来到首页

        //开启记住我功能
        // 1、登录成功后，将cookie发给浏览器保存，以后登录带上cookie，只要通过检查就可以免登陆
        // 2、点击注销会删除cookie
        http.rememberMe().rememberMeParameter("remember");
    }

    //定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        auth.inMemoryAuthentication().withUser("zhangsan").password("123").roles("VIP1", "VIP2")
                .and().withUser("lisi").password("123").roles("VIP2", "VIP3")
                .and().withUser("wangwu").password("123").roles("VIP1", "VIP3");
    }
}

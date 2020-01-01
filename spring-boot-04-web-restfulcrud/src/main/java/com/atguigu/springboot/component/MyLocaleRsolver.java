package com.atguigu.springboot.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 可以在连接上携带上区域信息
 */
public class MyLocaleRsolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String loc = request.getParameter("loc");
        Locale locale = Locale.getDefault();
        if(!StringUtils.isEmpty(loc)){
            String[] split = loc.split("_");
            locale = new Locale(split[0], split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}

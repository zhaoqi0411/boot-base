package com.papaxiong.support.auth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class CookieUtils {


    public static Cookie getCookie(HttpServletRequest request, String name){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(Objects.equals(cookie.getName(),name)){
                return  cookie;
            }
        }
        return null;
    }
}

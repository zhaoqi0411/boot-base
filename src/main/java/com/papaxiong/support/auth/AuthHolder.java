package com.papaxiong.support.auth;


import com.alibaba.fastjson.JSON;
import com.papaxiong.model.po.SysUserDO;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author zhaoqi
 * @since 2020-11-15
 * 通用于 警员和犯人
 */
public class AuthHolder {

    public static ThreadLocal<Map<String, Object>> authHolder=new ThreadLocal<>();


    public static Map<String, Object> getMap() {
        return authHolder.get();
    }

    public static Object get(String key) {
        if (getMap() == null) {
            return null;
        }
        return getMap().get(key);
    }

    public static void set(String key, Object value) {
        Map<String, Object> map = getMap();
        if (Objects.isNull(map)) {
            map = new HashMap<>();
            authHolder.set(map);
        }
        map.put(key, value);
    }


    public static SysUserDO getSysUser() {
        if (get("user") == null) {
            return null;
        }
        String user = (String)get("user");
        Map parse = JSON.parseObject(user);
        SysUserDO userDO = new SysUserDO();
        userDO.setId(Long.valueOf((String) parse.get("userId")));
        userDO.setUserName((String) parse.get("userName"));
        userDO.setUserCode((String) parse.get("userCode"));
        userDO.setRealName((String) parse.get("realName"));
        return userDO;
    }


}



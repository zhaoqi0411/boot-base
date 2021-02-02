package com.papaxiong.support;


import com.papaxiong.support.model.BusinessBaseException;

/**
 * 提供包裹类
 * 用于给API 的返回
 *
 * @param <T>
 */
public class Wrapper<T> {

    private String code;

    private String msg;

    private T data;

    private String traceId;//zipkin trace id



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public static <T> Wrapper<T> ok(T res) {
        Wrapper<T> wp = new Wrapper<>();
        wp.setData(res);
        wp.setCode("200");
        wp.setMsg("OK");
        return wp;
    }

    public static <T> Wrapper<T> error(BusinessBaseException exception) {
        Wrapper<T> wp = new Wrapper<>();
        wp.setCode(exception.getCode());
        wp.setMsg(exception.getMessage());
        return wp;
    }

    public static <T> Wrapper<T> error(String code, String msg) {
        Wrapper<T> wp = new Wrapper<>();
        wp.setCode(code);
        wp.setMsg(msg);
        return wp;
    }

}

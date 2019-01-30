package com.zswl.common.base;

/**
 * Created by Administrator on 2018/6/11 0011.
 */

public class HttpResult<T> {
    private String code;
    private String msg;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isOk() {
        return "1".equals(code);
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

    @Override
    public String toString() {
        return "code:"+code+";msg:"+msg+";data"+data.toString();
    }
}

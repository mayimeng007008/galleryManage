package org.crown.common.utils;


import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import lombok.Data;

/**
 * ajax 回执
 */
@Data
public class JsonUtil {

    //默认成功
    private boolean flag = true;
    private Map<String, Object> msg;
    private JSONObject josnObj;
    private Integer status;
    private Object data;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public JsonUtil() {
    }

    public JsonUtil(boolean flag, Map<String, Object> msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public JsonUtil(boolean flag, Map<String, Object> msg, Integer status) {
        this.flag = flag;
        this.msg = msg;
        this.status = status;
    }

    /**
     * restful 返回
     */
    public static JsonUtil error(Map<String, Object> msg) {
        return new JsonUtil(false, msg);
    }

    public static JsonUtil sucess(Map<String, Object> msg) {
        return new JsonUtil(true, msg);
    }
}

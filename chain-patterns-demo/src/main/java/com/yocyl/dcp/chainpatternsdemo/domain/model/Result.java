package com.yocyl.dcp.chainpatternsdemo.domain.model;

import lombok.Data;

/**
 * @author: success
 * @date: 2025/10/18 12:34
 * @version: v1.0.0
 * @description: TODO
 **/
@Data
public class Result {

    private String code;

    private String msg;

    public static Result success(String msg) {
        Result result = new Result();
        result.setCode("200");
        result.setMsg(msg);
        return result;
    }

    public static Result fail(String msg){
        Result result = new Result();
        result.setCode("400");
        result.setMsg(msg);
        return result;
    }

    public boolean isSuccess() {
        return "200".equals(code);
    }
}

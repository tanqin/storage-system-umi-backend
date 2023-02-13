package com.tanqin.entity;

import lombok.Data;

/**
 * 结果集
 */
@Data
public class Result {
    private Integer code; // 状态码
    private String message; // 响应信息
    private Long total; // 总条数
    private Object data; // 数据

    private static Result result(Integer code, String message, Long total, Object data) {
        Result res = new Result();
        res.setCode(code);
        res.setMessage(message);
        res.setTotal(total);
        res.setData(data);
        return res;
    }

    // 失败(无参数)
    public static Result fail() {
        return Result.result(400, "操作失败", 0L, null);
    }

    // 失败(1 个参数)
    public static Result fail(String message) {
        return Result.result(400, message, 0L, null);
    }

    // 失败(2 个参数)
    public static Result fail(String message, Integer code) {
        return Result.result(code, message, 0L, null);
    }

    // 成功（0 个参数）
    public static Result success() {
        return Result.result(200, "操作成功", 0L, null);
    }

    // 成功（1 个参数）
    public static Result success(String message) {
        return Result.result(200, message, 0L, null);
    }

    // 成功（1 个参数）
    public static Result success(Object data) {
        return Result.result(200, "操作成功", 0L, data);
    }

    // 成功（2 个参数）
    public static Result success(Object data, String message) {
        return Result.result(200, message, 0L, data);
    }

    // 成功（2 个参数）
    public static Result success(Object data, Long total) {
        return Result.result(200, "操作成功", total, data);
    }

    // 成功(3 个参数)
    public static Result success(Object data, Long total, String message) {
        return Result.result(200, message, total, data);
    }
}

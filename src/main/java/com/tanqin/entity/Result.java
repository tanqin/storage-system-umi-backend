package com.tanqin.entity;

import com.tanqin.common.HttpUtil;
import lombok.Data;
import org.springframework.http.HttpStatus;


/**
 * 结果集
 */
@Data
public class Result {
    private Integer code; // 状态码
    private String message; // 响应信息
    private Long total; // 总条数
    private Object data; // 数据

    public static Result result(Integer code, String message, Long total, Object data) {
        HttpUtil.getResponse().setStatus(code);
        Result res = new Result();
        res.setCode(code);
        res.setMessage(message);
        res.setTotal(total);
        res.setData(data);
        return res;
    }

    /**
     * 失败 (无参数)
     *
     * @return
     */
    public static Result fail() {
        return Result.result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "操作失败！", 0L, null);
    }

    /**
     * 失败(1 个参数)
     *
     * @param message 错误信息
     * @return
     */
    public static Result fail(String message) {
        return Result.result(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, 0L, null);
    }

    /**
     * 失败（1 个参数）
     *
     * @param code 失败状态码
     * @return
     */
    public static Result fail(Integer code) {
        return Result.result(code, "操作失败！", 0L, null);
    }

    /**
     * 失败(2 个参数)
     *
     * @param message 错误信息
     * @param code    失败状态码
     * @return
     */
    public static Result fail(String message, Integer code) {
        return Result.result(code, message, 0L, null);
    }

    /**
     * 成功（0 个参数）
     *
     * @return
     */
    public static Result success() {
        return Result.result(HttpStatus.OK.value(), "操作成功！", 0L, null);
    }

    /**
     * 成功（1 个参数）
     *
     * @param message 成功信息
     * @return
     */
    public static Result success(String message) {
        return Result.result(HttpStatus.OK.value(), message, 0L, null);
    }

    /**
     * 成功（1 个参数）
     *
     * @param code 成功状态码
     * @return
     */
    public static Result success(Integer code) {
        return Result.result(code, "操作成功！", 0L, null);
    }

    /**
     * 成功（1 个参数）
     *
     * @param data 响应的数据
     * @return
     */
    public static Result success(Object data) {
        return Result.result(HttpStatus.OK.value(), "操作成功！", 0L, data);
    }

    /**
     * 成功（2 个参数）
     *
     * @param code
     * @param message
     * @return
     */
    public static Result success(Integer code, String message) {
        return Result.result(code, message, 0L, null);
    }

    /**
     * 成功（2 个参数）
     *
     * @param data    响应的数据
     * @param message 成功信息
     * @return
     */
    public static Result success(Object data, String message) {
        return Result.result(HttpStatus.OK.value(), message, 0L, data);
    }

    /**
     * 成功（2 个参数）
     *
     * @param data  响应的数据
     * @param total 数量
     * @return
     */
    public static Result success(Object data, Long total) {
        return Result.result(HttpStatus.OK.value(), "操作成功！", total, data);
    }

    /**
     * 成功(3 个参数)
     *
     * @param data    响应的数据
     * @param total   数量
     * @param message 成功信息
     * @return
     */
    public static Result success(Object data, Long total, String message) {
        return Result.result(HttpStatus.OK.value(), message, total, data);
    }
}

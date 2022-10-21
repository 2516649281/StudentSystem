package com.chunfeng.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * JSON响应类
 *
 * @author by 春风能解释
 * <p>
 * 2022/10/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonRequest<T> implements Serializable {
    /**
     * 状态码
     */
    private Integer status = 200;
    /**
     * 消息
     */
    private String message = "success!";
    /**
     * 是否成功
     */
    private Boolean success = true;
    /**
     * 数据
     */
    private T data = null;

    public JsonRequest(T data) {
        this.data = data;
    }

    public JsonRequest(Integer status, String message, Boolean success) {
        this.status = status;
        this.message = message;
        this.success = success;
    }

    /**
     * 成功请求
     *
     * @param data 数据
     * @param <T>  类型
     * @return JSON
     */
    public static <T> JsonRequest<T> success(T data) {
        return new JsonRequest<>(data);
    }

    /**
     * 错误请求
     *
     * @param serviceEnum 错误枚举
     * @return JSON
     */
    public static <T> JsonRequest<T> error(ServiceEnum serviceEnum) {
        return new JsonRequest<>(serviceEnum.getStatus(), serviceEnum.getMessage(), false);
    }
}

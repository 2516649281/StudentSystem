package com.chunfeng.result;

/**
 * 错误枚举
 *
 * @author by 春风能解释
 * <p>
 * 2022/10/19
 */
public enum ServiceEnum {
    /**
     * 权限不足
     */
    UNAUTHORIZED(401, "权限不足!"),
    /**
     * 登录失败
     */
    LOGIN_ERROR(402, "登录失败,请检查用户名或密码是否正确!"),
    /**
     * 注册失败
     */
    REGISTER_ERROR(403, "注册失败,请检查数据是否符合规范!"),
    /**
     * 找不到资源
     */
    NOT_FOUND(404, "找不到资源!"),
    /**
     * 查询数据时遇到问题
     */
    SELECT_ERROR(501, "数据查询失败!"),
    /**
     * 添加数据时遇到问题
     */
    INSERT_ERROR(502, "数据添加失败!"),
    /**
     * 修改数据时遇到问题
     */
    UPDATE_ERROR(503, "数据修改失败!"),
    /**
     * 删除数据时遇到问题
     */
    DELETE_ERROR(504, "数据删除失败!");

    /**
     * 状态码
     */
    private Integer status;
    /**
     * 消息
     */
    private String message;

    ServiceEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package com.wf.common.constants;

public enum ResultCode {

    /**
     * 1xxx System code
     */
    SYSTEM_ERROR(1001, "系统繁忙"),
    NETWORK_ERROR(1002, "网络错误"),
    OPTIMISTIC_LOCK_ERROR(1003, "数据更新失败"),
    PARAMS_ERROR(1004, "参数错误"),
    INVALID_CODE(1007, "无效的验证码"),
    INVALID_TELEPHONE(1008, "无效的手机号"),
    SAVE_FAILED(1009, "保存失败"),
    IMPORT_FILE_FAILED(1010, "读取文件内容失败，请重试"),
    IMPORT_FILE_NOT_NULL(1011, "文件内容不能为空"),
    CANNOT_FOUND_ENTERPRISE_PARAM(1012, "无法获取集团配置"),
    WORD_TO_PDF_FAILED(1013, "word转为pdf失败"),
    REPEATED_SUBMISSION_ERROR(1014, "正在处理，请不要重复提交"),
    RECORD_NOT_EXISTS(1015, "记录不存在"),


    /**
     * 11xxx user code
     */
    USER_PASSWORD_DIFFER(11000, "密码不一致"),
    USER_PASSWORD_RESET_FAILED(11001, "密码重置失败"),
    USER_NOT_EXIST(11002, "账户不存在"),
    USER_IS_EXIST(11003, "账户已存在"),
    PASSWORD_ERROR(11004, "密码错误"),
    LOGIN_TOKEN_IS_NULL(11005, "登录Token不能为空"),
    LOGIN_TOKEN_EXPIRE(11006, "您已长时间未操作，为了安全请您重新登录！"),
    ACCESS_TOKEN_INVALID(11007, "无效的Token"),
    USER_NO_CFG_AUTH_MENUs(11008, "账户未配置任何访问权限"),
    USER_NO_PERM(11009, "账户未配置访问权限"),
    USER_STOP_FAILED(10010, "账户停用失败"),
    USER_MENUS_GET_FAILED(10011, "获取用户菜单信息失败"),
    USER_STOPED_NOT_CHANGE(10012, "账户已停用，不能更改"),
    USER_STATUS_IS_SAME(10013, "账户状态相同无须更改"),
    USER_IS_STOP(10014, "账户已停用"),
    INIT_ADMIN_ROLE_FAILED(10015, "创建集团超级管理员失败"),
    OP_NO_ALLOWED(10016, "操作非法"),
    USERNAME_IS_EXIST(11003, "账户名已存在");


    private int code;
    private String message;


    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

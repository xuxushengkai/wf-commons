package com.wf.common.constants;

public interface CommonConstants {

    /**
     * JWT登录信息
     */
    interface JWT {
        String JWT_KEY_USER_ID = "userId";
        String JWT_KEY_LOGIN_TIME = "loginTime";
        String JWT_TOKEN_SPACENAME = "JWT_TOKEN";
    }

    enum Status {
        ENABLE(0, "启用中"),
        DISABLE(1, "停用中");

        public Integer code;
        public String name;

        private Status(Integer code, String name) {
            this.code = code;
            this.name = name;
        }
    }

}

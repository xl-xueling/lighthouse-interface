package com.dtstep.lighthouse.api.common;

public enum ResultCode {

    Success("0","success"),

    ParamsValidateFailed("1","Params Validate Failed!"),

    GetNotSupport("2","Request method 'GET' not supported!"),

    SystemError("500","System Error!"),

    ;


    ResultCode(String code,String message){
        this.code = code;
        this.message = message;
    }

    private String code;

    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

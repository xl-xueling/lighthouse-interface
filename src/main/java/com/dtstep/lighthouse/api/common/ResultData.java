package com.dtstep.lighthouse.api.common;

public class ResultData {

    private String code;

    private String message;

    public ResultData(ResultCode resultCode){
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public ResultData(String message){
        this.code = ResultCode.SystemError.getCode();
        this.message = message;
    }

    public static ResultData success(){
        return new ResultData(ResultCode.Success);
    }

    public static ResultData result(ResultCode resultCode){
        return new ResultData(resultCode);
    }

    public static ResultData result(String errorMessage){
        return new ResultData(errorMessage);
    }

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

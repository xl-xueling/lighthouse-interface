package com.dtstep.lighthouse.api.common;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

public class StatParams implements Serializable {

    @NotEmpty(message = "Parameter 'token' cannot be null!")
    private String token;

    @NotEmpty(message = "Parameter 'secretKey' cannot be null!")
    private String secretKey;

    @NotNull(message = "Parameter 'params' cannot be null!")
    Map<String,Object> params;

    private long timestamp = System.currentTimeMillis();

    private int repeat = 1;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }
}

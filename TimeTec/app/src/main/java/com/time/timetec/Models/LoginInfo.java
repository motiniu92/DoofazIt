package com.time.timetec.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginInfo {


    @SerializedName("login_success")
    @Expose
    private String loginSuccess;
    @SerializedName("customer_id")
    @Expose
    private int customerId;

    public String getLoginSuccess() {
        return loginSuccess;
    }

    public void setLoginSuccess(String loginSuccess) {
        this.loginSuccess = loginSuccess;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}

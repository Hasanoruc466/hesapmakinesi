package com.example.hesapmakinesi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExchangeResponse {
    @SerializedName("result")
    @Expose
    private ExchangeResult result;
    @SerializedName("success")
    @Expose
    private boolean success;

    public ExchangeResult getResult() {
        return result;
    }

    public void setResult(ExchangeResult result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

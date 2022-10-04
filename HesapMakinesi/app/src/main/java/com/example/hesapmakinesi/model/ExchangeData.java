package com.example.hesapmakinesi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExchangeData {
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("rate")
    @Expose
    private String rate;
    @SerializedName("calculatedstr")
    @Expose
    private String calculatedstr;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getCalculatedstr() {
        return calculatedstr;
    }

    public void setCalculatedstr(String calculatedstr) {
        this.calculatedstr = calculatedstr;
    }

}

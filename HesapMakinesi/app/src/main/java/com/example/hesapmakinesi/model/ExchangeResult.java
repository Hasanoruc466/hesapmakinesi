package com.example.hesapmakinesi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ExchangeResult {
    @SerializedName("data")
    @Expose
    private ArrayList<ExchangeData> datas = null;
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("lastupdate")
    @Expose
    private String lastUpdate;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public ArrayList<ExchangeData> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<ExchangeData> datas) {
        this.datas = datas;
    }
}

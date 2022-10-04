package com.example.hesapmakinesi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Responses {
    @SerializedName("result")
    @Expose
    private ArrayList<Results> results = null;
    @SerializedName("success")
    @Expose
    private boolean success;

    public ArrayList<Results> getResults() {
        return results;
    }

    public void setResults(ArrayList<Results> results) {
        this.results = results;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

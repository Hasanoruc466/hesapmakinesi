package com.example.hesapmakinesi.dao;

import com.example.hesapmakinesi.model.ExchangeResponse;
import com.example.hesapmakinesi.model.Responses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APIDao {
    @Headers("authorization:"  + "apikey 5PhX6j8ttEzxDWrD36WsPI:62Y9lcLBHx57fgv4868bHA")
    @GET("economy/symbols")
    Call<Responses> getCode();
    @Headers("authorization:"  + "apikey 5PhX6j8ttEzxDWrD36WsPI:62Y9lcLBHx57fgv4868bHA")
    @GET("economy/exchange")
    Call<ExchangeResponse> getExchange(@Query("int") String i, @Query("to") String to, @Query("base") String base);
    @Headers("authorization:"  + "apikey 5PhX6j8ttEzxDWrD36WsPI:62Y9lcLBHx57fgv4868bHA")
    @GET("economy/currencyToAll")
    Call<ExchangeResponse> getCurrencyAll(@Query("int") String i, @Query("base") String base);
}

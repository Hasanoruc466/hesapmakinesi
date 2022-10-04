package com.example.hesapmakinesi.retrofit;

import com.example.hesapmakinesi.dao.APIDao;

public class APIUtils {
    private static String baseUrl = "https://api.collectapi.com/";
    public static APIDao getDoviz(){
        return RetrofitClient.getRetrofit(baseUrl).create(APIDao.class);
    }
}

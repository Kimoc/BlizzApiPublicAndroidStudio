package com.aarongutierrez.blizzardapi.service;

import com.aarongutierrez.blizzardapi.constants.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/*
* Creates a service for the api call
* */
public class ServiceGenerator {
    public static final String BASE_URL = Constants.BASE_URL;
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> serviceClass){
        return retrofit.create(serviceClass);
    }
}

package com.realibi.weatherapp.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.realibi.weatherapp.interfaces.WeatherApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService networkService;
    private static String BASE_URL = "https://api.openweathermap.org/data/2.5/";

    private NetworkService(){}

    public static NetworkService getInstance(){
        if(networkService == null){
            networkService = new NetworkService();
        }
        return networkService;
    }

    public WeatherApi getRetrofitService()  {

        Gson gson = new GsonBuilder().create();

        OkHttpClient.Builder clientBuilder =
                new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(10, TimeUnit.SECONDS);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(WeatherApi.class);
    }

}

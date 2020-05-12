package com.realibi.weatherapp.interfaces;

import com.realibi.weatherapp.models.WeatherData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface WeatherApi {
    @GET
    Call<WeatherData> getWeather(@Url String url);
}

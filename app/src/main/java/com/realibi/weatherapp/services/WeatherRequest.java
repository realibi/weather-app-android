package com.realibi.weatherapp.services;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;

import com.realibi.weatherapp.R;
import com.realibi.weatherapp.models.WeatherData;

import java.io.IOException;

import retrofit2.Call;

public class WeatherRequest extends AsyncTask<String, Integer, String> {

    private Activity activity;
    private WeatherData weatherData;

    public WeatherRequest(Activity activity, WeatherData weatherData){
        this.activity = activity;
        this.weatherData = weatherData;
    }

    @Override
    protected String doInBackground(String... arg) {
        String url = String.format("weather?q=%s&units=metric&appid=%s", arg[0], arg[1]);
        Call<WeatherData> weatherDataCall = NetworkService.getInstance().getRetrofitService().getWeather(url);
        try {
            weatherData = weatherDataCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected void onPostExecute(String s) {
        Activity mainActivity = activity;
        TextView degreesTextView = mainActivity.findViewById(R.id.degreesTextView);
        TextView cityNameTextView = mainActivity.findViewById(R.id.cityTextView);
        TextView feelsLikeTextView = mainActivity.findViewById(R.id.feelsLikeTextView);
        TextView descriptionTextView = mainActivity.findViewById(R.id.descriptionTextView);
        EditText cityNameEditText = mainActivity.findViewById(R.id.cityEditText);

        if(weatherData != null){
            degreesTextView.setText(weatherData.getMain().getTemp().toString());
            cityNameTextView.setText(weatherData.getName());
            feelsLikeTextView.setText("Feels like: " + weatherData.getMain().getFeelsLike().toString());
            descriptionTextView.setText(weatherData.getWeather().get(0).getDescription());
            cityNameEditText.setText("");
        }
        else {
            cityNameTextView.setText("City not found.");
            feelsLikeTextView.setText("Try again!");
        }
    }
}

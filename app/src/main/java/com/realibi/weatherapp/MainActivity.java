package com.realibi.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.realibi.weatherapp.models.WeatherData;
import com.realibi.weatherapp.services.WeatherRequest;

public class MainActivity extends AppCompatActivity {
    private WeatherRequest myAsyncTask;
    WeatherData weatherData = new WeatherData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText cityNameEditText = findViewById(R.id.cityEditText);
        Button searchButton = findViewById(R.id.searchButton);
        String apiKey = getString(R.string.apikey);

        searchButton.setOnClickListener(v -> {
            myAsyncTask = new WeatherRequest(this, weatherData);
            myAsyncTask.execute(cityNameEditText.getText().toString(), apiKey);
        });
    }
}

package com.example.weatherappcw1.ui.forecast;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.weatherappcw1.Weather.WeatherData;
import com.example.weatherappcw1.Weather.WeatherOperation;
import com.example.weatherappcw1.databinding.FragmentForecastBinding;

import com.google.gson.Gson;

import java.util.List;

public class ForecastFragment extends Fragment {

    private FragmentForecastBinding binding;
    public ViewGroup main;
    public ForecastEntities ForecastEntities;
    public WeatherData info;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentForecastBinding.inflate(inflater, container, false);
        main = container;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle s) {
        super.onViewCreated(view, s);
        ForecastEntities = new ForecastEntities(view);

        start();
    }

    void start() {
        WeatherOperation WeatherOp = new WeatherOperation("auto:ip", 2);
        WeatherOp.execute();

        try {
            String response = WeatherOp.get();

            Gson gson = new Gson();
            info = gson.fromJson(response, WeatherData.class);

            updateInfo(info);

        } catch (Exception e) {e.printStackTrace();}
    }

    void updateInfo(WeatherData WeatherInfo) {
        WeatherData.ForecastDayData[] forecastdays = WeatherInfo.forecast.forecastday;

        ForecastEntities.LocationCountry.setText(WeatherInfo.location.country);
        ForecastEntities.LocationName.setText(WeatherInfo.location.name);
        ForecastEntities.LocationRegion.setText(WeatherInfo.location.region);

        String day;
        String averageTemp;
        for (int i = 0; i < forecastdays.length; i++) {
            WeatherData.ForecastDayData date = forecastdays[i];
            day = date.date.split("-")[2];
            averageTemp = date.day.avgtemp_c + "°C";
            ForecastEntities.AllTextViews[i].setText(day + getOrdinal(day));
            ForecastEntities.AllTextViews[i + 3].setText(averageTemp);
        }
    }
    String getOrdinal(String data) {
        switch (data.charAt(data.length() - 1)) {
            case '1':
                return "st";
            case '2':
                return "nd";
        }
        return "th";
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.example.weatherappcw1.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.weatherappcw1.R;
import com.example.weatherappcw1.Weather.WeatherColors;
import com.example.weatherappcw1.Weather.WeatherOperation;
import com.example.weatherappcw1.databinding.FragmentHomeBinding;

import com.example.weatherappcw1.Weather.WeatherData;

import com.google.gson.Gson;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    public ViewGroup main;
    public HomeEntities homeEntities;
    public WeatherData info;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        main = container;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle s) {
        super.onViewCreated(view, s);

        homeEntities = new HomeEntities(view);

        start();

        Switch DegreeType = (Switch)view.findViewById(R.id.DegreeType);
        DegreeType.setOnCheckedChangeListener((buttonView, UseFahrenheit) -> {
            if (UseFahrenheit) homeEntities.SetTemp(info.current.temp_f);
            else homeEntities.SetTemp(info.current.temp_c);
        });
    }

    void start() {
        WeatherOperation WeatherOp = new WeatherOperation("autodetect");
        WeatherOp.execute();

        try {
            String response = WeatherOp.get();

            Gson gson = new Gson();
            info = gson.fromJson(response, WeatherData.class);

            UpdateInfo(info);
        } catch (Exception e) {e.printStackTrace();}

    }

    void UpdateInfo(WeatherData WeatherInfo) {
        main.setBackgroundColor(WeatherColors.GetWeatherInHex(WeatherInfo.current.condition.text));

        homeEntities.LocationCountry.setText(WeatherInfo.location.country);
        homeEntities.LocationName.setText(WeatherInfo.location.name);
        homeEntities.LocationRegion.setText(WeatherInfo.location.region);
        homeEntities.Temp.setText(Double.toString(WeatherInfo.current.temp_c) + "°");
        homeEntities.Condition.setText(WeatherInfo.current.condition.text);

        String ExtraInfo = "";

        ExtraInfo += "Wind (MPH): " + WeatherInfo.current.wind_mph + "\n";
        ExtraInfo += "Wind (Degree): " + WeatherInfo.current.wind_degree + "\n";
        ExtraInfo += "Wind (Direction): " + WeatherInfo.current.wind_dir + "\n";
        ExtraInfo += "Pressure (inches): " + WeatherInfo.current.pressure_in + "\n";
        ExtraInfo += "Precipitation (inches): " + WeatherInfo.current.precip_in + "\n";
        ExtraInfo += "Humidity: " + WeatherInfo.current.humidity + "\n";
        ExtraInfo += "Cloud: " + WeatherInfo.current.cloud + "\n";
        ExtraInfo += "Feels Like (C): " + WeatherInfo.current.feelslike_c + "\n";
        ExtraInfo += "Gust (MPH): " + WeatherInfo.current.gust_mph + "\n";

        homeEntities.ExtraInfo.setText(ExtraInfo);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.example.weatherappcw1.ui.home;

import java.util.Date;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.weatherappcw1.MainActivity;
import com.example.weatherappcw1.R;
import com.example.weatherappcw1.Weather.WeatherColors;
import com.example.weatherappcw1.Weather.WeatherOperation;
import com.example.weatherappcw1.databinding.FragmentHomeBinding;

import com.example.weatherappcw1.Weather.WeatherData;

import com.google.gson.Gson;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    public ViewGroup main;
    public HomeEntities HomeEntities;
    public WeatherData info;

    public String customQuery = "";
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        main = container;

        Bundle arguments = getArguments();
        if (arguments != null) customQuery = arguments.getString("query");

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle s) {
        super.onViewCreated(view, s);
        HomeEntities = new HomeEntities(view);
        start();

        //Toggle for Celsius and Fahrenheit
        Switch DegreeType = (Switch)view.findViewById(R.id.DegreeType);
        DegreeType.setOnCheckedChangeListener((buttonView, UseFahrenheit) -> {
            if (UseFahrenheit) HomeEntities.SetTemp(info.current.temp_f);
            else HomeEntities.SetTemp(info.current.temp_c);
        });
    }

    void start() {
        String query = "auto:ip";
        if (!customQuery.equals("")) {
            MainActivity main = (MainActivity)getActivity();
            assert main != null;
            main.location = customQuery;
            query = customQuery;
        }

        WeatherOperation WeatherOp = new WeatherOperation(query,1);
        WeatherOp.execute();

        try {
            String response = WeatherOp.get();

            Gson gson = new Gson();
            info = gson.fromJson(response, WeatherData.class);
            UpdateInfo(info);
            MainActivity main = (MainActivity)getActivity();
            assert main != null;
            main.info = info;
        } catch (Exception e) {e.printStackTrace();}

    }

    void UpdateInfo(WeatherData WeatherInfo) {
        String date = new Date().toString();
        main.setBackgroundColor(WeatherColors.GetWeatherInHex(WeatherInfo.current.condition.text));

        HomeEntities.LocationCountry.setText(WeatherInfo.location.country);
        HomeEntities.LocationName.setText(WeatherInfo.location.name);
        HomeEntities.LocationRegion.setText(WeatherInfo.location.region);
        HomeEntities.Temp.setText(Double.toString(WeatherInfo.current.temp_c) + "°");
        HomeEntities.Condition.setText(WeatherInfo.current.condition.text);
        HomeEntities.TimeUpdate.setText(date.substring(0, date.length() - 15));

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

        HomeEntities.ExtraInfo.setText(ExtraInfo);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
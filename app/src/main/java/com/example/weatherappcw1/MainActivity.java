package com.example.weatherappcw1;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.weatherappcw1.Weather.WeatherData;
import com.example.weatherappcw1.Weather.WeatherColors;
import com.example.weatherappcw1.Weather.WeatherOperation;

import com.google.gson.Gson;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.weatherappcw1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    public ConstraintLayout main;
    public Entities entities;
    public WeatherData info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_dashboard, R.id.navigation_home, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        entities = new Entities(this);
        main = findViewById(R.id.container);
        start();

        Switch DegreeType = (Switch)findViewById(R.id.DegreeType);
        DegreeType.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean UseFahrenheit) {
                if (UseFahrenheit) entities.SetTemp(info.current.temp_f);
                else entities.SetTemp(info.current.temp_c);
            }
        });

    }
    void start() {
        start("autodetect");
    }

    void start(String Location) {
        WeatherOperation WeatherOp = new WeatherOperation(Location);
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

        entities.LocationCountry.setText(WeatherInfo.location.country);
        entities.LocationName.setText(WeatherInfo.location.name);
        entities.LocationRegion.setText(WeatherInfo.location.region);
        entities.Temp.setText(Double.toString(WeatherInfo.current.temp_c) + "°");
        entities.Condition.setText(WeatherInfo.current.condition.text);

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

        entities.ExtraInfo.setText(ExtraInfo);

    }
}
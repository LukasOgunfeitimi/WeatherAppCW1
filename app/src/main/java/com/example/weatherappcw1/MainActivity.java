package com.example.weatherappcw1;

import android.os.Bundle;
import android.view.View;
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

/*
Home Search Hourly 10day About
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public String location = "auto:ip";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_forecast, R.id.navigation_home, R.id.navigation_search, R.id.navigation_about)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


    }


    }

package com.example.weatherappcw1.ui.forecast;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.weatherappcw1.Weather.WeatherOperation;
import com.example.weatherappcw1.databinding.FragmentForecastBinding;

public class ForecastFragment extends Fragment {

    private FragmentForecastBinding binding;
    public ViewGroup main;
    public ForecastEntities ForecastEntities;

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
            System.out.println(response);
        } catch (Exception e) {e.printStackTrace();}
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
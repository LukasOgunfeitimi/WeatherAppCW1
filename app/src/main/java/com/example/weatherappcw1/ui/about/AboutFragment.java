package com.example.weatherappcw1.ui.about;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weatherappcw1.MainActivity;
import com.example.weatherappcw1.Weather.WeatherData;
import com.example.weatherappcw1.databinding.FragmentAboutBinding;


public class AboutFragment extends Fragment {
    public WeatherData data;
    private FragmentAboutBinding binding;
    public AboutEntities AboutEntities;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AboutViewModel dashboardViewModel =
                new ViewModelProvider(this).get(AboutViewModel.class);

        binding = FragmentAboutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AboutEntities = new AboutEntities(view);
        start();
    }

    void start() {
        MainActivity m = (MainActivity)getActivity();
        assert m != null;
        data = m.info;
        AboutEntities.name.append(data.location.name);
        AboutEntities.region.append(data.location.region);
        AboutEntities.country.append(data.location.country);
        AboutEntities.lat.append(String.valueOf(data.location.lat));
        AboutEntities.lon.append(String.valueOf(data.location.lon));
        AboutEntities.tz_id.append(data.location.tz_id);
        AboutEntities.localtime.append(data.location.localtime);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
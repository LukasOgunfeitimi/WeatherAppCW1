package com.example.weatherappcw1.ui.forecast;

import android.view.View;
import android.widget.TextView;

import com.example.weatherappcw1.R;

public class ForecastEntities {
    public TextView test;

    public ForecastEntities(View activity) {
        this.test = activity.findViewById(R.id.textView);
    }
}

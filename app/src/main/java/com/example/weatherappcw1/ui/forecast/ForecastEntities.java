package com.example.weatherappcw1.ui.forecast;

import android.view.View;
import android.widget.TextView;

import com.example.weatherappcw1.R;

public class ForecastEntities {
    public TextView LocationCountry;
    public TextView LocationName;
    public TextView LocationRegion ;
    public TextView[] AllTextViews = new TextView[6];

    public ForecastEntities(View activity) {
        this.LocationCountry = activity.findViewById(R.id.LocationCountry);
        this.LocationName = activity.findViewById(R.id.LocationName);
        this.LocationRegion = activity.findViewById(R.id.LocationRegion);

        this.AllTextViews[0] = activity.findViewById(R.id.Temp);
        this.AllTextViews[1] = activity.findViewById(R.id.Temp2);
        this.AllTextViews[2] = activity.findViewById(R.id.Temp3);

        this.AllTextViews[3] = activity.findViewById(R.id.Condition);
        this.AllTextViews[4] = activity.findViewById(R.id.Condition2);
        this.AllTextViews[5] = activity.findViewById(R.id.Condition3);

    }
}

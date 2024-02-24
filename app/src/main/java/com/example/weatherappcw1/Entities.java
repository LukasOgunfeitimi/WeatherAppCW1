package com.example.weatherappcw1;

import android.app.Activity;
import android.widget.TextView;

public class Entities {
    TextView LocationCountry;
    TextView LocationName;
    TextView LocationRegion ;
    TextView Temp;
    TextView Condition;
    TextView ExtraInfo;
    public Entities(Activity activity) {
        this.ExtraInfo = activity.findViewById(R.id.ExtraInfo);
        this.LocationCountry = activity.findViewById(R.id.LocationCountry);
        this.LocationName = activity.findViewById(R.id.LocationName);
        this.LocationRegion = activity.findViewById(R.id.LocationRegion);
        this.Temp = activity.findViewById(R.id.Temp);
        this.Condition = activity.findViewById(R.id.Condition);
    }

    public void SetTemp(Double Temp) {
        this.Temp.setText(Double.toString(Temp) + "°");
    }

}

package com.example.weatherappcw1;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class Entities {
    public TextView LocationCountry;
    public TextView LocationName;
    public TextView LocationRegion ;
    public TextView Temp;
    public TextView Condition;
    public TextView ExtraInfo;
    public Entities(View activity) {
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

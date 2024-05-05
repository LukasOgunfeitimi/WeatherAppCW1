package com.example.weatherappcw1.ui.about;

import android.view.View;
import android.widget.TextView;
import com.example.weatherappcw1.R;

public class AboutEntities {
    public TextView name;
    public TextView region;
    public TextView country;
    public TextView lat;
    public TextView lon;
    public TextView tz_id;
    public TextView localtime;
    public AboutEntities(View v) {
        this.name = v.findViewById(R.id.name);
        this.region = v.findViewById(R.id.region);
        this.country = v.findViewById(R.id.country);
        this.lat = v.findViewById(R.id.lat);
        this.lon = v.findViewById(R.id.lon);
        this.tz_id = v.findViewById(R.id.tz_id);
        this.localtime = v.findViewById(R.id.localtime);
    }
}

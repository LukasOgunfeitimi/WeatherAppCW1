package com.example.weatherappcw1.ui.search;

import android.view.View;
import android.widget.SearchView;

import com.example.weatherappcw1.R;

public class SearchEntities {
    public SearchView search;

    public SearchEntities(View activity)        {
        this.search = activity.findViewById(R.id.Search);
    }


}

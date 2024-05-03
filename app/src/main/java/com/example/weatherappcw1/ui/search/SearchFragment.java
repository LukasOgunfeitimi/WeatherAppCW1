package com.example.weatherappcw1.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.weatherappcw1.MainActivity;
import com.example.weatherappcw1.databinding.FragmentSearchBinding;
import com.example.weatherappcw1.R;
import com.example.weatherappcw1.ui.forecast.ForecastFragment;
import com.example.weatherappcw1.ui.home.HomeFragment;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;


public class SearchFragment extends Fragment {
    public SearchEntities SearchEntities;

    private FragmentSearchBinding binding;
    public NavController nav;
    public SearchView search;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SearchViewModel searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle s) {
        super.onViewCreated(view, s);
        SearchView search = view.findViewById(R.id.Search);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchForQuery(view, query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    void SearchForQuery(View view, String query) {
        Bundle bundle = new Bundle();
        bundle.putString("query", query);
        Navigation.findNavController(view).navigate(R.id.search_to_home, bundle);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
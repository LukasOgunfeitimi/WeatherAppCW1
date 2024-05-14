package com.example.weatherappcw1.ui.bookmark;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import com.example.weatherappcw1.R;

import java.util.Arrays;

public class BookmarkFragment extends Fragment {
    public static final String SHARED = "PRE";
    private View currentView;
    public static BookmarkFragment newInstance() {
        return new BookmarkFragment();
    }
    private final int[] ButtonIDs = {R.id.bookmark1,R.id.bookmark2,R.id.bookmark3,R.id.bookmark4,R.id.bookmark5};
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        currentView = inflater.inflate(R.layout.fragment_bookmark, container, false);
        setButtonTexts();
        return currentView;
    }

    /***
     * Store the locations in the cache
     */
    void storePre() {
        SharedPreferences sp = getActivity().getSharedPreferences(SHARED, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        String[] texts = getButtonTexts();
        for (int i = 0; i < texts.length; i++) {
            edit.putString("bookmark" + i, texts[i]);
        }
        edit.apply();
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        storePre();
    }

    /***
     * Set the buttons text to the locations
     * from the cache
     */
    void setButtonTexts() {
        SharedPreferences sp = getActivity().getSharedPreferences(SHARED, Context.MODE_PRIVATE);
        if (sp == null) return;
        Button button;
        String buttonText;
        for (int i = 0; i < ButtonIDs.length; i++) {
            button = currentView.findViewById(ButtonIDs[i]);
            buttonText = sp.getString("bookmark" + i, "Bookmark " + (i + 1));
            button.setText(buttonText);
        }
    }
    /***
     * Return the locations of all the buttons
     * @return String[]
     */
    String[] getButtonTexts() {
        String[] texts = new String[5];
        Button button;
        for (int i = 0; i < ButtonIDs.length; i++) {
            button = currentView.findViewById(ButtonIDs[i]);
            texts[i] = (String)button.getText();
        }
        return texts;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setSearch(view);
        setButtons(view);
    }

    /***
     * Set the query listener for the text view
     * when the user enters a location, push that
     * location to the button's text
     * @param view
     */
    void setSearch(View view) {
        SearchView search = view.findViewById(R.id.bookmark_search);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Button button = FindButton(view);
                button.setText(query);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    /***
     * Try and find a button that is available
     * if none are available return the first one.
     * @param view
     * @return button
     */
    Button FindButton(View view) {
        Button button;
        String buttonText;
        for (int buttonID: ButtonIDs) {
            button = view.findViewById(buttonID);
            buttonText = (String)button.getText();
            if (buttonText.contains("Bookmark")) return button;
        }
        return view.findViewById(ButtonIDs[0]);
    }

    void setButtons(View view) {
        Button button;
        for (int buttonID : ButtonIDs) {
            button = view.findViewById(buttonID);
            Button finalButton = button;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String locationQuery = (String)finalButton.getText();
                    if (locationQuery.contains("Bookmark")) return;

                    Bundle bundle = new Bundle();
                    bundle.putString("query", (String) finalButton.getText());
                    storePre();
                    Navigation.findNavController(view).navigate(R.id.bookmark_to_home, bundle);
                }
            });
        }
    }

}
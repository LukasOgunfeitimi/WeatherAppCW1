package com.example.weatherappcw1.Weather;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

public class WeatherOperation extends AsyncTask<Void, Void, String> {
    private final String EndPoint;
    private final String APIKEY = "909ca5dbb1a8401f834122709242002";
    private String location;

    public WeatherOperation(String location) {
        super();
        if (Objects.equals(location, "autodetect")) {
            this.location = "auto:ip";
        } else {
            this.location = location;
        }
        this.EndPoint = "https://api.weatherapi.com/v1/current.json?key=" + this.APIKEY + "&q=" + this.location + "&aqi=";
    }
    @Override
    protected String doInBackground(Void... voids) {
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL(this.EndPoint);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            connection.disconnect();

        } catch (Exception e) {
            Log.e("HttpTask", "Error", e);
        }
        return response.toString();
    }
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        // Handle the result, update UI, etc.
        //Log.d("HttpTask", "Response: " + result);
    }
}


package com.example.weatherappcw1.Weather;

import java.util.List;

public class WeatherData {
    public LocationData location;
    public CurrentData current;
    public ForecastData forecast;

    public static class LocationData {
        public String name;
        public String region;
        public String country;
    }

    public static class CurrentData {
        public double temp_c;
        public double temp_f;
        public ConditionData condition;
        public double wind_mph;
        public int wind_degree;
        public String wind_dir;
        public double pressure_in;
        public double precip_in;
        public int humidity;
        public int cloud;
        public double feelslike_c;
        public double gust_mph;
    }

    public static class ConditionData {
        public String text;
        public String icon;
        public int code;
    }

    public static class ForecastData {
        public ForecastDayData[] forecastday;
    }
    public static class ForecastDayData {
        public String date;
        public ForecastSpecificDay day;
    }
    public static class ForecastSpecificDay {
        public String avgtemp_c;
    }
}

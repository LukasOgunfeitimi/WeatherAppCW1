package com.example.weatherappcw1.Weather;
import android.graphics.drawable.GradientDrawable;

public class WeatherColors {
    public static int LightYellow = 0xFFF7DC6F;
    public static int LightGray = 0xFF7F8C8D;
    public static int DarkBlue  = 0xFF6C8094;

    /*
    Possible weather conditions https://www.weatherapi.com/docs/weather_conditions.json
     */
    public static GradientDrawable GetWeatherInHex(String Weather) {
        switch (Weather) {
            case "Sunny":
                return createGradient(0xFFFFD700, 0xFF87CEFA);

            case "Patchy rain possible":
            case "Moderate rain":
            case "Heavy rain at times":
            case "Light freezing rain":
            case "Heavy rain":
            case "Light rain shower":
            case "Light rain":
                return createGradient(0xFF1E90FF, 0xFFADD8E6);

            case "Cloudy":
            case "Partly Cloudy":
            case "Overcast":
            case "Mist":
                return createGradient(0xFFB0C4DE, 0xFF696969);
        }
        return createGradient(0x0, 0x0);
    }

    private static GradientDrawable createGradient(int from, int to) {
        int[] colors = {from, to}; // Blue to Green
        GradientDrawable gradientDrawable = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM, colors);
        gradientDrawable.setCornerRadius(0f);
        return gradientDrawable;
    }
}

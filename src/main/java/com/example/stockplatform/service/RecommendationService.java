package com.example.stockplatform.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class RecommendationService {

    // Thresholds for decision making
    private static final double BUY_THRESHOLD = 0.95; // 5% below the SMA
    private static final double HOLD_THRESHOLD = 1.05; // 5% above the SMA

    public String analyzeStockData(String jsonData) {
        JSONObject json = new JSONObject(jsonData);
        // Assume that we receive a JSON object with daily prices under "Time Series (Daily)"
        JSONObject dailyPrices = json.getJSONObject("Time Series (Daily)");

        // Calculate the Simple Moving Average (SMA)
        double sma = calculateSMA(dailyPrices, 20); // 20-day SMA

        // Get the latest price (assuming the JSON keys are dates and prices are under "4. close")
        String latestDate = dailyPrices.keys().next(); // This might not always get the latest date without sorting
        double latestPrice = dailyPrices.getJSONObject(latestDate).getDouble("4. close");

        // Determine recommendation based on the latest price and SMA
        if (latestPrice / sma <= BUY_THRESHOLD) {
            return "Buy";
        } else if (latestPrice / sma >= HOLD_THRESHOLD) {
            return "Hold";
        } else {
            return "Hold";
        }
    }

    // Helper method to calculate SMA
    private double calculateSMA(JSONObject dailyPrices, int days) {
        double sum = 0.0;
        int count = 0;

        for (String date : dailyPrices.keySet()) {
            double closePrice = dailyPrices.getJSONObject(date).getDouble("4. close");
            sum += closePrice;
            if (++count == days) break;
        }

        return sum / days;
    }
}

package com.greenravolution.gravodriver.functions;

import com.greenravolution.gravodriver.Objects.OrderDetails;
import com.greenravolution.gravodriver.Objects.Orders;
import com.greenravolution.gravodriver.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by user on 14/3/2018.
 */

public class Rates {
    public Rates() {

    }

    public Double getRates(int category_id, Double weight, String rates) {
        try {
            JSONArray getRates = new JSONArray(rates);
            for (int i = 0; i < getRates.length(); i++) {
                JSONObject rate = getRates.getJSONObject(i);
                if (category_id == rate.getInt("id")) {
                    String price = rate.getString("rate");
                    String[] getPrice = price.split("/");
                    Double pricing = Double.parseDouble(getPrice[0]) * weight;
                    return pricing;
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0.00;

    }

    public String getRate(int category_id, String rates) {

        try {
            JSONArray getRates = new JSONArray(rates);
            for (int i = 0; i < getRates.length(); i++) {
                JSONObject rate = getRates.getJSONObject(i);
                if (category_id == rate.getInt("id")) {
                    String price = rate.getString("rate");
                    return price;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "No Such Item";

    }

    public String getItem(int cat_id, String rates) {
        try {
            JSONArray getRates = new JSONArray(rates);
            for (int i = 0; i < getRates.length(); i++) {
                JSONObject rate = getRates.getJSONObject(i);
                if (cat_id == rate.getInt("id")) {
                    String cat = rate.getString("type");
                    return cat;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "No Such Item";

    }

    public int getImage(int cat_id, String rates) {
        try {
            JSONArray getRates = new JSONArray(rates);
            for (int i = 0; i < getRates.length(); i++) {
                JSONObject rate = getRates.getJSONObject(i);
                if (cat_id == rate.getInt("id")) {
                    String price = rate.getString("type");
                    String[] wasteType = price.split(" ");
                    switch (wasteType[0]) {
                        case "Paper":
                            return R.drawable.recycle_paper;
                        case "Metals":
                            return R.drawable.aluminium;
                        case "E-Waste":
                            return R.drawable.laptop;
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;

    }

    public int getImageColour(int cat_id, String rates) {
        try {
            JSONArray getRates = new JSONArray(rates);
            for (int i = 0; i < getRates.length(); i++) {
                JSONObject rate = getRates.getJSONObject(i);
                if (cat_id == rate.getInt("id")) {
                    String price = rate.getString("type");
                    String[] wasteType = price.split(" ");
                    switch (wasteType[0]) {
                        case "Paper":
                            return R.color.brand_yellow;
                        case "Metals":
                            return R.color.brand_orange;
                        case "E-Waste":
                            return R.color.brand_purple;
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;

    }

    public int GetTotalWeight(ArrayList<OrderDetails> orderDetails) {
        int weight = 0;

        for (int j = 0; j < orderDetails.size(); j++) {
            weight = weight + Integer.parseInt(orderDetails.get(j).getWeight());
        }
        return weight;
    }

    public int GetCollectedWeight(ArrayList<OrderDetails> orderDetails, ArrayList<Orders> orders) {
        ArrayList<OrderDetails> collectedOrders = new ArrayList<>();
        int weight = 0;
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getStatus_id() == 4) {
                for (int j = 0; j < orderDetails.size(); j++) {
                    if (collectedOrders.get(j).getTransaction_id() == orders.get(i).getId()) {
                        collectedOrders.add(orderDetails.get(j));
                    }
                }
            }
        }
        for (int i = 0; i < collectedOrders.size(); i++) {
            weight = weight + Integer.parseInt(collectedOrders.get(i).getWeight());
        }
        return weight;
    }

}

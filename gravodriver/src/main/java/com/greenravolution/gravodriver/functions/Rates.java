package com.greenravolution.gravodriver.functions;

import android.graphics.drawable.Drawable;

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

    public String getRates(int category_id, int weight) {
        double rate = 0.00;
        double price = 0.00;
        switch (category_id) {
            case 1:
                //newspaper
                rate = 0.11;
                price = rate * weight;
                return String.valueOf(price);

            case 2:
                //aluminium
                rate = 0.21;
                price = rate * weight;
                return String.valueOf(price);

            case 3:
                //aluminium
                rate = 0.12;
                price = rate * weight;
                return String.valueOf(price);
            case 4:
                //aluminium
                rate = 0.23;
                price = rate * weight;
                return String.valueOf(price);
            case 5:
                rate = 0.20;
                price = rate * weight;
                return String.valueOf(price);
            case 6:
                rate = 0.25;
                price = rate * weight;
                return String.valueOf(price);
            case 7:
                rate = 0.20;
                price = rate * weight;
                return String.valueOf(price);
            case 8:
                rate = 0.20;
                price = rate * weight;
                return String.valueOf(price);
            case 9:
                rate = 0.20;
                price = rate * weight;
                return String.valueOf(price);
            case 10:
                rate = 0.20;
                price = rate * weight;
                return String.valueOf(price);
        }
        return "No Price";

    }
    public double getRate(int category_id) {

        switch (category_id) {
            case 1:
                //newspaper
                return 0.11;

            case 2:
                //aluminium
                return 0.21;
            case 3:
                //aluminium
                return 0.12;

            case 4:
                //aluminium
                return 0.20;

            case 5:
                //aluminium
                return 0.23;

            case 6:
                //aluminium
                return 0.10;

            case 7:
                //aluminium
                return 0.20;

            case 8:
                //aluminium
                return 0.25;

            case 9:
                //aluminium
                return 0.20;

            case 10:
                //aluminium
                return 0.20;

        }
        return 0.00;

    }

    public String getItem(int cat_id) {
        switch (cat_id) {
            case 1:
                return "Paper | Old Newspapers";
            case 2:
                return "Paper | Old Textbooks";
            case 3:
                return "Paper | Old Cardboards";
            case 4:
                return "Paper | Recycled Paper";
            case 5:
                return "E-Waste | LCD Screens";
            case 6:
                return "E-Waste | Laptops";
            case 7:
                return "Metals | Aluminium";
            case 8:
                return "E-Waste | Copper Wire";
            case 9:
                return "E-Waste | Brass";
            case 10:
                return "E-Waste | Aluminium Cans";
        }
        return "No Such Item";

    }
    public int getImage(int cat_id) {
        switch (cat_id) {
            case 1:
                return R.drawable.newspaper;
            case 2:
                return R.drawable.books;
            case 3:
                return R.drawable.cardboard;
            case 4:
                return R.drawable.recycle_paper;
            case 5:
                return R.drawable.laptop;
            case 6:
                return R.drawable.laptop;
            case 7:
                return R.drawable.aluminium;
            case 8:
                return R.drawable.copper;
            case 9:
                return R.drawable.brass;
            case 10:
                return R.drawable.aluminium_cans;
        }
        return 0;

    }

    public int getImageColour(int cat_id) {
        switch (cat_id) {
            case 1:
                return R.color.brand_yellow;
            case 2:
                return R.color.brand_yellow;
            case 3:
                return R.color.brand_yellow;
            case 4:
                return R.color.brand_yellow;
            case 5:
                return R.color.brand_purple;
            case 6:
                return R.color.brand_purple;
            case 7:
                return R.color.brand_orange;
            case 8:
                return R.color.brand_orange;
            case 9:
                return R.color.brand_orange;
            case 10:
                return R.color.brand_orange;
        }
        return 0;

    }

    public int GetTotalWeight(ArrayList<Orders> orders) {
        int weight = 0;
        for (int i = 0; i < orders.size(); i++) {
            String itemDetails = "[{'id':1,'cat_id':1,'weight':20},{'id':1,'cat_id':2,'weight':30},{'id':1,'cat_id':5,'weight':30}]";
            try {
                JSONArray details = new JSONArray(itemDetails);
                for (int position = 0; position < details.length(); position++) {
                    JSONObject detail = details.getJSONObject(position);
                    int itemWeight = detail.getInt("weight");
                    weight = weight + itemWeight;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return weight;
    }

    public int GetCollectedWeight(ArrayList<Orders> orders) {
        ArrayList<Orders> collectedOrders = new ArrayList<Orders>();
        int weight = 0;
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getStatus_id() == 1) {
                collectedOrders.add(orders.get(i));
            }
        }
        for (int i = 0; i < collectedOrders.size(); i++) {
            String itemDetails = "[{'id':1,'cat_id':1,'weight':20},{'id':1,'cat_id':2,'weight':30},{'id':1,'cat_id':5,'weight':30}]";
            try {
                JSONArray details = new JSONArray(itemDetails);
                for (int position = 0; position < details.length(); position++) {
                    JSONObject detail = details.getJSONObject(position);
                    int itemWeight = detail.getInt("weight");
                    weight = weight + itemWeight;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return weight;
    }

    public double EstimateAmountPayment(ArrayList<Orders> orders) {

        double price = 0;
        for (int i = 0; i < orders.size(); i++) {
            String itemDetails = "[{'id':1,'cat_id':1,'weight':20},{'id':1,'cat_id':2,'weight':30},{'id':1,'cat_id':5,'weight':30}]";
            try {
                JSONArray details = new JSONArray(itemDetails);
                for (int position = 0; position < details.length(); position++) {
                    JSONObject detail = details.getJSONObject(position);
                    int item = detail.getInt("cat_id");
                    int weight = detail.getInt("weight");
                    Rates getRates = new Rates();
                    String itemPrice = getRates.getRates(item, weight);
                    price = price + Double.parseDouble(itemPrice);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return price;
    }

    public double EstimateAmountPaid(ArrayList<Orders> orders) {
        ArrayList<Orders> collectedOrders = new ArrayList<Orders>();
        double price = 0;
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getStatus_id() == 1) {
                collectedOrders.add(orders.get(i));
            }
        }
        for (int i = 0; i < collectedOrders.size(); i++) {
            String itemDetails = "[{'id':1,'cat_id':1,'weight':20},{'id':1,'cat_id':2,'weight':30},{'id':1,'cat_id':5,'weight':30}]";
            try {
                JSONArray details = new JSONArray(itemDetails);
                for (int position = 0; position < details.length(); position++) {
                    JSONObject detail = details.getJSONObject(position);
                    int item = detail.getInt("cat_id");
                    int getWeight = detail.getInt("weight");
                    Rates getRates = new Rates();
                    String itemPrice = getRates.getRates(item,getWeight);
                    price = price + Double.parseDouble(itemPrice);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return price;
    }

}

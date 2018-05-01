package com.greenravolution.gravo.functions;

import com.greenravolution.gravo.R;
import com.greenravolution.gravo.objects.OrderDetails;
import com.greenravolution.gravo.objects.Orders;

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


    public String getRates(int category_id, int weight, String rates) {
        try {
            JSONArray getRates = new JSONArray(rates);
            for (int i = 0; i < getRates.length(); i++) {
                JSONObject rate = getRates.getJSONObject(i);
                if (category_id == rate.getInt("id")) {
                    String price = rate.getString("rate");
                    String[] getPrice = price.split("/");
                    Double pricing = Double.parseDouble(getPrice[0]) * weight;
                    return String.valueOf(pricing);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "0.00";

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
                        case "Metal":
                            return R.drawable.aluminium_cans;
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

    public int getImage(String type) {

        switch (type) {
            case "Paper | Old Newspaper":
                return R.drawable.newspaper;
            case "Paper | Old Paper":
                return R.drawable.recycle_paper;
            case "Paper | Old Cardboard Cartons":
                return R.drawable.cardboard;
            case "Paper | Old Textbooks":
                return R.drawable.books;
            case "Metals | Copper Wires -( <= 4mm diameter )":
                return R.drawable.copperwire_less4mm;
            case "Metals | Copper Wires -( > 4mm diameter)":
                return R.drawable.copperwire_more4mm;
            case "Metals | Untainted -Stripped Copper Wires":
                return R.drawable.strippedcopper_untainted;
            case "Metals | Dirty  -Stripped Copper Wires":
                return R.drawable.strippedcopper_dirty;
            case "Metals | Brass Items - ":
                return R.drawable.brass_tap;
            case "Metals | Copper Pipes or -Copper Plates":
                return R.drawable.copper_plates;
            case "Metals | Telephone Wires - ":
                return R.drawable.tele_wire;
            case "Metals | Aluminium Items - ":
                return R.drawable.aluminium_pans;
            case "Metals | Mixed Wires -(bundled / coiled)":
                return R.drawable.mix_wires_bunde_coil;
            case "E-Waste | Smartphone (operational)":
                return R.drawable.smartphone;
            case "E-Waste | Smartphone (non-operational)":
                return R.drawable.smartphone;

            case "E-Waste | Laptop (non-operational)":
                return R.drawable.laptop;
            case "E-Waste | CPU":
                return R.drawable.cpu;
            case "E-Waste | LCD Screen":
                return R.drawable.lcd;
            case "E-Waste | LCD Screen (Cracked)":
                return R.drawable.lcd;

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
                        case "Metal":
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

    public int getImageColour(String color) {
        switch (color) {
            case "Paper":
                return R.color.brand_yellow;
            case "Metal":
                return R.color.brand_orange;
            case "E-Waste":
                return R.color.brand_purple;
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

    public double EstimateAmountPayment(ArrayList<OrderDetails> orders, String rates) {

        double price = 0;
        for (int i = 0; i < orders.size(); i++) {
            for (int position = 0; position < orders.size(); position++) {
                int item = orders.get(i).getCategory_id();
                int weight = Integer.parseInt(orders.get(i).getWeight());
                Rates getRates = new Rates();
                String itemPrice = getRates.getRates(item, weight, rates);
                price = price + Double.parseDouble(itemPrice);
            }
        }
        return price;
    }

    public double EstimateAmountPaid(ArrayList<Orders> orders, String rates) {
        ArrayList<Orders> collectedOrders = new ArrayList<Orders>();
        double price = 0;
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getStatus_id() == 4) {
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
                    String itemPrice = getRates.getRates(item, getWeight, rates);
                    price = price + Double.parseDouble(itemPrice);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return price;
    }

}

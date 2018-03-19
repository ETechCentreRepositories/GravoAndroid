package com.greenravolution.gravodriver.functions;

import com.greenravolution.gravodriver.Objects.Orders;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by user on 14/3/2018.
 */

public class Rates {
    public Rates(){

    }

    public double getRate(int waste_id, int waste_item){
        switch(waste_id){
            case 1:
                //Paper
                switch(waste_item){
                    case 1:
                        //newspaper
                        return 0.10;
                }
            case 2:
                switch(waste_item){
                    case 7 :
                        //aluminium
                        return 0.20;
                }
            case 3:
                //E-Waste
            case 4:
                //Bulk
        }
        return 0.00;

    }
    public double getRates(int waste_id, int waste_item, int weight){
        switch(waste_id){
            case 1:
                //Paper
                switch(waste_item){
                    case 1:
                        //newspaper
                        double rate =  0.10;
                        return rate* weight;
                }
            case 2:
                switch(waste_item){
                    case 7 :
                        //aluminium
                        double rate =  0.20;
                        return rate* weight;
                }
            case 3:
                //E-Waste
            case 4:
                //Bulk
        }
        return 0.00;

    }
    public String getItem(int waste_id, int waste_item){
        String waste_type ="";
        String waste_item_type = "";
        switch(waste_id){
            case 1:
                waste_type = "Paper";
                //Paper
                switch(waste_item){
                    case 1:
                        waste_item_type = "Old Newspapers";
                        //newspaper
                        return waste_type + " | " + waste_item_type;
                }
            case 2:
                waste_type = "Metals";
                //metals
                switch(waste_item){
                    case 7 :
                        waste_item_type = "Aluminium";
                        //aluminium
                        return waste_type + " | " + waste_item_type;
                }
            case 3:
                //E-Waste
            case 4:
                //Bulk
        }
        return "No Such Item";

    }
    public int GetTotalWeight(ArrayList<Orders> orders){
        int weight = 0;
        for (int i =0; i< orders.size(); i++){
            String itemDetails = orders.get(i).getDetails();
            try {
                JSONArray details = new JSONArray(itemDetails);
                for(int position = 0; position < details.length(); position++){
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
            if(orders.get(i).getStatus_id() == 1) {
                collectedOrders.add(orders.get(i));
            }
        }
        for (int i = 0; i < collectedOrders.size(); i++) {
            String itemDetails = collectedOrders.get(i).getDetails();
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

    public double EstimateAmountPayment(ArrayList<Orders> orders){

        double price = 0;
        for (int i = 0; i < orders.size(); i++) {
            String itemDetails = orders.get(i).getDetails();
            try {
                JSONArray details = new JSONArray(itemDetails);
                for(int position = 0; position < details.length(); position++){
                    JSONObject detail = details.getJSONObject(position);
                    int item = detail.getInt("waste_id");
                    int waste_item = detail.getInt("waste_item");
                    int weight = detail.getInt("weight");
                    Rates getRates = new Rates();
                    double itemPrice = getRates.getRates(item, waste_item, weight);
                    price = price + itemPrice;
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
            if(orders.get(i).getStatus_id() == 1) {
                collectedOrders.add(orders.get(i));
            }
        }
        for (int i = 0; i < collectedOrders.size(); i++) {
            String itemDetails = collectedOrders.get(i).getDetails();
            try {
                JSONArray details = new JSONArray(itemDetails);
                for(int position = 0; position < details.length(); position++){
                    JSONObject detail = details.getJSONObject(position);
                    int item = detail.getInt("waste_id");
                    int waste_item = detail.getInt("waste_item");
                    int getWeight = detail.getInt("weight");
                    Rates getRates = new Rates();
                    double itemPrice = getRates.getRates(item, waste_item, getWeight);
                    price = price + itemPrice;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return price;
    }

}

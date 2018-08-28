package com.greenravolution.gravo.functions;

import com.greenravolution.gravo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Rates {
    public Rates() {

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

    public int getImage(String type) {
        switch (type) {
            case "Paper | Newspaper":
                return R.drawable.paper_main;
            case "Paper | White Paper (black print only)":
                return R.drawable.paper_bp;
            case "Paper | Cardboard Carton":
                return R.drawable.paper_oc;
            case "Paper | Textbook, Magazine, Colored Paper":
                return R.drawable.paper_otb;
            case "Metals | Copper Wire -(Diameter <= 4mm)":
                return R.drawable.metal_copper_wire_one;
            case "Metals | Copper Wire -(Diameter > 4mm)":
                return R.drawable.metal_copper_wire_one;
            case "Metals | Dirty Stripped -Copper Wire":
                return R.drawable.metals_dirty_copper;
            case "Metals | Untainted Stripped -Copper Wire":
                return R.drawable.metals_untainted_copper;
            case "Metals | Brass - ":
                return R.drawable.metal_brass_item;
            case "Metals | Copper Pipe -Copper Plate":
                return R.drawable.metals_copper;
            case "Metals | Aluminium (UBC)- ":
                return R.drawable.metal_aluminium;
            case "Metals | Mixed Wire -(bundled / coiled)":
                return R.drawable.metal_mixed_wires;
            case "E-Waste | Smartphone":
                return R.drawable.ewaste_mobile_phone;
            case "E-Waste | Laptop":
                return R.drawable.ewaste_laptop;
            case "E-Waste | Computer CPU":
                return R.drawable.ewaste_cpu;
            case "E-Waste | Computer Display":
                return R.drawable.ewaste_lcd_screen;
            case "E-Waste | PCB":
                return R.drawable.ewaste_pcb;
            case "E-Waste | Battery":
                return R.drawable.ewaste_battery;
            case "E-Waste | Printer":
                return R.drawable.ewaste_printer;
        }
        return 0;

    }

    public int getImageColour(String color) {
        switch (color) {
            case "Paper":
                return R.color.brand_yellow;
            case "Metals":
                return R.color.brand_orange;
            case "E-Waste":
                return R.color.brand_purple;
        }
        return 0;
    }

}

package com.greenravolution.gravo.CategoryFragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.greenravolution.gravo.R;
import com.greenravolution.gravo.functions.Rates;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class EWaste extends Fragment {
    LinearLayout paperContents;
    public static final String SESSION = "login_status";

    public EWaste() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ewaste, container, false);
        paperContents = view.findViewById(R.id.paperContents);
        SharedPreferences sessionManager = getActivity().getSharedPreferences(SESSION, Context.MODE_PRIVATE);
        String rates = sessionManager.getString("rates", "");
        try {
            JSONArray array = new JSONArray(rates);
            for(int i =0; i< array.length(); i++){
                JSONObject object = array.getJSONObject(i);
                int id = object.getInt("id");
                String types = object.getString("type");
                String[] type = types.split(" ");
                if(type[0].contains("E-Waste")){
                    String rat = object.getString("rate");
                    String typeName = "";
                    for(int j = 2; j < type.length; j++){
                        typeName += type[j]+" ";
                    }
                    com.greenravolution.gravo.objects.Rates rate = new com.greenravolution.gravo.objects.Rates(id,types,rat);
                    paperContents.addView(initView(rate));
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }
    public View initView(com.greenravolution.gravo.objects.Rates rate){
        Rates rateClass = new Rates();
        LayoutInflater inflater2 = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
        View contents = inflater2.inflate(R.layout.category_page_items,null);
        LinearLayout itemView = contents.findViewById(R.id.item);
        TextView itemName = contents.findViewById(R.id.itemName);
        TextView itemsWeight = contents.findViewById(R.id.itemWeight);
        TextView itemRate = contents.findViewById(R.id.itemRate);
        ImageView itemImage = contents.findViewById(R.id.itemImage);
        itemView.setBackgroundColor(getResources().getColor(rateClass.getImageColour("E-Waste")));
        itemImage.setImageResource(rateClass.getImage(rate.getType()));
        String[] type = rate.getType().split(" ");
        String typeName = "";
        for(int j = 2; j < type.length; j++){
            typeName += type[j]+" ";
        }
        itemName.setText(typeName);
        itemRate.setText(rate.getRate());

        return contents;
    }

}

package com.greenravolution.gravodriver.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.greenravolution.gravodriver.Objects.Orders;
import com.greenravolution.gravodriver.R;
import com.greenravolution.gravodriver.TransactionDetails;
import com.greenravolution.gravodriver.functions.Rates;
import com.travijuu.numberpicker.library.NumberPicker;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by user on 19/3/2018.
 */

public class DetailAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater = null;
    private ArrayList<Orders> orders = new ArrayList<>();

    private class ViewHolder{
        NumberPicker itemWeight;
        TextView itemName, ItemPrice, itemRate;
    }

    public DetailAdapter(TransactionDetails transactionDetails, ArrayList<Orders> oal){
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.orders = orders;
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Orders getItem(int position) {
        return orders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final Orders order = getItem(position);
        String details = order.getDetails();
        final ViewHolder holder;
        try {
            if (view == null) {
                view = inflater.inflate(R.layout.item_details, null);
                holder = new ViewHolder();
                holder.itemName = view.findViewById(R.id.getTitle);
                holder.ItemPrice = view.findViewById(R.id.getPrice);
                holder.itemRate = view.findViewById(R.id.getRate);



                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            JSONObject detail = new JSONObject(details);
            int item = detail.getInt("waste_id");
            int waste_item = detail.getInt("waste_item");
            int weight = detail.getInt("weight");
            Rates getRates = new Rates();


            String category = getRates.getItem(item, waste_item);
            holder.itemName.setText(category);
            Log.e("item_id "+item, waste_item+"");






        } catch (Exception ex) {
            Log.d("CHECK", "Error at TrackerAdapter");
            Log.d("CHECK", Log.getStackTraceString(ex));
        }
        return view;
    }
}

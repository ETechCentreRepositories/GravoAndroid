package com.greenravolution.gravodriver.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenravolution.gravodriver.Objects.Orders;
import com.greenravolution.gravodriver.R;

import java.util.ArrayList;

/**
 * Created by user on 13/3/2018.
 */

public class OrdersAdapter extends ArrayAdapter<Orders> {
    private final Context context;
    private ArrayList<Orders> orders;

    public OrdersAdapter(Context context, ArrayList<Orders> orders) {
        super(context, -1, orders);
        this.context = context;
        this.orders = orders;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.schedule_item, parent, false);

        return rowView;
    }
}

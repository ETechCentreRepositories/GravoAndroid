package com.greenravolution.gravodriver.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.greenravolution.gravodriver.Objects.Orders;
import com.greenravolution.gravodriver.R;
import com.greenravolution.gravodriver.TransactionDetails;

import java.util.ArrayList;

/**
 * Created by user on 13/3/2018.
 */

public class OrdersAdapter extends ArrayAdapter<Orders> {

    private final Context context;
    private ArrayList<Orders> orders;
    private AdapterView.OnItemClickListener onItemClickListener;


    public OrdersAdapter(Context context, ArrayList<Orders> orders) {
        super(context, -1, orders);
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.schedule_item, parent, false);
            holder = new ViewHolder();
            holder.tt = convertView.findViewById(R.id.pickupTitle);
            holder.ta = convertView.findViewById(R.id.pickupAddress);
            holder.tpc = convertView.findViewById(R.id.pickupPostal);
            holder.tst = convertView.findViewById(R.id.pickupTiming);
            holder.botw = convertView.findViewById(R.id.botw);
            holder.barr = convertView.findViewById(R.id.barr);
            holder.bmap = convertView.findViewById(R.id.bmap);
            holder.llarr = convertView.findViewById(R.id.llarr);
            holder.llotw = convertView.findViewById(R.id.llotw);
            holder.llarr.setVisibility(View.GONE);

            holder.botw.setTag(holder);

            String title = "Pickup " + String.valueOf(position + 1);
            String address = orders.get(position).getAddress();
            String postal = orders.get(position).getPostal();
            String timing = orders.get(position).getTiming();

            holder.tt.setText(title);
            holder.ta.setText(address);
            holder.tpc.setText(postal);
            holder.tst.setText(timing);

            holder.botw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.llotw.setVisibility(View.GONE);
                    holder.llarr.setVisibility(View.VISIBLE);
                }
            });
            holder.barr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Driver Arrived for " + holder.tt.getText().toString(), Toast.LENGTH_SHORT).show();
                    /// TODO: 14/3/2018 intent to transaction page add in details
                    Intent intent = new Intent(getContext(), TransactionDetails.class);
                    intent.putExtra("details", orders.get(position).getDetails());
                    getContext().startActivity(intent);
                }
            });
            holder.bmap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create a Uri from an intent string. Use the result to create an Intent.
                    String url ="https://www.google.com/maps/dir/?api=1&destination="+orders.get(position).getPostal()+"&travelmode=driving";
                    // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    // Make the Intent explicit by setting the Google Maps package
                    mapIntent.setPackage("com.google.android.apps.maps");
                    // Attempt to start an activity that can handle the Intent
                    getContext().startActivity(mapIntent);
                }
            });
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    public static class ViewHolder {
        LinearLayout llotw, llarr;
        Button botw, barr, bmap;
        TextView tt, ta, tpc, tst;
    }

}

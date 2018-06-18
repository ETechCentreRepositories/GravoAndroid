package com.greenravolution.gravodriver.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.greenravolution.gravodriver.BulkTransactionDetails;
import com.greenravolution.gravodriver.MainActivity;
import com.greenravolution.gravodriver.Objects.Orders;
import com.greenravolution.gravodriver.R;
import com.greenravolution.gravodriver.TransactionDetails;

import java.util.ArrayList;

/**
 * Created by user on 13/3/2018.
 */

public class OrdersAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater = null;
    private ArrayList<Orders> orders = new ArrayList<>();

    public OrdersAdapter(Context context, final ArrayList<Orders> orders) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final Orders order = getItem(position);
        final ViewHolder holder;
        try {
            if (view == null) {
                view = inflater.inflate(R.layout.schedule_item, null);
                holder = new ViewHolder();
                holder.cardView = view.findViewById(R.id.cardView);
                holder.tt = view.findViewById(R.id.pickupTitle);
                holder.ta = view.findViewById(R.id.pickupAddress);
                holder.tpc = view.findViewById(R.id.pickupPostal);
                holder.tst = view.findViewById(R.id.pickupTiming);
                holder.botw = view.findViewById(R.id.botw);
                holder.barr = view.findViewById(R.id.barr);
                holder.bmap = view.findViewById(R.id.bmap);
                holder.llarr = view.findViewById(R.id.llarr);
                holder.llotw = view.findViewById(R.id.llotw);
                holder.llContent = view.findViewById(R.id.llContent);

                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            if (order.getStatus_id() == 4) {

                holder.llotw.setVisibility(View.GONE);
                holder.llarr.setVisibility(View.GONE);
                String title = "Pickup " + String.valueOf(position + 1) + " (Collected)";
                holder.tt.setTextColor(context.getResources().getColor(R.color.white));
                holder.tt.setText(title);
                holder.ta.setText("Address: " + order.getAddress());
                holder.ta.setTextColor(context.getResources().getColor(R.color.white));
                holder.tpc.setText(String.format("Transaction Code: %s", String.valueOf(order.getTransaction_code())));
                holder.tpc.setTextColor(context.getResources().getColor(R.color.white));
//                holder.tst.setText(String.valueOf(order.getSession_id()));
//                holder.tst.setTextColor(context.getResources().getColor(R.color.white));
                holder.cardView.setBackgroundColor(context.getResources().getColor(R.color.grey));
                holder.llContent.setBackgroundColor(context.getResources().getColor(R.color.grey));

            } else if (order.getStatus_id() == 3) {

                holder.llarr.setVisibility(View.GONE);
                String title = "Pickup " + String.valueOf(position + 1) + " (Arrived)";
                holder.tt.setTextColor(context.getResources().getColor(R.color.white));
                holder.tt.setText(title);
                holder.ta.setText("Address: " + order.getAddress());
                holder.ta.setTextColor(context.getResources().getColor(R.color.white));
                holder.tpc.setText(String.format("Transaction Code: %s", String.valueOf(order.getTransaction_code())));
                holder.tpc.setTextColor(context.getResources().getColor(R.color.white));
//                holder.tst.setText(String.valueOf(order.getSession_id()));
//                holder.tst.setTextColor(context.getResources().getColor(R.color.white));
                holder.cardView.setBackgroundColor(context.getResources().getColor(R.color.brand_pink));
                holder.llContent.setBackgroundColor(context.getResources().getColor(R.color.brand_pink));

            } else {
                //normal pickup
                holder.llotw.setVisibility(View.GONE);
                String title = "Pickup " + String.valueOf(position + 1);
                holder.tt.setText(title);
                holder.ta.setText(String.format("Address: %s", order.getAddress()));
                holder.tpc.setText(String.format("Transaction Code: %s", String.valueOf(order.getTransaction_code())));
            }

//            holder.botw.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    holder.llarr.setVisibility(View.VISIBLE);
//                    holder.llotw.setVisibility(View.GONE);
//                }
//            });

            holder.barr.setOnClickListener(v -> {
                /// TODO: 14/3/2018 intent to transaction page add in details
//                if (order.getTransaction_type().equals("1")) {
                Intent intent = new Intent(context, TransactionDetails.class);
                Orders orders = getItem(position);
                intent.putExtra("address", orders.getAddress());
                intent.putExtra("transaction_id", orders.getTransaction_code());
                intent.putExtra("id", orders.getId());

                MainActivity.updatetransaction updatetransaction = new MainActivity.updatetransaction();
                updatetransaction.execute(String.valueOf(orders.getId()));
                ((MainActivity) context).startActivityForResult(intent, 1);
//                } else if (order.getTransaction_type().equals("2")) {
//                    Intent intent = new Intent(context, BulkTransactionDetails.class);
//                    Orders orders = getItem(position);
//                    intent.putExtra("address", orders.getAddress());
//                    intent.putExtra("transaction_id", orders.getTransaction_code());
//                    intent.putExtra("id", orders.getId());
//
//                    ((MainActivity) context).startActivityForResult(intent, 1);
//                } else {
//                    //TODO completed page summary;
//                }

            });

            holder.bmap.setOnClickListener(v -> {
                // Create a Uri from an intent string. Use the result to create an Intent.
                String url = "https://www.google.com/maps/dir/?api=1&destination=" + orders.get(position).getAddress() + "&travelmode=driving";
                // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                // Make the Intent explicit by setting the Google Maps package
                mapIntent.setPackage("com.google.android.apps.maps");
                // Attempt to start an activity that can handle the Intent
                context.startActivity(mapIntent);
            });


        } catch (Exception ex) {
            Log.d("CHECK", "Error at TrackerAdapter");
            Log.d("CHECK", Log.getStackTraceString(ex));
        }
        return view;
    }

    private class ViewHolder {
        CardView cardView;
        LinearLayout llotw, llarr, llContent;
        Button botw, barr, bmap;
        TextView tt, ta, tpc, tst;
    }
}

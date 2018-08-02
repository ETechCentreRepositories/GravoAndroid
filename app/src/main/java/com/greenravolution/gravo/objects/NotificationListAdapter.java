package com.greenravolution.gravo.objects;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greenravolution.gravo.R;

import java.util.Calendar;
import java.util.List;

public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.NotificationViewHolder> {

    class NotificationViewHolder extends RecyclerView.ViewHolder {
        private final TextView notificationItemView;
        private final TextView tvTime;

        private NotificationViewHolder(View itemView) {
            super(itemView);
            notificationItemView = itemView.findViewById(R.id.tvMessage);
            tvTime = itemView.findViewById(R.id.tvTime);
        }
    }

    private LayoutInflater mInflater;
    private List<Notification> allNotifications; // Cached copy of words

    public NotificationListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new NotificationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {
        if (allNotifications != null) {
            Notification current = allNotifications.get(position);
            holder.notificationItemView.setText(current.getMessage());
            Calendar currentC = Calendar.getInstance();

            long difference = (currentC.getTimeInMillis()- current.getTime()  );
            Log.i("day difference time" ,currentC.getTimeInMillis() + " " + current.getTime());
            //int difference = 380000000;
            int seconds = (int) (difference / 1000) % 60 ;
            int minutes = (int) ((difference / (1000*60)) % 60);
            int hours   = (int) ((difference / (1000*60*60)) % 24);
            int days = (int) (difference / (1000*60*60*24));
            Log.i("day difference","Days: " + days + " Hours: " + hours + " Min: " + minutes + " Sec: " + seconds + " Difference: " + difference);
            if(days > 0){
                holder.tvTime.setText(days + "d ago");
            } else if (hours > 0){
                holder.tvTime.setText(hours + "h ago");
            } else if (minutes > 0){
                holder.tvTime.setText(minutes + "min ago");
            } else {
                holder.tvTime.setText(seconds + "s ago");
            }
        } else {
            // Covers the case of data not being ready yet.
            holder.notificationItemView.setText("No Notifications");
        }
    }

    public void setWords(List<Notification> words){
        allNotifications = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (allNotifications != null)
            return allNotifications.size();
        else return 0;
    }

    public Notification getNotificationAtPosition (int position) {
        return allNotifications.get(position);
    }

}

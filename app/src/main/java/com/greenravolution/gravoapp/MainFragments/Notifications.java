package com.greenravolution.gravoapp.MainFragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.greenravolution.gravoapp.R;
import com.greenravolution.gravoapp.functions.DBHelper;
import com.greenravolution.gravoapp.objects.Notification;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Notifications extends Fragment {
    LinearLayout notificationsList;
    SwipeRefreshLayout refreshNoti;



    public Notifications() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        notificationsList = view.findViewById(R.id.notifications_list);
        refreshNoti = view.findViewById(R.id.refresh_noti);
        refreshNoti.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshNoti.setRefreshing(false);
                getNotifications();
            }
        });
        getNotifications();
        return view;
    }
    private void getNotifications(){
        notificationsList.removeAllViews();
        DBHelper dbHelper = new DBHelper(this.getContext());
        ArrayList<Notification> notifications = dbHelper.getAllNotifications();
        Log.e("NOTIFICATION","IN NOTIFCATION PAGE: NOTI COUNT: "+notifications.size());
        for (int i=0; i< notifications.size();i++){
            Log.e("NOTIFICATION",notifications.get(i).getId()+" ");
            Log.e("NOTIFICATION",notifications.get(i).getMessage()+" ");
            Log.e("NOTIFICATION",notifications.get(i).getTitle()+" ");
            notificationsList.addView(addNotifications(notifications.get(i)));
        }
    }
    private View addNotifications(Notification notification){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.recyclerview_item,null,false);
        TextView message = view.findViewById(R.id.tvMessage);
        ImageView btnClose = view.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(getContext());
                dbHelper.deleteNotification(notification);
                getNotifications();
            }
        });
        message.setText(notification.getTitle()+"\n\n"+notification.getMessage());

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

}

package com.greenravolution.gravoapp.MainFragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.greenravolution.gravoapp.R;
import com.greenravolution.gravoapp.functions.GetAsyncRequest;
import com.greenravolution.gravoapp.functions.HttpReq;
import com.greenravolution.gravoapp.objects.Notification;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;


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

    private void getNotifications() {
        notificationsList.removeAllViews();
//        notificationsList.addView(addNotifications(notifications.get(i)));
        getnotifications getnotifications = new getnotifications();
        try {
            JSONObject result = new JSONObject(getnotifications.execute().get());
            int status = result.getInt("status");
            if(status==200){
                JSONArray notifications = result.getJSONArray("result");
                for(int i = 0;i< notifications.length();i++){
                    JSONObject notification = notifications.getJSONObject(i);
                    notificationsList.addView(addNotifications(
                            new Notification(notification.getInt("id")
                                    , notification.getString("recycler_id")
                                    , notification.getString("title")
                                    , notification.getString("message")
                                    , notification.getString("datetime"))));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private View addNotifications(Notification notification) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.recyclerview_item, null, false);
        TextView message = view.findViewById(R.id.tvMessage);
        ImageView btnClose = view.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = notification.getId();
                deletenotifications deletenotifications = new deletenotifications();
                try {
                    String delete = deletenotifications.execute(String.valueOf(id)).get();
                    int status = new JSONObject(delete).getInt("status");
                    if(status==200){
                        getNotifications();
                    }else{
                        Toast.makeText(getContext(), "An unexpected error has occurred", Toast.LENGTH_LONG).show();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        message.setText(notification.getTitle() + "\n\n" + notification.getMessage());


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }
    public class getnotifications extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            SharedPreferences sp = getContext().getSharedPreferences("login_status",Context.MODE_PRIVATE);
            String id = String.valueOf(sp.getInt("user_id",-1));
            HttpReq req = new HttpReq();
            return req.GetRequest("https://www.ehostingcentre.com/gravo/getnotifications.php?id="+id);
        }
    }
    public class deletenotifications extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            HttpReq req = new HttpReq();
            return req.PostRequest("https://www.ehostingcentre.com/gravo/deletenotification.php","id="+strings[0]);
        }
    }
    public void getTransactions() {     //get list of items
        GetAsyncRequest asyncRequest = new GetAsyncRequest();
        SharedPreferences sessionManager = getActivity().getSharedPreferences("login_status", Context.MODE_PRIVATE);
        String collectorid = sessionManager.getString("id", "");
        asyncRequest.execute("http://ehostingcentre.com/gravo/gettransaction.php?type=withcollectorid&id=" + collectorid);
    }
}


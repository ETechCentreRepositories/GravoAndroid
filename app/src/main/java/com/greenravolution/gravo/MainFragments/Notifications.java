package com.greenravolution.gravo.MainFragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.greenravolution.gravo.R;
import com.greenravolution.gravo.objects.Notification;
import com.greenravolution.gravo.objects.NotificationListAdapter;
import com.greenravolution.gravo.objects.NotificationViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Notifications extends Fragment {

    private NotificationViewModel mNotificationViewModel;
    NotificationListAdapter adapter;

    public Notifications() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);


        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
         adapter = new NotificationListAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Toast.makeText(getContext(), "Moving", Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                // Remove item from backing list here

                Toast.makeText(getContext(), "deleted " , Toast.LENGTH_SHORT).show();
                int position = viewHolder.getAdapterPosition();
                    Notification myNotification = adapter.getNotificationAtPosition(position);

                // Delete the word
                mNotificationViewModel.deleteNotification(myNotification);
                adapter.notifyDataSetChanged();
            }
        });

        itemTouchHelper.attachToRecyclerView(recyclerView);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);

        mNotificationViewModel = ViewModelProviders.of(this).get(NotificationViewModel.class);

        mNotificationViewModel.getAllNotifications().observe(this, new Observer<List<Notification>>() {
            @Override
            public void onChanged(@Nullable final List<Notification> listNotifications){
                adapter.setWords(listNotifications);
            }
        });

    }

}

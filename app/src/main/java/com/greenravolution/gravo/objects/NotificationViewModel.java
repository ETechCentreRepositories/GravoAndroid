package com.greenravolution.gravo.objects;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

public class NotificationViewModel extends AndroidViewModel {
    private NotificationRepository mRepository;

    private LiveData<List<Notification>> allNotifications;

    public NotificationViewModel (Application application){
        super(application);
        mRepository = new NotificationRepository(application);
        allNotifications = mRepository.getAllNotifications();
    }

    public LiveData<List<Notification>> getAllNotifications() {
        return allNotifications;
    }

    public void insert(Notification notification) {
        mRepository.insert(notification);
    }

    //Warning: Never pass context into ViewModel instances. Do not store Activity, Fragment, or View instances or their Context in the ViewModel.
    //
    //For example, an Activity can be destroyed and created many times during the lifecycle of a ViewModel as the device is rotated.
    // If you store a reference to the Activity in the ViewModel, you end up with references that point to the destroyed Activity.
    // This is a memory leak.
    //
    //If you need the application context, use AndroidViewModel, as shown in this codelab.



}

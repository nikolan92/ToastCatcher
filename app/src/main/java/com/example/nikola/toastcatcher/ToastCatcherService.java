package com.example.nikola.toastcatcher;

import android.accessibilityservice.AccessibilityService;
import android.app.Notification;
import android.content.Intent;
import android.os.Parcelable;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

import com.example.nikola.toastcatcher.DataLayer.LogItem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;


public class ToastCatcherService extends AccessibilityService {

    LinkedList<LogItem> logItems;
    int numberOfLogItems;

    private static ToastCatcherService toastCatcherServiceInstance;

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Toast.makeText(this, "ToastCather listen for toast messages.",Toast.LENGTH_SHORT).show();
        logItems = new LinkedList<>();
        toastCatcherServiceInstance = this;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        toastCatcherServiceInstance = null;
        return super.onUnbind(intent);
    }
    public static ToastCatcherService getSharedInstance(){
        return toastCatcherServiceInstance;
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() != AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED)
            return; // event is not a notification

        String sourcePackageName = (String) event.getPackageName();

        Parcelable parcelable = event.getParcelableData();
        if (parcelable instanceof Notification) {
            // Statusbar Notification
        }
        else if(!sourcePackageName.equals(this.getPackageName())){
            // something else, e.g. a Toast message

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd      HH:mm:ss");
            String currentDateAndTime = sdf.format(new Date());

            logItems.addFirst(new LogItem(currentDateAndTime, event.getText().toString(),(String) event.getPackageName()));
            numberOfLogItems++;
            if(numberOfLogItems>10) {
                logItems.removeLast();
                numberOfLogItems--;
            }
        }
    }
    @Override
    public void onInterrupt() {

    }
}

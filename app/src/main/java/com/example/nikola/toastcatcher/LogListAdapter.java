package com.example.nikola.toastcatcher;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nikola.toastcatcher.DataLayer.LogItem;

import java.util.ArrayList;

public class LogListAdapter extends ArrayAdapter{


    public LogListAdapter(Context context, ArrayList logs) {
        super(context, 0,logs);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item, parent, false);
        }

        LogItem item = (LogItem) getItem(position);
        TextView date = (TextView) convertView.findViewById(R.id.textViewDate);
        TextView message = (TextView) convertView.findViewById(R.id.textViewMessage);
        TextView source = (TextView) convertView.findViewById(R.id.textViewSource);

        date.setText(item.getTime());
        message.setText(item.getMessage());
        source.setText(item.getSourcePackage());

        //return super.getView(position, convertView, parent);
        return convertView;
    }

}

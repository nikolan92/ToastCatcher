package com.example.nikola.toastcatcher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.nikola.toastcatcher.DataLayer.LogItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class ToastCatcher extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast_catcher);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView =(ListView) findViewById(R.id.list_view_toast_messages);

    }

    @Override
    protected void onResume() {
        super.onResume();
        fillListView();
    }

    private void fillListView(){
        ToastCatcherService toastCatcherService = ToastCatcherService.getSharedInstance();

        if(toastCatcherService != null){
            ArrayList<LogItem> logItems = new ArrayList<>();

            Iterator item = toastCatcherService.logItems.descendingIterator();
            while (item.hasNext())
                logItems.add((LogItem) item.next());

            //logItems.add(new LogItem("5:55  01/05/2017","Some text","package.nikola.example"));
            //logItems.add(new LogItem("5:35  01/03/2017","Some text","package.nikola.example"));
            if(logItems.size()>0) {
                LogListAdapter adapter = new LogListAdapter(this, logItems);
                listView.setAdapter(adapter);
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toast_catcher, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            fillListView();

        }else if(id == R.id.action_clear_list){
            ToastCatcherService toastCatcherService = ToastCatcherService.getSharedInstance();
            if(toastCatcherService != null){
                toastCatcherService.logItems = new LinkedList<>();
                toastCatcherService.numberOfLogItems = 0;
                listView.setAdapter(new LogListAdapter(this,new ArrayList()));
                fillListView();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}

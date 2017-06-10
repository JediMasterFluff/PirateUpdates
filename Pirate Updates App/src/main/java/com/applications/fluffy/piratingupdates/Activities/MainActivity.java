package com.applications.fluffy.piratingupdates.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.applications.fluffy.piratingupdates.Helpers.TorrentRVAdapter;
import com.applications.fluffy.piratingupdates.Objects.Preferences;
import com.applications.fluffy.piratingupdates.Objects.Torrents;
import com.applications.fluffy.piratingupdates.R;

import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getActionBar();

        actionBar.setTitle(""); // No title

        Intent intent = getIntent();
        Bundle b = intent.getBundleExtra(SplashScreen.MESSAGE);
        ArrayList<Torrents> torrentsList = b.getParcelableArrayList(SplashScreen.MESSAGE);

        RecyclerView rv = (RecyclerView) findViewById(R.id.activity_main);

        LinearLayoutManager llm = new LinearLayoutManager(getBaseContext());
        rv.setLayoutManager(llm);

        TorrentRVAdapter adapter = new TorrentRVAdapter(torrentsList);
        rv.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        CreateMenu(menu);
        return true;
    }

    private void CreateMenu(Menu menu) {
        // Start preference activity
        MenuItem add = menu.add(0, 0, 0, "Add RSS Feed");
        {
            add.setIcon(R.drawable.ic_settings_white);
            add.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }
    }

    private boolean MenuChoice(MenuItem item) {

        switch (item.getItemId()) {
            case 0:
                //Open Preferences Screen
                startActivity(new Intent(this, Preferences.class));
                return true;
            default:
                Toast.makeText(this, "Not working", Toast.LENGTH_SHORT).show();
                return true;
        }

        //return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_download) {
            return true;
        }

        return MenuChoice(item);
    }
}

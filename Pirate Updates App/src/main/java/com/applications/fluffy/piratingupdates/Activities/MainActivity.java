package com.applications.fluffy.piratingupdates.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.applications.fluffy.piratingupdates.Loaders.XMLParser;
import com.applications.fluffy.piratingupdates.Objects.Torrents;
import com.applications.fluffy.piratingupdates.R;

import java.util.Vector;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "Torrent Object";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void readRss(View view){

        try {
            XMLParser parse = new XMLParser();

            parse.execute();

            Vector<Torrents> vector = parse.get();

            Intent intent = new Intent(this, TorrentActivity.class);

            intent.putExtra(EXTRA_MESSAGE, vector.elementAt(0));
            startActivity(intent);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


}

package com.applications.fluffy.piratingupdates.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.applications.fluffy.piratingupdates.Objects.Torrents;
import com.applications.fluffy.piratingupdates.R;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Torrents> torrentsVector;
    public final static String EXTRA_MESSAGE = "Torrents Object";
    public Bitmap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        Bundle b = intent.getBundleExtra(SplashScreen.MESSAGE);

        torrentsVector = b.getParcelableArrayList(SplashScreen.MESSAGE);

        loadTable(torrentsVector);

    }

    private void loadTable(ArrayList<Torrents> torrentsArrayList) {
        int index = 0;

        for(final Torrents tor : torrentsArrayList){
            TableLayout tbl = (TableLayout) findViewById(R.id.activity_main_table);
            TableRow tr = new TableRow(this);
            ImageView imageview;
            imageview = new ImageView(this);
            map = tor.readBitmap();
            imageview.setImageBitmap(map);
            imageview.setClickable(true);
            imageview.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, TorrentActivity.class);
                    intent.putExtra(EXTRA_MESSAGE, tor);

                    startActivity(intent);
                }
            } );
            tr.addView(imageview);
            tbl.addView(tr, index);
            index++;
        }

    }

    private void onClick(View view){

    }


}

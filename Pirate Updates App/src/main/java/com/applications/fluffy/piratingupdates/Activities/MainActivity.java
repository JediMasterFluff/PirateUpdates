package com.applications.fluffy.piratingupdates.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
        int colIndex;
        TableRow tr;

        for(int i = 0; i<= torrentsArrayList.size(); i = i + 2){
            TableLayout tbl = (TableLayout) findViewById(R.id.activity_main_table);

            tr = new TableRow(this);
            for(colIndex = 0; colIndex < 2; colIndex++) {
                ImageView imageview = new ImageView(this);

                // Make imageview look pretty
                final float scale = getBaseContext().getResources().getDisplayMetrics().density;

                int height = (int) (200 * scale + 0.5F);
                int width = (int) (150 * scale + 0.5F);
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(width,height);
                layoutParams.setMargins(6,6,6,6);
                imageview.setLayoutParams(layoutParams);

                final Torrents tor;
                if(colIndex == 0) {
                    tor = torrentsArrayList.get(i);
                }
                else {
                    tor = torrentsArrayList.get(i++);
                }
                map = tor.readBitmap();
                imageview.setImageBitmap(map);
                imageview.setClickable(true);
                imageview.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, TorrentActivity.class);
                        intent.putExtra(EXTRA_MESSAGE, tor);

                        startActivity(intent);
                    }
                });
                tr.addView(imageview, colIndex);
            }
            tbl.addView(tr, index);
        }

    }
}

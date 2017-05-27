package com.applications.fluffy.piratingupdates.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.applications.fluffy.piratingupdates.Helpers.TorrentRVAdapter;
import com.applications.fluffy.piratingupdates.Objects.Torrents;
import com.applications.fluffy.piratingupdates.R;

import java.util.ArrayList;

import static com.applications.fluffy.piratingupdates.Helpers.TorrentRVAdapter.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {
    private Bitmap map;

    public MainActivity returnActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Bundle b = intent.getBundleExtra(SplashScreen.MESSAGE);
        ArrayList<Torrents> torrentsList = b.getParcelableArrayList(SplashScreen.MESSAGE);

        RecyclerView rv = (RecyclerView) findViewById(R.id.activity_main);

        LinearLayoutManager llm = new LinearLayoutManager(getBaseContext());
        rv.setLayoutManager(llm);

        TorrentRVAdapter adapter = new TorrentRVAdapter(torrentsList);
        rv.setAdapter(adapter);

    }

    private void loadTable(ArrayList<Torrents> torrentsArrayList) {
        int index = 0;
        int colIndex;
        TableRow tr;

        for(int i = 0; i<= torrentsArrayList.size(); i = i + 2){
            TableLayout tbl = (TableLayout) findViewById(R.id.activity_main);

            tr = new TableRow(this);
            for(colIndex = 0; colIndex < 2; colIndex++) {
                ImageView imageview = new ImageView(this);

                // Make imageview look pretty
                final float scale = getBaseContext().getResources().getDisplayMetrics().density;

                int height = (int) (200 * scale + 0.5F);
                int width = (int) (150 * scale + 0.5F);
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(width,height);
                layoutParams.setMargins(6, 6, 6, 6);// Left, Top, Right, Bottom
                imageview.setLayoutParams(layoutParams);

                final Torrents tor;
                if(colIndex == 0) {
                    tor = torrentsArrayList.get(i);
                }
                else {
                    tor = torrentsArrayList.get(i++);
                }
                //map = tor.readBitmap();
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

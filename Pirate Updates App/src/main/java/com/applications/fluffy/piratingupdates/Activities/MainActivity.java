package com.applications.fluffy.piratingupdates.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.applications.fluffy.piratingupdates.Objects.Torrents;
import com.applications.fluffy.piratingupdates.R;

import java.util.Vector;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private Vector<Torrents> torrentsVector;
    public final static String EXTRA_MESSAGE = "Torrents Object";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        torrentsVector = (Vector<Torrents>) intent.getParcelableExtra(SplashScreen.MESSAGE);

        loadTable(torrentsVector);

    }

    private void loadTable(Vector<Torrents> torrentsVector) {
        int index = 0;
        TableLayout tbl = (TableLayout) findViewById(R.id.activity_main_table);
        TableRow tr = new TableRow(this);
        ImageView imageview;

        for(Torrents tor : torrentsVector){
            imageview = new ImageView(this);
            imageview.setImageBitmap(tor.getImage());
            imageview.setClickable(true);
            imageview.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {

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

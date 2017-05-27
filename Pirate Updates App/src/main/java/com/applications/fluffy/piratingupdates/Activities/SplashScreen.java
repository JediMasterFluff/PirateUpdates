package com.applications.fluffy.piratingupdates.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.applications.fluffy.piratingupdates.Helpers.ImageLoadTask;
import com.applications.fluffy.piratingupdates.Helpers.XMLParser;
import com.applications.fluffy.piratingupdates.Objects.Torrents;
import com.applications.fluffy.piratingupdates.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class SplashScreen extends Activity implements Runnable {
    private Thread mThread;
    private Intent intent;
    public final static String MESSAGE = "TORRENT Vector";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        mThread = new Thread(this);

        mThread.start();
    }

    @Override
    public void run()
    {
        try
        {
            //Read RSS Feed(s)
            ArrayList<Torrents> torrentsArrayList = readRss();

            //Create Bitmaps for all torrents
            torrentsArrayList = createBitmaps(torrentsArrayList);

            intent = new Intent(this, MainActivity.class);

            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(MESSAGE, torrentsArrayList);
            intent.putExtra(MESSAGE, bundle);

            startActivity(intent);

            //Thread.sleep(2000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            finish();
        }
    }

    private ArrayList<Torrents> readRss(){
        ArrayList<Torrents> list = new ArrayList<>();
        try {
            XMLParser parse = new XMLParser();
            parse.execute();
            list = parse.get();

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return list;

    }

    private ArrayList<Torrents> createBitmaps(ArrayList<Torrents> list){
        try {
            for(Torrents tor : list){
                ImageLoadTask ilt = new ImageLoadTask(tor.getPosterImgLink(), tor);
                ilt.execute();
                String path = getBaseContext().getCacheDir().getPath();
                path = path + "/" + tor.getTitle().replace(" ", "");
                System.out.println("[INFO] - PATH TO BITMAP ~" + path);
                tor.saveBitmap(path, ilt.get());

            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return list;
    }
}
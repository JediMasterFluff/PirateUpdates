package com.applications.fluffy.piratingupdates.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import com.applications.fluffy.piratingupdates.Loaders.ImageLoadTask;
import com.applications.fluffy.piratingupdates.Loaders.XMLParser;
import com.applications.fluffy.piratingupdates.Objects.Torrents;
import com.applications.fluffy.piratingupdates.R;

import java.util.Vector;
import java.util.concurrent.ExecutionException;


public class SplashScreen extends Activity implements Runnable {
    Thread mThread;
    private Vector<Torrents> torVect;
    Intent intent;
    public final static String MESSAGE = "TORRENT VECTOR";
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
            torVect = readRss();

            //Create Bitmaps for all torrents
            torVect = createBitmaps(torVect);
            intent = new Intent(this, MainActivity.class);
            intent.putExtra(MESSAGE, torVect);

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

    private Vector<Torrents> readRss(){
        Vector<Torrents> vector = new Vector<Torrents>();
        try {
            XMLParser parse = new XMLParser();
            parse.execute();
            vector = parse.get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return vector;

    }

    private Vector<Torrents> createBitmaps(Vector<Torrents> vector){
        try {
        for(Torrents tor : vector){
            ImageLoadTask ilt = new ImageLoadTask(tor.getPosterImgLink(), tor);
            ilt.execute();

            tor.setImage(scaleDownBitmap(ilt.get(),250, getBaseContext()));

        }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return vector;
    }

    private static Bitmap scaleDownBitmap(Bitmap photo, int newHeight, Context context) {

        final float densityMultiplier = context.getResources().getDisplayMetrics().density;

        int h= (int) (newHeight*densityMultiplier);
        int w= (int) (h * photo.getWidth()/((double) photo.getHeight()));

        photo=Bitmap.createScaledBitmap(photo, w, h, true);

        return photo;
    }
}
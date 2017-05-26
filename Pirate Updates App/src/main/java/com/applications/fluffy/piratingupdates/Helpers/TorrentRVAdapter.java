package com.applications.fluffy.piratingupdates.Helpers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.applications.fluffy.piratingupdates.Activities.TorrentActivity;
import com.applications.fluffy.piratingupdates.Objects.Torrents;
import com.applications.fluffy.piratingupdates.R;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by fluffy on 26/05/17.
 */

public class TorrentRVAdapter extends RecyclerView.Adapter<TorrentRVAdapter.TorrentViewHolder> {

    private ArrayList<Torrents> torrents;
    public final static String EXTRA_MESSAGE = "Torrents Object";
    private Bitmap map;
    public TorrentRVAdapter(ArrayList<Torrents> torrents) {
        this.torrents = torrents;
    }


    static class TorrentViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView title;
        TextView genre;
        TextView desc;
        ImageView poster;

        TorrentViewHolder(View view) {
            super(view);
            cv = (CardView) view.findViewById(R.id.t_cv);
            title = (TextView) view.findViewById(R.id.torrent_title);
            genre = (TextView) view.findViewById(R.id.torrent_genre);
            desc = (TextView) view.findViewById(R.id.torrent_desc);
            poster = (ImageView) view.findViewById(R.id.torrent_poster);
        }

    }

    @Override
    public TorrentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.torrent_cardview, parent, false);
        return new TorrentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TorrentViewHolder holder, int i) {

        holder.desc.setText(torrents.get(i).shortDesc());
        holder.genre.setText(torrents.get(i).getGenre());
        holder.title.setText(torrents.get(i).getTitle());

        try {
            map = torrents.get(i).readBitmap();
        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.poster.setImageBitmap(map);
        //holder.poster.setImageResource(R.mipmap.no_image_found);

        final Torrents tor = torrents.get(i);
        holder.cv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                Intent intent = new Intent(context, TorrentActivity.class);
                intent.putExtra(EXTRA_MESSAGE, tor);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return torrents.size();
    }
}

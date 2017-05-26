package com.applications.fluffy.piratingupdates.Helpers;

import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.applications.fluffy.piratingupdates.Objects.Torrents;
import com.applications.fluffy.piratingupdates.R;

import java.util.ArrayList;

/**
 * Created by fluffy on 26/05/17.
 */

public class TorrentRVAdapter extends RecyclerView.Adapter<TorrentRVAdapter.TorrentViewHolder> {

    private ArrayList<Torrents> torrents;

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
        }

    }

    @Override
    public TorrentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.torrent_cardview, parent, false);
        return new TorrentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TorrentViewHolder holder, int i) {
        Bitmap map = torrents.get(i).readBitmap();

        holder.desc.setText(torrents.get(i).getDescription());
        holder.genre.setText(torrents.get(i).getGenre());
        holder.title.setText(torrents.get(i).getTitle());
        holder.poster.setImageBitmap(map);
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

package com.applications.fluffy.piratingupdates.Objects;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fluffy on 03/02/17.
 */

public class Torrents {
    //Attributes
    private String title;
    private String description;
    private String torrentWebLink;
    private String torrentDownloadLink;
    private String posterImgLink;
    private String genre;
    private String size;
    private String runtime;
    private String pubDate;
    private String imdbRating;
    private Double rottenRating;
    private int seeders;
    private int peers;

    public Torrents() {
        this.title = "";
        this.description = "";
        this.torrentWebLink = "";
        this.torrentDownloadLink = "";
        this.posterImgLink = "";
        this.genre = "";
        this.size = "";
        this.runtime = "";
        this.pubDate = "";
        this.imdbRating = "";
        this.rottenRating = 0.0;
        this.seeders = 0;
        this.peers = 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {

        Pattern pattern = Pattern.compile("<img src=(.*?) alt");
        Matcher matcher = pattern.matcher(description);
        if(matcher.find()){
            System.out.print("Description Matcher : ");
            this.setPosterImgLink(matcher.group(1));
        }

        this.description = description;
    }

    public String getTorrentWebLink() {
        return torrentWebLink;
    }

    public void setTorrentWebLink(String torrentWebLink) {
        this.torrentWebLink = torrentWebLink;
    }

    public String getTorrentDownloadLink() {
        return torrentDownloadLink;
    }

    public void setTorrentDownloadLink(String torrentDownloadLink) {
        this.torrentDownloadLink = torrentDownloadLink;
    }

    public String getPosterImgLink() {
        return posterImgLink;
    }

    public void setPosterImgLink(String posterImgLink) {
        this.posterImgLink = posterImgLink;
    }

    public String getGenre() {
        return genre;
    }

    private void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSize() {
        return size;
    }

    private void setSize(String size) {
        this.size = size;
    }

    public String getRuntime() {
        return runtime;
    }

    private void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public Double getRottenRating() {
        return rottenRating;
    }

    public void setRottenRating(Double rottenRating) {
        this.rottenRating = rottenRating;
    }

    public int getSeeders() {
        return seeders;
    }

    public void setSeeders(int seeders) {
        this.seeders = seeders;
    }

    public int getPeers() {
        return peers;
    }

    public void setPeers(int peers) {
        this.peers = peers;
    }
}

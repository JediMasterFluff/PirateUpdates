package com.applications.fluffy.piratingupdates.Objects;

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
    private String pubDate;
    private Double imdbRating;
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
        this.pubDate = "";
        this.imdbRating = 0.0;
        this.rottenRating = 0.0;
        this.seeders = 0;
        this.peers = 0;
    }

    @Override
    public String toString() {
        return "com.applications.fluffy.piratingupdates.Objects.Torrents{" +
                "name='" + title + '\'' +
                ", description='" + description + '\'' +
                ", torrentWebLink='" + torrentWebLink + '\'' +
                ", torrentDownloadLink='" + torrentDownloadLink + '\'' +
                ", posterImgLink='" + posterImgLink + '\'' +
                ", genre='" + genre + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", imdbRating=" + imdbRating +
                ", rottenRating=" + rottenRating +
                ", seeders=" + seeders +
                ", peers=" + peers +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTorrentWebLink() {
        return torrentWebLink;
    }

    public void setTorrentLink(String torrentLink) {
        this.torrentWebLink = torrentLink;
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

    public Double getImdbRating() {
        return imdbRating;
    }

    private void setImdbRating(Double imdbRating) {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTorrentWebLink(String torrentWebLink) {
        this.torrentWebLink = torrentWebLink;
    }

    public void setTorrentDownloadLink(String torrentDownloadLink) {
        this.torrentDownloadLink = torrentDownloadLink;
    }

    public String getTorrentDownloadLink() {
        return torrentDownloadLink;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
}

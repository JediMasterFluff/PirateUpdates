package com.applications.fluffy.piratingupdates.Objects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by fluffy on 03/02/17.
 */

public class Torrents implements Parcelable {
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

    private byte[] image;

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

        setPosterImgLink(description);

        String replaceStr = description.replaceAll("\\<.*?\\>", "|").trim();

        ArrayList<String> descArr = new ArrayList<>(Arrays.asList(replaceStr.split("\\|")));

        for (String str : descArr) {
            if (str.length() != 0) {
                switch (str.substring(0, 4)) {
                    case "IMDB":
                        setImdbRating(str);
                        break;
                    case "Genr":
                        setGenre(str);
                        break;
                    case "Size":
                        setSize(str);
                        break;
                    case "Runt":
                        setRuntime(str);
                        break;
                    default:
                        this.description = str;
                        break;
                }

                System.out.println("~~~~~~~~~~~ Substring ~~~~~~~~~~~");
                System.out.println(str.substring(0, 3));
            }
        }
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

    private void setPosterImgLink(String posterImgLink) {

        Pattern pattern = Pattern.compile("<img src=(.*?) alt");
        Matcher matcher = pattern.matcher(posterImgLink);
        if(matcher.find())
            this.posterImgLink = matcher.group(1).replace("\"", "");

    }

    public String getGenre() {
        return genre;
    }

    private void setGenre(String genre) {
        this.genre = genre.replace("Genre: ", "");
    }

    public String getSize() {
        return size;
    }

    private void setSize(String size) {
        this.size = size.replace("Size: ", "");
    }

    public String getRuntime() {
        return runtime;
    }

    private void setRuntime(String runtime) {
        this.runtime = runtime.replace("Runtime: ", "");
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

    private void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating.replace("IMDB Rating: ", "");
    }

    public Double getRottenRating() {
        return rottenRating;
    }

    public void setRottenRating(Double rottenRating) {
        this.rottenRating = rottenRating;
    }

    public Bitmap getImage() {
        return unCompressBitmap(image);
    }

    public void setImage(Bitmap image) {
        this.image = compressBitmap(image);
    }

    private byte[] compressBitmap(Bitmap map){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        map.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    private Bitmap unCompressBitmap(byte[] byteMap){
        return BitmapFactory.decodeByteArray(byteMap,0, byteMap.length);
    }

    protected Torrents(Parcel in) {
        title = in.readString();
        description = in.readString();
        torrentWebLink = in.readString();
        torrentDownloadLink = in.readString();
        posterImgLink = in.readString();
        genre = in.readString();
        size = in.readString();
        runtime = in.readString();
        pubDate = in.readString();
        imdbRating = in.readString();
        rottenRating = in.readByte() == 0x00 ? null : in.readDouble();
        //byte[] _byte = new byte[in.readInt()];
        image = in.createByteArray();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(torrentWebLink);
        dest.writeString(torrentDownloadLink);
        dest.writeString(posterImgLink);
        dest.writeString(genre);
        dest.writeString(size);
        dest.writeString(runtime);
        dest.writeString(pubDate);
        dest.writeString(imdbRating);
        if (rottenRating == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(rottenRating);
        }
        //dest.writeInt(image.length);
        dest.writeByteArray(image);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Torrents> CREATOR = new Parcelable.Creator<Torrents>() {
        @Override
        public Torrents createFromParcel(Parcel in) {
            return new Torrents(in);
        }

        @Override
        public Torrents[] newArray(int size) {
            return new Torrents[size];
        }
    };
}
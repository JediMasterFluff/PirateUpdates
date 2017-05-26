package com.applications.fluffy.piratingupdates.Objects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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

    private String imageFile; //path to image file on phone

    private final static int charLimit = 300;

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
        this.imageFile = "";
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = cleanTitle(title);
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

    /**
     * @return A Bitmap image from the Torrent object imagefile path
     */
    public Bitmap readBitmap() throws IOException {

        if(!this.imageFile.isEmpty()) {
            File file = new File(this.imageFile);
            BitmapFactory.Options bfo = new BitmapFactory.Options();
            return BitmapFactory.decodeFile(file.getAbsolutePath(), bfo);
        } else {
            URL conn = new URL(this.posterImgLink);
            HttpURLConnection httpConn = (HttpURLConnection) conn.openConnection();
            httpConn.setDoInput(true);
            httpConn.connect();
            InputStream is = httpConn.getInputStream();
            return BitmapFactory.decodeStream(is);
        }

    }

    public void saveBitmap(String file, Bitmap map){
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            map.compress(Bitmap.CompressFormat.PNG, 100, out);// PNG is a lossless format, the compression factor (100) is ignored
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            this.imageFile = file;
        }
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
        imageFile = in.readString();
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
        dest.writeString(imageFile);
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

    private String cleanTitle(String title) {
        int index = 0;

        index = title.indexOf("(", 0);
        return title.substring(0, index);
    }

    public String shortDesc() {
        if (description.length() > charLimit) {
            return this.description.substring(0, charLimit) + "...";
        } else
            return this.description;
    }
}
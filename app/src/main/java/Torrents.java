/**
 * Created by fluffy on 03/02/17.
 */

public class Torrents {
    //Attributes
    private String name;
    private String description;
    private String torrentLink;
    private String posterImgLink;
    private String genre;
    private Double imdbRating;
    private Double rottenRating;
    private int seeders;
    private int peers;

    public Torrents(){
        this.name = "";
        this.description = "";
        this.torrentLink = "";
        this.posterImgLink = "";
        this.genre = "";
        this.imdbRating = 0.0;
        this.rottenRating = 0.0;
        this.seeders = 0;
        this.peers = 0;
    }

    @Override
    public String toString() {
        return "Torrents{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", torrentLink='" + torrentLink + '\'' +
                ", posterImgLink='" + posterImgLink + '\'' +
                ", genre='" + genre + '\'' +
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

    public String getTorrentLink() {
        return torrentLink;
    }

    public void setTorrentLink(String torrentLink) {
        this.torrentLink = torrentLink;
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

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(Double imdbRating) {
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

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

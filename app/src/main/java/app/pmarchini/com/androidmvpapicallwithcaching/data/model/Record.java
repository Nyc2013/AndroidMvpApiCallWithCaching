package app.pmarchini.com.androidmvpapicallwithcaching.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Pierre on 25/11/2017.
 */



public class Record {

    @Expose
    @SerializedName("albumId")
    private int albumId;

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("title")
    private String title;

    @Expose
    @SerializedName("url")
    private String url;

    @Expose
    @SerializedName("thumbnailUrl")
    private String thumbnailUrl;

    public int getAlbumId() {
            return albumId;
        }

    public void setAlbumId(int albumId) {
            this.albumId = albumId;
        }

    public int getId() {
            return id;
        }

    public void setId(int id) {
            this.id = id;
        }

    public String getTitle() {
            return title;
        }

    public void setTitle(String title) {
            this.title = title;
        }

    public String getUrl() {
            return url;
        }

    public void setUrl(String url) {
            this.url = url;
        }

    public String getThumbnailUrl() {
            return thumbnailUrl;
        }

    public void setThumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
        }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Record)) return false;

        Record record = (Record) o;

        if (!title.equals(record.title)) return false;
        if (!url.equals(record.url)) return false;
        return thumbnailUrl.equals(record.thumbnailUrl);

    }

    @Override
    public int hashCode() {

        int result = title.hashCode();
        result = 31 * result + url.hashCode();
        result = 31 * result + thumbnailUrl.hashCode();
        return result;
    }
}


package com.example.movieapp.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class tvDetails {

    private tvshow tv ;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("runtime")
    @Expose
    private Integer runtime;

    @SerializedName("imagePath")
    @Expose
    private String imagePath;
    @SerializedName("genres")
    @Expose
    private List<String> genres = null;
    @SerializedName("pictures")
    @Expose
    private List<String> pictures = null;
    @SerializedName("episodes")
    @Expose
    private List<Episode> episodes = null;

    public tvshow getTv() {
        return tv;
    }

    public void setTv(tvshow tv) {
        this.tv = tv;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }
}

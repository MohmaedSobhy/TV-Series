
package com.example.movieapp.Data;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Episode {

    @SerializedName("season")
    @Expose
    private String season;
    @SerializedName("episode")
    @Expose
    private Integer episode;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("air_date")
    @Expose
    private String airDate;

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Integer getEpisode() {
        return episode;
    }

    public void setEpisode(Integer episode) {
        this.episode = episode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

}

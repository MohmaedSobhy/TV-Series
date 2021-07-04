
package com.example.movieapp.Data;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;



public class DataClientServer {

    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("pages")
    @Expose
    private Integer pages;
    @SerializedName("tv_shows")
    @Expose
    private List<tvshow> tvShows = null;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<tvshow> getTvShows() {
        return tvShows;
    }

    public void setTvShows(List<tvshow> tvShows) {
        this.tvShows = tvShows;
    }
}

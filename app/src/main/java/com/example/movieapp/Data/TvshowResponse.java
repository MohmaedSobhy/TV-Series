package com.example.movieapp.Data;

import com.google.gson.annotations.SerializedName;

public class TvshowResponse {

    @SerializedName("tvShow")
    private tvDetails tv;

    public tvDetails getTv() {
        return tv;
    }
}

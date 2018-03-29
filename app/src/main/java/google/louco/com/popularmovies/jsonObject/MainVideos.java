package google.louco.com.popularmovies.jsonObject;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainVideos {
    @SerializedName("results")
    private List<Videos> videos;

    public List<Videos> getVideos() {
        return videos;
    }
}

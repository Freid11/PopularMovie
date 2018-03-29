package google.louco.com.popularmovies.jsonObject;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainReviews {
    @SerializedName("results")
    private List<Reviews> reviews;

    public List<Reviews> getReviews() {
        return reviews;
    }
}

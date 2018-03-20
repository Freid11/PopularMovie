package google.louco.com.popularmovies.jsonObject;

import com.google.gson.annotations.SerializedName;

import google.louco.com.popularmovies.mvp.model.JsonConverter;

public class Movie {

    @SerializedName("original_title")
    private final String Title;

    @SerializedName("poster_path")
    private final String Image_Url;

    @SerializedName("overview")
    private final String Overview;

    @SerializedName("vote_average")
    private final float VoteAverage;

    @SerializedName("release_date")
    private final String ReleaseDate;

    public Movie(String title, String image_Url, String overview, float voteAverage, String releaseDate) {
        Title = title;
        Image_Url = image_Url;
        Overview = overview;
        VoteAverage = voteAverage;
        ReleaseDate = releaseDate;
    }

    public String getTitle() {
        return Title;
    }

    public String getImage_Url() {
        return Image_Url;
    }

    public String getOverview() {
        return Overview;
    }

    public float getVoteAverage() {
        return VoteAverage;
    }

    public String getReleaseDate() {
        return ReleaseDate;
    }

    public String toJson(){
        return JsonConverter.getGson().toJson(this);
    }

    public static Movie fromJson(String json){
        return JsonConverter.getGson().fromJson(json, Movie.class);
    }
}

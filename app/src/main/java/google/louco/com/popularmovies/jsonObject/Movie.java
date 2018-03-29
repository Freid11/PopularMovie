package google.louco.com.popularmovies.jsonObject;

import com.google.gson.annotations.SerializedName;

import google.louco.com.popularmovies.mvp.model.JsonConverter;

/**
 * Обьект Фильм для сериализации данных из API
 */
public class Movie {

    @SerializedName("id")
    private final int ID;

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

    private boolean Favorite = false;

    public Movie(int id, String title, String image_Url, String overview, float voteAverage, String releaseDate) {
        ID = id;
        Title = title;
        Image_Url = image_Url;
        Overview = overview;
        VoteAverage = voteAverage;
        ReleaseDate = releaseDate;
    }

    public int getID() {
        return ID;
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

    public boolean isFavorite() {
        return Favorite;
    }

    public boolean swichFavorite() {
        Favorite = !Favorite;
        return Favorite;
    }

    /**
     * @return Возвращает json из Объекта
     */
    public String toJson(){
        return JsonConverter.getGson().toJson(this);
    }

    /**
     * Преобразует json строку в объект {@link Movie}
     * @param json - строка
     * @return - возращает {@link Movie}
     */
    public static Movie fromJson(String json){
        return JsonConverter.getGson().fromJson(json, Movie.class);
    }
}

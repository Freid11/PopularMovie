package google.louco.com.popularmovies.jsonObject;

import com.google.gson.annotations.SerializedName;

import google.louco.com.popularmovies.mvp.model.JsonConverter;

/**
 * Обьект Фильм для сериализации данных из API
 */
public class Movie {

    public static final String ID_MOVIE = "id";
    public static final String TITLE = "original_title";
    public static final String IMAGE_URL = "poster_path";
    public static final String OVERVIEW = "overview";
    public static final String VOTE_AVERAGE = "vote_average";
    public static final String RELEASE_DATE = "release_date";

    @SerializedName(ID_MOVIE)
    private final int ID;

    @SerializedName(TITLE)
    private final String Title;

    @SerializedName(IMAGE_URL)
    private final String Image_Url;

    @SerializedName(OVERVIEW)
    private final String Overview;

    @SerializedName(VOTE_AVERAGE)
    private final String VoteAverage;

    @SerializedName(RELEASE_DATE)
    private final String ReleaseDate;

    private boolean Favorite = false;

    public Movie(int id, String title, String image_Url, String overview, String voteAverage, String releaseDate) {
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

    public String getVoteAverage() {
        return VoteAverage;
    }

    public String getReleaseDate() {
        return ReleaseDate;
    }

    public boolean isFavorite() {
        return Favorite;
    }

    public void switchFavorite() {
        Favorite = !Favorite;
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

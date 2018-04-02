package google.louco.com.popularmovies.jsonObject;

import android.content.ContentValues;
import android.database.Cursor;

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
    private static final String SEPARATOR = "\n";
    private static final String SEPARATOR_TITLE_COUNT = ":  ";

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
    public String toJson() {
        return JsonConverter.getGson().toJson(this);
    }

    /**
     * Преобразует json строку в объект {@link Movie}
     *
     * @param json - строка
     * @return - возращает {@link Movie}
     */
    public static Movie fromJson(String json) {
        return JsonConverter.getGson().fromJson(json, Movie.class);
    }

    public ContentValues getContentValue() {
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID_MOVIE, ID);
        contentValues.put(TITLE, Title);
        contentValues.put(IMAGE_URL, Image_Url);
        contentValues.put(OVERVIEW, Overview);
        contentValues.put(VOTE_AVERAGE, VoteAverage);
        contentValues.put(RELEASE_DATE, ReleaseDate);

        return contentValues;
    }

    public static Movie fromCursor(Cursor cursor){
        return new Movie(cursor.getInt(cursor.getColumnIndex(ID_MOVIE)),
                cursor.getString(cursor.getColumnIndex(TITLE)),
                cursor.getString(cursor.getColumnIndex(IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(OVERVIEW)),
                cursor.getString(cursor.getColumnIndex(VOTE_AVERAGE)),
                cursor.getString(cursor.getColumnIndex(RELEASE_DATE)));
    }

    @Override
    public String toString() {
        String stringBuffer = ID_MOVIE + SEPARATOR_TITLE_COUNT + String.valueOf(ID) + SEPARATOR +
                TITLE + SEPARATOR_TITLE_COUNT +  Title + SEPARATOR +
                IMAGE_URL + SEPARATOR_TITLE_COUNT +  Image_Url + SEPARATOR +
                OVERVIEW + SEPARATOR_TITLE_COUNT +  Overview + SEPARATOR +
                VOTE_AVERAGE + SEPARATOR_TITLE_COUNT +  VoteAverage + SEPARATOR +
                RELEASE_DATE + SEPARATOR_TITLE_COUNT +  ReleaseDate;

        return stringBuffer;
    }
}

package google.louco.com.popularmovies.jsonObject;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Основной класс выгрузки данных из API
 */
public class MainJson {

    @SerializedName("page")
    private final int page;

    @SerializedName("total_pages")
    private int total_pages;

    @SerializedName("results")
    private List<Movie> films;

    public MainJson(int page) {
        this.page = page;
    }

    public List<Movie> getFilms() {
        return films;
    }

    public int getPage() {
        return page;
    }
}

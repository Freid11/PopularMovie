package google.louco.com.popularmovies.mvp.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import google.louco.com.popularmovies.jsonObject.Movie;

public interface ViewMain extends MvpView{
    /**
     * Отображет прогресс загрузки данных
     * @param show - булева переменная отображать или нет прогресс
     */
    void showProgress(Boolean show);
    /**
     * Добовляет фильмы в ленту
     * @param movies - список фильмов
     */
    void addMovieList(List<Movie> movies);
    /**
     * Перегружает список фильмов
     * @param movies - список фильмов
     */
    void rsMovieList(List<Movie> movies);
    /**
     * Отображает детальную информацию Фильма {@link Movie}
     * @param movie - Передаем фильм для детального просмотра
     */
    void showDetailMovie(Movie movie);
}

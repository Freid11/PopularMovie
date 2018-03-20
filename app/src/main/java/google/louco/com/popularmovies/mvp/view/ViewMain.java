package google.louco.com.popularmovies.mvp.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import google.louco.com.popularmovies.jsonObject.Movie;

public interface ViewMain extends MvpView{
    void showProgress(Boolean show);
    void addMovieList(List<Movie> movies);
    void rsMovieList(List<Movie> movies);
    void showDetailMovie(Movie movie);
}

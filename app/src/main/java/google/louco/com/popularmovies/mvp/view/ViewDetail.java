package google.louco.com.popularmovies.mvp.view;

import com.arellomobile.mvp.MvpView;

import google.louco.com.popularmovies.jsonObject.Movie;

public interface ViewDetail extends MvpView {
    void showDetail(Movie movie);
    void getIntentMovie();
}

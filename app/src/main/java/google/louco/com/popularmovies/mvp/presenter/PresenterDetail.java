package google.louco.com.popularmovies.mvp.presenter;

import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import google.louco.com.popularmovies.jsonObject.Movie;
import google.louco.com.popularmovies.mvp.view.ViewDetail;

@InjectViewState
public class PresenterDetail extends MvpPresenter<ViewDetail>{

    public static final String KEY_INTENT_MOVIE = "movie_json";

    public PresenterDetail() {
        getViewState().getIntentMovie();
    }

    public void showDetail(Intent intent){
        Bundle bundle = intent.getExtras();
        if (bundle!= null) {
            String json = bundle.getString(KEY_INTENT_MOVIE);
            Movie movie = Movie.fromJson(json);
            getViewState().showDetail(movie);
        }
    }
}

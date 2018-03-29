package google.louco.com.popularmovies.mvp.presenter;

import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import google.louco.com.popularmovies.BuildConfig;
import google.louco.com.popularmovies.jsonObject.MainReviews;
import google.louco.com.popularmovies.jsonObject.MainVideos;
import google.louco.com.popularmovies.jsonObject.Movie;
import google.louco.com.popularmovies.jsonObject.Reviews;
import google.louco.com.popularmovies.jsonObject.Videos;
import google.louco.com.popularmovies.mvp.model.RequestServer;
import google.louco.com.popularmovies.mvp.view.ViewDetail;
import io.reactivex.observers.DisposableObserver;

@InjectViewState
public class PresenterDetail extends MvpPresenter<ViewDetail>{

    public static final String KEY_INTENT_MOVIE = "movie_json";
    private static final String URL_YOUTUBE = BuildConfig.URL_YOUTUBE;

    public PresenterDetail() {
        getViewState().getIntentMovie();
    }

    public void showDetail(Intent intent){
        Bundle bundle = intent.getExtras();
        if (bundle!= null) {
            String json = bundle.getString(KEY_INTENT_MOVIE);
            Movie movie = Movie.fromJson(json);
            getViewState().showDetail(movie);
            RequestServer.getVideos(movie.getID(), new RequestVideos());
            RequestServer.getReview(movie.getID(), new RequestReview());
        }
    }

    public void setFavorite(Movie movie){
        movie.swichFavorite();
        getViewState().showDetail(movie);
    }

    public void  clickVideos(Videos videos){
        getViewState().showVideo(URL_YOUTUBE+videos.getKey());
    }

    public void clickReview(Reviews reviews){
        getViewState().showReview(reviews);
    }

    class RequestVideos extends DisposableObserver<MainVideos>{

        @Override
        public void onNext(MainVideos mainVideos) {
            getViewState().fillListVideos(mainVideos.getVideos());
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }

    class RequestReview extends DisposableObserver<MainReviews>{

        @Override
        public void onNext(MainReviews mainReviews) {
            getViewState().fillListReview(mainReviews.getReviews());
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}

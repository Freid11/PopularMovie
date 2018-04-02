package google.louco.com.popularmovies.mvp.presenter;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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
public class PresenterDetail extends MvpPresenter<ViewDetail> {

    public static final String KEY_INTENT_MOVIE = "movie_json";
    private static final String URL_YOUTUBE = BuildConfig.URL_YOUTUBE;

    private static final String CONTENT_AUTHORITY = BuildConfig.URI_CONTENT;
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public PresenterDetail() {
        getViewState().getIntentMovie();
    }

    public void showDetail(Intent intent, ContentResolver contentResolver) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String json = bundle.getString(KEY_INTENT_MOVIE);
            Movie movie = Movie.fromJson(json);
            ShowDetailMovie(movie, contentResolver);
            RequestServer.getVideos(movie.getID(), new RequestVideos());
            RequestServer.getReview(movie.getID(), new RequestReview());
        }
    }

    private void ShowDetailMovie(Movie movie, ContentResolver contentResolver) {
        Cursor cursor = contentResolver.query(BASE_CONTENT_URI,
                new String[]{Movie.ID_MOVIE, Movie.TITLE},
                Movie.ID_MOVIE + " =?",
                new String[]{String.valueOf(movie.getID())},
                null);

        Log.d("Louco", "Cursor count:  " + cursor.getCount());
        if (cursor.getCount() > 0) {
            movie.switchFavorite();
        }
        getViewState().showDetail(movie);
    }

    public void setFavorite(Movie movie, ContentResolver contentResolver) {
        movie.switchFavorite();
        if (movie.isFavorite()) {
            Uri uri = contentResolver.insert(BASE_CONTENT_URI, movie.getContentValue());
            if (uri == null) {
                Log.d("Louco", "error insert");
                //todo show message error
            } else {
                Log.d("Louco", "good insert");
                getViewState().showDetail(movie);
            }
        } else {
            int id = contentResolver.delete(BASE_CONTENT_URI, Movie.ID_MOVIE + " =?", new String[]{String.valueOf(movie.getID())});
            getViewState().showDetail(movie);
            Log.d("Louco", "delete " + id);
        }

    }

    public void clickVideos(Videos videos) {
        getViewState().showVideo(URL_YOUTUBE + videos.getKey());
    }

    public void clickReview(Reviews reviews) {
        getViewState().showReview(reviews);
    }

    class RequestVideos extends DisposableObserver<MainVideos> {

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

    class RequestReview extends DisposableObserver<MainReviews> {

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

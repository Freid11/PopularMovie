package google.louco.com.popularmovies.mvp.presenter;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

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
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class PresenterDetail extends MvpPresenter<ViewDetail> {

    public static final String KEY_INTENT_MOVIE = "movie_json";
    private static final String URL_YOUTUBE = BuildConfig.URL_YOUTUBE;

    private static final String CONTENT_AUTHORITY = BuildConfig.URI_CONTENT;
    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

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
        Observable.just(contentResolver)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SearchMovie(movie));
    }

    public void setFavorite(Movie movie, ContentResolver contentResolver) {
        Observable<ContentResolver> observable = Observable.just(contentResolver)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        movie.switchFavorite();
        if (movie.isFavorite()) {
            observable.subscribe(new InsertMovie(movie));
        } else {
            observable.subscribe(new DeleteMovie(movie));
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

    class SearchMovie extends DisposableObserver<ContentResolver> {
        private Movie movie;

        SearchMovie(Movie movie) {
            this.movie = movie;
        }

        @Override
        public void onNext(ContentResolver contentResolver) {

            @SuppressLint("Recycle") Cursor cursor = contentResolver.query(BASE_CONTENT_URI,
                    new String[]{Movie.ID_MOVIE, Movie.TITLE},
                    Movie.ID_MOVIE + " =?",
                    new String[]{String.valueOf(movie.getID())},
                    null);

            assert cursor != null;
            if (cursor.getCount() > 0) {
                movie.switchFavorite();
            }

            getViewState().showDetail(movie);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }

    class InsertMovie extends DisposableObserver<ContentResolver>{

        private Movie movie;

        InsertMovie(Movie movie) {
            this.movie = movie;
        }

        @Override
        public void onNext(ContentResolver contentResolver) {
            Uri uri = contentResolver.insert(BASE_CONTENT_URI, movie.getContentValue());
            if (uri == null) {
                Log.d("Louco", "error insert");
                //todo show message error
            } else {
                Log.d("Louco", "good insert");
                getViewState().showDetail(movie);
            }
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }

    class DeleteMovie extends DisposableObserver<ContentResolver>{

        private Movie movie;

        DeleteMovie(Movie movie) {
            this.movie = movie;
        }

        @Override
        public void onNext(ContentResolver contentResolver) {
            int id = contentResolver.delete(BASE_CONTENT_URI, Movie.ID_MOVIE + " =?", new String[]{String.valueOf(movie.getID())});
            getViewState().showDetail(movie);
            Log.d("Louco", "delete " + id);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}

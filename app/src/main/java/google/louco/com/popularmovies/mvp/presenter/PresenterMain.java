package google.louco.com.popularmovies.mvp.presenter;

import android.content.ContentResolver;
import android.database.Cursor;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import google.louco.com.popularmovies.jsonObject.MainJson;
import google.louco.com.popularmovies.jsonObject.Movie;
import google.louco.com.popularmovies.mvp.model.RequestServer;
import google.louco.com.popularmovies.mvp.view.ViewMain;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class PresenterMain extends MvpPresenter<ViewMain> {

    enum TypeFilterMovie {POPULAR, TOP, FAVORITE}

    enum StatusList {RESET, ADD}

    private final int FIRST_PAGE = 1;
    private static final TypeFilterMovie FIRST_FILTER = TypeFilterMovie.POPULAR;

    private int Page = FIRST_PAGE;
    private static TypeFilterMovie filterMovie = FIRST_FILTER;

    public final static int POPULAR_BAR = 0;
    public final static int TOP_BAR = 1;
    public final static int FAVORITE_BAR = 2;

    public PresenterMain() {
        FirstLoad();
    }

    public void NextPage() {
        Page++;
        RequestFilmList(StatusList.ADD);
    }

    public void ClickMovie(Movie movie) {
        getViewState().showDetailMovie(movie);
    }

    public void ClickMenuPopular() {
        Log.d("Louco", "ClickMenuPopular");
        filterMovie = TypeFilterMovie.POPULAR;
        Page = FIRST_PAGE;
        RequestFilmList(StatusList.RESET);
    }

    public void ClickMenuTop() {
        Log.d("Louco", "ClickMenuTop");
        filterMovie = TypeFilterMovie.TOP;
        Page = FIRST_PAGE;
        RequestFilmList(StatusList.RESET);
    }

    public void ClickMenuFavorite(ContentResolver contentResolver) {
        filterMovie = TypeFilterMovie.FAVORITE;
        Log.d("Louco", "ClickMenuFavorite");
        Observable.just(contentResolver)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new getFavoriteMovie());
    }

    private void FirstLoad() {
        switch (filterMovie) {
            case POPULAR:
                getViewState().setPositionBar(POPULAR_BAR);
                break;
            case TOP:
                getViewState().setPositionBar(TOP_BAR);
                break;
            case FAVORITE:
                getViewState().setPositionBar(FAVORITE_BAR);
                break;
        }
    }

    private void RequestFilmList(StatusList statusList) {
        switch (filterMovie) {
            case POPULAR:
                RequestServer.getPopularMovie(Page, new ResponseServer(statusList));
                break;
            case TOP:
                RequestServer.getTopMovie(Page, new ResponseServer(statusList));
                break;
        }
    }

    private class ResponseServer extends DisposableObserver<MainJson> {

        private final StatusList statusList;

        ResponseServer(StatusList statusList) {
            this.statusList = statusList;
        }

        @Override
        protected void onStart() {
            super.onStart();
            getViewState().showProgress(true);
        }

        @Override
        public void onNext(MainJson mainJson) {
            switch (statusList) {
                case ADD:
                    getViewState().addMovieList(mainJson.getFilms());
                    break;
                case RESET:
                    getViewState().rsMovieList(mainJson.getFilms());
            }

        }

        @Override
        public void onError(Throwable e) {
            hideProgress();
        }

        @Override
        public void onComplete() {
            hideProgress();
        }

        private void hideProgress() {
            getViewState().showProgress(false);
        }
    }

    private class getFavoriteMovie extends DisposableObserver<ContentResolver>{

        @Override
        public void onNext(ContentResolver contentResolver) {
            List<Movie> movies = new ArrayList<>();
            Cursor cursor = contentResolver.query(PresenterDetail.BASE_CONTENT_URI,
                    null,
                    null,
                    null,
                    null);

            assert cursor != null;
            while (cursor.moveToNext()) {
                movies.add(Movie.fromCursor(cursor));
            }
            getViewState().rsMovieList(movies);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}


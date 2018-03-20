package google.louco.com.popularmovies.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import google.louco.com.popularmovies.jsonObject.MainJson;
import google.louco.com.popularmovies.jsonObject.Movie;
import google.louco.com.popularmovies.mvp.model.RequestServer;
import google.louco.com.popularmovies.mvp.view.ViewMain;
import io.reactivex.observers.DisposableObserver;

@InjectViewState
public class PresenterMain extends MvpPresenter<ViewMain> {

    enum TypeFilterMovie {POPULAR, TOP}

    enum StatusList {RESET, ADD}

    private final int FIRST_PAGE = 1;
    private final TypeFilterMovie FIRST_FILTER = TypeFilterMovie.POPULAR;

    private int Page = FIRST_PAGE;
    private TypeFilterMovie filterMovie = FIRST_FILTER;

    public PresenterMain() {
        RequestFilmList(StatusList.ADD);
    }

    public void NextPage() {
        Page++;
        RequestFilmList(StatusList.ADD);
    }

    public void ClickMovie(Movie movie) {
        getViewState().showDetailMovie(movie);
    }

    public void ClickMenuPopular() {
        filterMovie = TypeFilterMovie.POPULAR;
        Page = FIRST_PAGE;
        RequestFilmList(StatusList.RESET);
    }

    public void ClickMenuTop() {
        filterMovie = TypeFilterMovie.TOP;
        Page = FIRST_PAGE;
        RequestFilmList(StatusList.RESET);
    }

    private void RequestFilmList(StatusList statusList) {
        switch (filterMovie) {
            case TOP:
                RequestServer.getTopMovie(Page, new ResponseServer(statusList));
                break;
            case POPULAR:
                RequestServer.getPopularMovie(Page, new ResponseServer(statusList));
                break;
        }
    }

    private class ResponseServer extends DisposableObserver<MainJson> {

        private final StatusList statusList;

        public ResponseServer(StatusList statusList) {
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

}


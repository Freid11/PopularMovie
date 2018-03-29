package google.louco.com.popularmovies.mvp.model;

import google.louco.com.popularmovies.BuildConfig;
import google.louco.com.popularmovies.jsonObject.MainJson;
import google.louco.com.popularmovies.jsonObject.MainReviews;
import google.louco.com.popularmovies.jsonObject.MainVideos;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RequestServer {

    private static final String KEY_API = BuildConfig.API_KEY;

    public static void getPopularMovie(int Page, DisposableObserver<MainJson> disposableObserver){
        RetrofitConnect.getInstance()
                .create(ApiConnect.class)
                .getPopular(KEY_API, Page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver);
    }

    public static void getTopMovie(int Page, DisposableObserver<MainJson> disposableObserver){
        RetrofitConnect.getInstance()
                .create(ApiConnect.class)
                .getTOP(KEY_API, Page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver);
    }

    public static void getVideos(int id, DisposableObserver<MainVideos> disposableObserver){
        RetrofitConnect.getInstance()
                .create(ApiConnect.class)
                .getMovies(id, KEY_API)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver);
    }

    public static void getReview(int id, DisposableObserver<MainReviews> disposableObserver){
        RetrofitConnect.getInstance()
                .create(ApiConnect.class)
                .getReviews(id, KEY_API)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver);
    }

}

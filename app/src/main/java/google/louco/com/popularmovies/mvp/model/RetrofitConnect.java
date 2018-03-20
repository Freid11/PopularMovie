package google.louco.com.popularmovies.mvp.model;

import google.louco.com.popularmovies.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitConnect {

    private static final String URL_MOVIE = BuildConfig.URL_FILM;
    private static  Retrofit retrofit = null;

    static Retrofit getInstance() {
        if(retrofit == null) retrofit = new Retrofit.Builder()
                .baseUrl(URL_MOVIE)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    private RetrofitConnect() {

    }
}

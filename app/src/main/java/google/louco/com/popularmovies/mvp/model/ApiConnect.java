package google.louco.com.popularmovies.mvp.model;


import google.louco.com.popularmovies.jsonObject.MainJson;
import google.louco.com.popularmovies.jsonObject.MainReviews;
import google.louco.com.popularmovies.jsonObject.MainVideos;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface ApiConnect {

    @GET("3/movie/popular")
    Observable<MainJson> getPopular(@Query("api_key") String Key, @Query("page") int Page);

    @GET("3/movie/top_rated")
    Observable<MainJson> getTOP(@Query("api_key") String Key, @Query("page") int Page);

    @GET("3/movie/{id}/videos")
    Observable<MainVideos> getMovies(@Path("id") int id, @Query("api_key") String key);

    @GET("3/movie/{id}/reviews")
    Observable<MainReviews> getReviews(@Path("id") int id, @Query("api_key") String key);
}

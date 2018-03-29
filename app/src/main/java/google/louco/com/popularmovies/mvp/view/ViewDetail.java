package google.louco.com.popularmovies.mvp.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import google.louco.com.popularmovies.jsonObject.Movie;
import google.louco.com.popularmovies.jsonObject.Reviews;
import google.louco.com.popularmovies.jsonObject.Videos;

public interface ViewDetail extends MvpView {

    /**
     * Выводит подробную информацию о фильме на экран
     * @param movie - фильм
     */
    void showDetail(Movie movie);

    /**
     * передает Intent -> presenter для выгрузки {@link Movie} из {@link android.os.Bundle}
     */
    void getIntentMovie();

    /**
     * Отображение трэйлеров фильма {@link Videos}
     * @param videos - список трэйлеров
     */
    void fillListVideos(List<Videos> videos);

    /**
     * Отображение коментариев фильма {@link Reviews}
     * @param reviews
     */
    void fillListReview(List<Reviews> reviews);

    /**
     * Просмтреть видео
     * @param video
     */
    void showVideo(String url);

    /**
     * Просмотреть коментарий
     * @param reviews
     */
    void showReview(Reviews reviews);
}

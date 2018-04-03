package google.louco.com.popularmovies.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.eightbitlab.bottomnavigationbar.BottomBarItem;
import com.eightbitlab.bottomnavigationbar.BottomNavigationBar;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import google.louco.com.popularmovies.R;
import google.louco.com.popularmovies.adapters.RVAdapterMovie;
import google.louco.com.popularmovies.jsonObject.Movie;
import google.louco.com.popularmovies.mvp.presenter.PresenterDetail;
import google.louco.com.popularmovies.mvp.presenter.PresenterMain;
import google.louco.com.popularmovies.mvp.view.ViewMain;

public class MainActivity extends MvpAppCompatActivity implements ViewMain {

    @InjectPresenter
    public PresenterMain presenterMain;

    @BindView(R.id.srl_refresh_movie)
    SwipyRefreshLayout swipyRefreshLayout;

    @BindView(R.id.rv_movie_list)
    RecyclerView recyclerView;

    @BindView(R.id.bottom_bar)
    BottomNavigationBar navigationBar;


    private RVAdapterMovie adapterMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        swipyRefreshLayout.setOnRefreshListener(direction -> presenterMain.NextPage());

        adapterMovie = new RVAdapterMovie(new OnClickMov());
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapterMovie);

        BottomBarItem itPopular = new BottomBarItem(R.drawable.ic_toc, R.string.popular);
        navigationBar.addTab(itPopular);
        BottomBarItem itTOP = new BottomBarItem(R.drawable.ic_poll, R.string.top);
        navigationBar.addTab(itTOP);
        BottomBarItem itFavorite = new BottomBarItem(R.drawable.ic_star, R.string.favorite);
        navigationBar.addTab(itFavorite);

        navigationBar.setOnSelectListener(this::ActionPositionBar);
    }

    @Override
    public void showProgress(Boolean show) {
        swipyRefreshLayout.setRefreshing(show);
    }

    @Override
    public void addMovieList(List<Movie> movies) {
        adapterMovie.addMovie(movies);
    }

    @Override
    public void rsMovieList(List<Movie> movies) {
        adapterMovie.rsMovieList(movies);
    }

    @Override
    public void showDetailMovie(Movie movie) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(PresenterDetail.KEY_INTENT_MOVIE, movie.toJson());
        startActivity(intent);
    }

    @Override
    public void setPositionBar(int position) {
        navigationBar.selectTab(position, false);
        ActionPositionBar(position);
    }


    private void ActionPositionBar(int position) {
        switch (position) {
            case PresenterMain.POPULAR_BAR:
                presenterMain.ClickMenuPopular();
                break;
            case PresenterMain.TOP_BAR:
                presenterMain.ClickMenuTop();
                break;
            case PresenterMain.FAVORITE_BAR:
                presenterMain.ClickMenuFavorite(getContentResolver());
                break;
        }
    }

    private class OnClickMov implements RVAdapterMovie.OnClickMovie {

        @Override
        public void onClick(Movie movie) {
            presenterMain.ClickMovie(movie);
        }
    }
}

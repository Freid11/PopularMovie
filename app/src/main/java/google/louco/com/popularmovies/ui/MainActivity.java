package google.louco.com.popularmovies.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
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

public class MainActivity extends MvpAppCompatActivity implements ViewMain{

    @InjectPresenter
    public PresenterMain presenterMain;

    @BindView(R.id.srl_refresh_movie)
    SwipyRefreshLayout swipyRefreshLayout;

    @BindView(R.id.rv_movie_list)
    RecyclerView recyclerView;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater  = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.it_menu_popular:
                presenterMain.ClickMenuPopular();
                break;
            case R.id.it_menu_top:
                presenterMain.ClickMenuTop();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private class OnClickMov implements RVAdapterMovie.OnClickMovie{

        @Override
        public void onClick(Movie movie) {
            presenterMain.ClickMovie(movie);
        }
    }
}

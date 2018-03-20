package google.louco.com.popularmovies.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import google.louco.com.popularmovies.BuildConfig;
import google.louco.com.popularmovies.R;
import google.louco.com.popularmovies.jsonObject.Movie;
import google.louco.com.popularmovies.mvp.presenter.PresenterDetail;
import google.louco.com.popularmovies.mvp.view.ViewDetail;

public class DetailActivity extends MvpAppCompatActivity implements ViewDetail {

    @InjectPresenter
    PresenterDetail presenter;

    @BindView(R.id.iv_poster_details)
    ImageView poster;

    @BindView(R.id.tv_vote_average)
    TextView tvAverage;

    @BindView(R.id.tv_date)
    TextView tvDate;

    @BindView(R.id.tv_overview)
    TextView tvOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
    }

    @Override
    public void showDetail(Movie movie) {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!= null) actionBar.setTitle(movie.getTitle());

        String URL_IMAGE = BuildConfig.URL_IMAGE_FILM;
        Picasso.get().load(URL_IMAGE + movie.getImage_Url()).into(poster);

        String userStoreMax = "/10";
        tvAverage.append(movie.getVoteAverage() + userStoreMax);

        tvDate.setText(movie.getReleaseDate());
        tvOverview.setText(movie.getOverview());
    }

    @Override
    public void getIntentMovie() {
        presenter.showDetail(getIntent());
    }

}

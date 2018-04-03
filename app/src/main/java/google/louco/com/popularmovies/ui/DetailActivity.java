package google.louco.com.popularmovies.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import google.louco.com.popularmovies.BuildConfig;
import google.louco.com.popularmovies.R;
import google.louco.com.popularmovies.adapters.RVAdapterReview;
import google.louco.com.popularmovies.adapters.RVAdapterVideo;
import google.louco.com.popularmovies.jsonObject.Movie;
import google.louco.com.popularmovies.jsonObject.Reviews;
import google.louco.com.popularmovies.jsonObject.Videos;
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

    @BindView(R.id.ib_favorite)
    ImageButton ibFavorite;

    @BindView(R.id.rv_video)
    RecyclerView rvVideo;

    @BindView(R.id.rv_review)
    RecyclerView rvReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

    }

    @Override
    public void showDetail(Movie movie) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setTitle(movie.getTitle());

        Log.d("Louco", movie.toString());

        String URL_IMAGE = BuildConfig.URL_IMAGE_FILM;

        Picasso.get()
                .load(URL_IMAGE + movie.getImage_Url())
                .placeholder(R.drawable.ic_autorenew)
                .error(R.drawable.ic_error)
                .into(poster);

        String userStoreMax = movie.getVoteAverage() + "/10";
        tvAverage.setText(userStoreMax);

        tvDate.setText(movie.getReleaseDate());
        tvOverview.setText(movie.getOverview());
        switchFavorite(movie.isFavorite());

        ibFavorite.setOnClickListener(view -> presenter.setFavorite(movie, getContentResolver()));
    }

    private void switchFavorite(boolean favorite) {
        if (favorite) ibFavorite.setImageDrawable(getResources().getDrawable(R.drawable.ic_star));
        else ibFavorite.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_border));
    }

    @Override
    public void getIntentMovie() {
        presenter.showDetail(getIntent(), getContentResolver());
    }

    @Override
    public void fillListVideos(List<Videos> videos) {
        RVAdapterVideo adapterVideo = new RVAdapterVideo(videos, new onClickVideos());

        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        Drawable SeparatorLine = ContextCompat.getDrawable(this, R.drawable.line_rv);

        assert SeparatorLine != null;
        decoration.setDrawable(SeparatorLine);

        rvVideo.addItemDecoration(decoration);
        rvVideo.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        rvVideo.setAdapter(adapterVideo);
    }

    @Override
    public void fillListReview(List<Reviews> reviews) {
        RVAdapterReview adapterReview = new RVAdapterReview(reviews, new onClickReview());

        rvReview.setLayoutManager(new LinearLayoutManager(getBaseContext(),LinearLayoutManager.HORIZONTAL,false));
        rvReview.setAdapter(adapterReview);
    }

    @Override
    public void showVideo(String url) {
        Intent playVideo = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        if (playVideo.resolveActivity(getPackageManager()) != null) {
            startActivity(playVideo);
        }
    }

    @Override
    public void showReview(Reviews reviews) {
        Log.d("Louco", "review "+reviews.getId());
    }

    class onClickVideos implements RVAdapterVideo.OnClickVideo{
        @Override
        public void OnClick(Videos videos) {
            presenter.clickVideos(videos);
        }
    }

    class onClickReview implements RVAdapterReview.OnClickReview{
        @Override
        public void onClick(Reviews reviews) {
            presenter.clickReview(reviews);
        }
    }
}

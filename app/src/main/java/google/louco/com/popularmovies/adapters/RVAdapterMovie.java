package google.louco.com.popularmovies.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import google.louco.com.popularmovies.BuildConfig;
import google.louco.com.popularmovies.R;
import google.louco.com.popularmovies.jsonObject.Movie;

public class RVAdapterMovie extends RecyclerView.Adapter<RVAdapterMovie.ViewHolder> {

    private List<Movie> movies = new ArrayList<>();
    private final OnClickMovie clickMovie;

    public RVAdapterMovie(OnClickMovie clickMovie) {
        this.clickMovie = clickMovie;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.adapter_rv_movies, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void addMovie(List<Movie> movies) {
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }

    public void rsMovieList(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_poster)
        ImageView poster;

        private Movie lastMovie;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(v -> clickMovie.onClick(lastMovie));
        }

        private void Bind(Movie movie) {
            this.lastMovie = movie;
            String URL_IMAGE = BuildConfig.URL_IMAGE_FILM;
            String url_poster = URL_IMAGE + movie.getImage_Url();
            Picasso.get().load(url_poster).into(poster);
        }
    }

    public interface OnClickMovie {
        void onClick(Movie movie);
    }
}

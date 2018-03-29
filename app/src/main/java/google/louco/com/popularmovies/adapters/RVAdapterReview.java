package google.louco.com.popularmovies.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import google.louco.com.popularmovies.R;
import google.louco.com.popularmovies.jsonObject.Reviews;

public class RVAdapterReview extends RecyclerView.Adapter<RVAdapterReview.ViewHolder>{

    private List<Reviews> reviewsList;
    private OnClickReview onClickReview;

    public RVAdapterReview(List<Reviews> reviewsList, OnClickReview onClickReview) {
        this.reviewsList = reviewsList;
        this.onClickReview = onClickReview;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.adapter_rv_review, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Bind(reviewsList.get(position));
    }

    @Override
    public int getItemCount() {
        return reviewsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_container_review)
        TextView tvConteiner;

        @BindView(R.id.tv_author_review)
        TextView tvAutor;

        private Reviews lastReview;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(view -> onClickReview.onClick(lastReview));
        }

        private void Bind(Reviews reviews){
            lastReview = reviews;
            tvConteiner.setText(reviews.getContent());
            tvAutor.setText(reviews.getAuthor());
        }

    }

    public interface OnClickReview{
        void onClick(Reviews reviews);
    }
}

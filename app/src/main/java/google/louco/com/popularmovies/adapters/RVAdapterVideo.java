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
import google.louco.com.popularmovies.jsonObject.Videos;

public class RVAdapterVideo extends RecyclerView.Adapter<RVAdapterVideo.ViewHolder> {

    private List<Videos> videosList;
    private OnClickVideo onClickVideo;

    public RVAdapterVideo(List<Videos> videosList, OnClickVideo onClickVideo) {
        this.videosList = videosList;
        this.onClickVideo = onClickVideo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.adapter_rv_video, parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Bind(videosList.get(position));
    }

    @Override
    public int getItemCount() {
        return videosList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private Videos lastVideos;

        @BindView(R.id.tv_name_video)
        TextView name_video;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(view -> {
                onClickVideo.OnClick(lastVideos);
            });
        }

        private void Bind(Videos videos){
            lastVideos = videos;
            name_video.setText(videos.getName());
        }
    }

    public interface OnClickVideo{
        void OnClick(Videos videos);
    }
}

package com.hadar.danny.daggerdemo3.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.hadar.danny.daggerdemo3.R;
import com.hadar.danny.daggerdemo3.api.models.Movie;

import javax.inject.Inject;
import javax.inject.Named;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MoviesAdapter extends ListAdapter<Movie, MoviesAdapter.MoviesVh> {
    private final LayoutInflater mInflater;
    private final int mResource;
    private final String mImageUrlPattern;
    private final RequestManager mRequestManager;

    @Inject
    MoviesAdapter(LayoutInflater inflater, int resource, RequestManager requestManager, @Named("image_url_format") String imageUrlPattern) {
        super(new MyDiffCallBack());

        mInflater = inflater;
        mResource = resource;
        mImageUrlPattern = imageUrlPattern;
        mRequestManager = requestManager;
    }

    @NonNull
    @Override
    public MoviesVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MoviesVh(mInflater.inflate(mResource, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesVh vh, int position) {
        Movie movie = getItem(position);

        mRequestManager.load(String.format(mImageUrlPattern, movie.getPosterUrl())).into(vh.image);
        vh.title.setText(movie.getTitle());
        vh.overView.setText(movie.getOverview());
        vh.rating.setText(String.valueOf(movie.getRating()));
        vh.releaseDate.setText(movie.getReleaseDate());
    }

    static class MoviesVh extends RecyclerView.ViewHolder {
        @BindView(R.id.civ_image)
        CircleImageView image;
        @BindView(R.id.tv_title)
        TextView title;
        @BindView(R.id.tv_overview)
        TextView overView;
        @BindView(R.id.tv_rating)
        TextView rating;
        @BindView(R.id.tv_release_date)
        TextView releaseDate;

        MoviesVh(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    private static class MyDiffCallBack extends DiffUtil.ItemCallback<Movie> {
        @Override
        public boolean areItemsTheSame(@NonNull Movie oldMovie, @NonNull Movie newItem) {
            return oldMovie.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldMovie, @NonNull Movie newItem) {
            return oldMovie.getTitle().equals(newItem.getTitle())
                    && oldMovie.getOverview().equals(newItem.getOverview())
                    && oldMovie.getRating() == oldMovie.getRating()
                    && oldMovie.getId() == newItem.getId();
        }
    }
}

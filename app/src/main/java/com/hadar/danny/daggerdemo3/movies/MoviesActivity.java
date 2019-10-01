package com.hadar.danny.daggerdemo3.movies;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hadar.danny.daggerdemo3.R;
import com.hadar.danny.daggerdemo3.api.models.ApiResponse;
import com.hadar.danny.daggerdemo3.api.models.Movie;
import com.hadar.danny.daggerdemo3.api.models.MoviesResult;
import com.hadar.danny.daggerdemo3.utils.SoftKeyboardUtils;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;

public class MoviesActivity extends DaggerAppCompatActivity {
    @BindView(R.id.rv_movies)
    RecyclerView mMoviesRv;
    @BindView(R.id.et_movie_name_query)
    EditText mMoviesQueryEt;
    @BindView(R.id.pb_loading_movies)
    ProgressBar mMoviesLoadingPb;
    @BindView(R.id.bt_search_movies)
    Button mFetchMoviesBt;

    @Inject
    MoviesAdapter mMoviesAdapter;
    @Inject
    MoviesViewModelFactory mMoviesViewModelFactory;

    private MoviesViewModel mMoviesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initRecyclerView();
        initViewModel();

    }

    private void initRecyclerView() {
        mMoviesRv.setAdapter(mMoviesAdapter);
        mMoviesRv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initViewModel() {
        mMoviesViewModel = ViewModelProviders.of(this, mMoviesViewModelFactory).get(MoviesViewModel.class);
        mMoviesViewModel.apiResponse().observe(this, this::processApiResponse);
    }

    private void processApiResponse(ApiResponse<MoviesResult> apiResponse) {
        switch (apiResponse.status) {
            case LOADING:
                adjustMoviesLoading(true);
                break;
            case SUCCESS:
                adjustMoviesLoading(false);
                List<Movie> movieList = apiResponse.data.getMovieList();
                int results = movieList.size();

                mMoviesAdapter.submitList(movieList);
                Toast.makeText(this, getString(R.string.results_format, results), Toast.LENGTH_SHORT).show();
                break;
            case ERROR:
                adjustMoviesLoading(false);
                Toast.makeText(this, apiResponse.error.getMessage(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void adjustMoviesLoading(boolean loading) {
        if (loading) {
            mFetchMoviesBt.setClickable(false);
            mMoviesLoadingPb.setVisibility(View.VISIBLE);
        } else {
            mFetchMoviesBt.setClickable(true);
            mMoviesLoadingPb.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.bt_search_movies)
    void onSearchMoviesButtonClicked() {
        String moviesQuery = mMoviesQueryEt.getText().toString();

        mMoviesViewModel.fetchMoviesClicked(moviesQuery);
        SoftKeyboardUtils.hideKeyBoard(this);
    }
}

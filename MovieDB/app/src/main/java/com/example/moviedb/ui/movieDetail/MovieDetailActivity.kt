package com.example.moviedb.ui.movieDetail

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.moviedb.BuildConfig
import com.example.moviedb.R
import com.example.moviedb.base.Constants
import com.example.moviedb.base.ViewModelFactory
import com.example.moviedb.databinding.ActivityMovieDetailBinding
import com.example.moviedb.model.Movie
import com.example.moviedb.ui.movieList.MovieListAdapter
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var mMovieDetailViewModel: MovieDetailViewModel
    private val mAdapter = MovieListAdapter(this)
    private var mGridLayoutManager: GridLayoutManager? = null
    private lateinit var mMovieDetailBinding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMovieDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)
        initialData()
        initialObservers()

    }

    private fun initialData() {
        mMovieDetailViewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(
            MovieDetailViewModel::class.java
        )
        getIntentExtra()
        mMovieDetailViewModel.getMovieDetail(mMovieDetailViewModel.mMovie?.id ?: 0)
        text_movie_overview.movementMethod = ScrollingMovementMethod()
        val columns = resources.getInteger(R.integer.movies_columns)
        mGridLayoutManager = GridLayoutManager(this, columns)
        btn_book_movie.setOnClickListener {
            navigateToCathay()
        }

    }

    private fun navigateToCathay() {
        val uri: Uri = Uri.parse(BuildConfig.CATHAY_URL)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    private fun initialObservers() {
        mMovieDetailViewModel.mDuration.observe(this, Observer {
            mMovieDetailBinding.textMovieDuration.text =
                mMovieDetailViewModel.getFormattedDuration(it)
        })
        mMovieDetailViewModel.mErrorOccured.observe(this, Observer {
            mMovieDetailBinding.textMovieDuration.text = getString(R.string.duration_not_available)
        })

        mAdapter.mMovieClicked.observe(this, Observer {
            Intent(this, MovieDetailActivity::class.java).apply {
                putExtra(Constants.MOVIE_ID, it)
                startActivity(this)
            }
        })
    }

    private fun getIntentExtra() {
        mMovieDetailViewModel.mMovie = intent.getParcelableExtra(Constants.MOVIE_ID)
        mMovieDetailViewModel.mMovie?.let { movie ->
            setPoster(movie)
            mMovieDetailBinding.movie = movie
            mMovieDetailViewModel.mMovieId = movie.id
        }
    }

    private fun setPoster(movie: Movie?) {
        Glide.with(this)
            .load(Constants.POSTER_IMAGE_BASE_URL + Constants.POSTER_IMAGE_SIZE + movie?.poster_path)
            .placeholder(ColorDrawable(ContextCompat.getColor(this, R.color.accent_material_light)))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .into(image_movie_detail_poster)

    }
}
package com.example.moviedb.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedb.R
import com.example.moviedb.base.Constants
import com.example.moviedb.base.ViewModelFactory
import com.example.moviedb.model.Movie
import com.example.moviedb.ui.movieDetail.MovieDetailActivity
import com.example.moviedb.ui.movieList.MovieListAdapter
import com.example.moviedb.ui.movieList.MovieListViewModel
import com.example.moviedb.utils.ItemOffsetDecoration
import kotlinx.android.synthetic.main.activity_movie_list.*
import kotlinx.android.synthetic.main.layout_progressbar.*

class MovieListActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var mMovieListViewModel : MovieListViewModel
    private val mAdapter = MovieListAdapter(this)
    private var mGridLayoutManager: GridLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        initialData()
        initialObservers()
    }

    private fun initialData(){
        mMovieListViewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(
            MovieListViewModel::class.java)
        showOrHideProgress(View.VISIBLE)
        mMovieListViewModel.getTopMovies(mMovieListViewModel.mPageCount)
        val mLayoutManager = LinearLayoutManager(this)
        movies_grid.adapter = mAdapter
        movies_grid.setHasFixedSize(true)
        movies_grid.layoutManager = mLayoutManager
        movies_grid.itemAnimator = DefaultItemAnimator()
        movies_grid.addItemDecoration(
            ItemOffsetDecoration(
                this,
                R.dimen.movie_item_offset
            )
        )

        val columns = resources.getInteger(R.integer.movies_columns)
        mGridLayoutManager = GridLayoutManager(this, columns)
        movies_grid.layoutManager = mGridLayoutManager

        swipe_refresh.setOnRefreshListener {
            mMovieListViewModel.getTopMovies(mMovieListViewModel.mPageCount)
            swipe_refresh.isRefreshing = false
        }

    }

    private fun initialObservers(){
        mMovieListViewModel.mListofMovies.observe(this, Observer {
            showOrHideProgress(View.GONE)
            mAdapter.setData(it.sortedBy { it.name }.toMutableList())

        })
        mMovieListViewModel.mErrorOccured.observe(this, Observer {
            showOrHideProgress(View.GONE)
            Toast.makeText(this,it.localizedMessage, Toast.LENGTH_LONG).show()
        })
        mAdapter.mMovieClicked.observe(this, Observer {
            Intent(this, MovieDetailActivity::class.java).apply {
                putExtra(Constants.MOVIE_ID,it)
                startActivity(this)
            }
        })
    }

    private fun showOrHideProgress(visibilty : Int){
        progressBar.visibility = visibilty
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val item = if (menu != null) menu.findItem(R.id.spinner) else throw NullPointerException("Expression 'menu' must not be null")
        val spinner = item.actionView as Spinner // get the spinner

        ArrayAdapter.createFromResource(
            this,
            R.array.view_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = this

        return super.onCreateOptionsMenu(menu)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selectedItem =  parent?.getItemAtPosition(position)
        val sortedList = getSortedList(selectedItem?.toString())
        sortedList?.let { mAdapter.setData(it) }
        mAdapter.notifyDataSetChanged()

    }

    private fun getSortedList(settingsName : String?): MutableList<Movie>? {
        if(settingsName.equals(Constants.SETTING_NAME)){
            mMovieListViewModel.mCurrentSetting = Constants.SETTING_NAME

            return mMovieListViewModel.sortMovieByName(mMovieListViewModel.mMovieListCache).toMutableList()

        }else if(settingsName.equals(Constants.SETTING_TIME)){
            mMovieListViewModel.mCurrentSetting = Constants.SETTING_TIME
            return mMovieListViewModel.sortMovieByDate(mMovieListViewModel.mMovieListCache).toMutableList()
        }
        return null
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }


}
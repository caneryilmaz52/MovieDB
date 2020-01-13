package code.challenge.moviedb.ui.movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import code.challenge.moviedb.R
import code.challenge.moviedb.di.component.DaggerFragmentComponent
import code.challenge.moviedb.di.module.FragmentModule

import code.challenge.moviedb.models.MovieList
import code.challenge.moviedb.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_movie.*
import javax.inject.Inject

class MovieFragment : Fragment(),MovieContract.View,MovieAdapter.IOnItemClickListener{

    @Inject
    lateinit var presenter: MovieContract.Presenter

    private lateinit var rootView: View

    fun newInstance(): MovieFragment {
        return MovieFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_movie, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            movie_progressBar.visibility = View.VISIBLE
        } else {
            movie_progressBar.visibility = View.GONE
        }
    }

    override fun showErrorMessage(error: String) {
        Log.e("Error", error)
    }


    override fun loadDataSuccess(list: MovieList) {
        val topRatedLayoutManager = LinearLayoutManager(activity)
        topRatedLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        val topRatedAdapter = MovieAdapter(activity!!, list.topRated.results.toMutableList(), this,
            isHorizontal = true,
            voteBadgeShow = false
        )
        top_rated_movie_rv!!.layoutManager = topRatedLayoutManager
        top_rated_movie_rv!!.adapter = topRatedAdapter

        val nowPlayingLayoutManager = LinearLayoutManager(activity)
        nowPlayingLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        val nowPlayingAdapter = MovieAdapter(activity!!, list.nowPlaying.results.toMutableList(), this,
            isHorizontal = false,
            voteBadgeShow = false
        )
        now_playing_movie_rv!!.layoutManager = nowPlayingLayoutManager
        now_playing_movie_rv!!.adapter = nowPlayingAdapter

        val poplularLayoutManager = LinearLayoutManager(activity)
        poplularLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        val popularAdapter = MovieAdapter(activity!!, list.popular.results.toMutableList(), this,
            isHorizontal = false,
            voteBadgeShow = true
        )
        popular_movie_rv!!.layoutManager = poplularLayoutManager
        popular_movie_rv!!.adapter = popularAdapter
    }

    override fun itemDetail(movieId: Int) {
        val detailIntent = Intent(this@MovieFragment.activity, DetailActivity::class.java)
        detailIntent.putExtra("itemID",movieId)
        detailIntent.putExtra("isMovie", true)
        startActivity(detailIntent)
    }

    private fun injectDependency() {
        val listComponent = DaggerFragmentComponent.builder().fragmentModule(FragmentModule()).build()
        listComponent.inject(this)
    }

    private fun initView() {
        presenter.loadData()
    }

    companion object {
        val TAG: String = "MovieFragment"
    }

}
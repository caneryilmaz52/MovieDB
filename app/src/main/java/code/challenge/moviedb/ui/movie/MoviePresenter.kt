package code.challenge.moviedb.ui.movie

import code.challenge.moviedb.api.IApiService
import code.challenge.moviedb.models.Movie
import code.challenge.moviedb.models.MovieList
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers

class MoviePresenter :MovieContract.Presenter{
    private val subscriptions = CompositeDisposable()
    private val api: IApiService= IApiService.create()
    private lateinit var view: MovieContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: MovieContract.View) {
        this.view = view
    }


    override fun loadData() {
        var subscribe = Observable.zip(api.getTopRatedMovies(), api.getNowPlayingMovies(), api.getPopularMovies(),
            Function3<Movie, Movie, Movie, MovieList> {
                    topRated, nowPlaying, popular -> createMovieList(topRated, nowPlaying, popular)
            }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list: MovieList? ->
                view.showProgress(false)
                view.loadDataSuccess(list!!)
            },{ error ->
                view.showProgress(false)
                view.showErrorMessage(error?.localizedMessage!!)
            })

        subscriptions.add(subscribe)
    }

    private fun createMovieList(topRated:Movie, nowPlaying:Movie, popular:Movie): MovieList {
        return MovieList(topRated, nowPlaying, popular)
    }
}
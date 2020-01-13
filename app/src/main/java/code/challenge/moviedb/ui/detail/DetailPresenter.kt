package code.challenge.moviedb.ui.detail

import code.challenge.moviedb.api.IApiService
import code.challenge.moviedb.models.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class DetailPresenter :DetailContract.Presenter{
    private val subscriptions = CompositeDisposable()
    private val api: IApiService = IApiService.create()
    private lateinit var view: DetailContract.View


    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: DetailContract.View) {
        this.view = view
    }

    override fun loadData(itemID: Int, isMovie:Boolean) {
        if(isMovie){
            var subscribe = Observable.zip(api.getMovieDetail(itemID), api.getMovieCredits(itemID),
                BiFunction<MovieDetail, Credits, MovieDetailList> {
                        movieDetail, credits -> createMovieDetailList(movieDetail, credits)
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list: MovieDetailList? ->
                    view.showProgress(false)
                    view.loadDataSuccess(list!!,null)
                },{ error ->
                    view.showProgress(false)
                    view.showErrorMessage(error?.localizedMessage!!)
                })

            subscriptions.add(subscribe)
        }else{
            var subscribe = Observable.zip(api.getTVDetail(itemID), api.getTVCredits(itemID),
                BiFunction<TVDetail, Credits, TvDetailList> {
                        tvDetail, credits -> createTvDetailList(tvDetail, credits)
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list: TvDetailList? ->
                    view.showProgress(false)
                    view.loadDataSuccess(null, list!!)
                },{ error ->
                    view.showProgress(false)
                    view.showErrorMessage(error?.localizedMessage!!)
                })

            subscriptions.add(subscribe)
        }
    }

    override fun backPress() {
        view.backPress()
    }

    private fun createMovieDetailList(movieDetail: MovieDetail,credits: Credits): MovieDetailList {
        return MovieDetailList(movieDetail,credits)
    }

    private fun createTvDetailList(tvDetail: TVDetail,credits: Credits): TvDetailList {
        return TvDetailList(tvDetail,credits)
    }
}
package code.challenge.moviedb.ui.tv

import code.challenge.moviedb.api.IApiService
import code.challenge.moviedb.models.TV
import code.challenge.moviedb.models.TvList
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class TvPresenter : TvContract.Presenter{
    private val subscriptions = CompositeDisposable()
    private val api: IApiService= IApiService.create()
    private lateinit var view: TvContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: TvContract.View) {
        this.view = view
    }

    override fun loadData() {

        var subscribe = Observable.zip(api.getTopRatedTV(), api.getPopularTV(),
            BiFunction<TV, TV, TvList> {
                    topRated, popular -> createTvList(topRated,popular)
            }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({list: TvList? ->
                view.showProgress(false)
                view.loadDataSuccess(list!!)
            },{ error ->
                view.showProgress(false)
                view.showErrorMessage(error?.localizedMessage!!)
            })

        subscriptions.add(subscribe)
    }

    private fun createTvList(topRated:TV, popular:TV): TvList {
        return TvList(topRated, popular)
    }
}
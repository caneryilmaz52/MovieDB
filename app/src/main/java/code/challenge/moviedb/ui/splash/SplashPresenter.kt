package code.challenge.moviedb.ui.splash

import io.reactivex.disposables.CompositeDisposable

class SplashPresenter :SplashContract.Presenter{
    private val subscriptions = CompositeDisposable()
    private lateinit var view:SplashContract.View


    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: SplashContract.View) {
        this.view=view
        view.finishSplash()
    }
}
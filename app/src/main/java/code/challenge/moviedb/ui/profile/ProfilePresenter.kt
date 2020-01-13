package code.challenge.moviedb.ui.profile

import io.reactivex.disposables.CompositeDisposable

class ProfilePresenter : ProfileContract.Presenter{
    private val subscriptions = CompositeDisposable()
    private lateinit var view: ProfileContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: ProfileContract.View) {
        this.view = view
    }
}
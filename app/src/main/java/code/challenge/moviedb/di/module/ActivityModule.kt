package code.challenge.moviedb.di.module

import android.app.Activity
import code.challenge.moviedb.ui.detail.DetailContract
import code.challenge.moviedb.ui.detail.DetailPresenter
import code.challenge.moviedb.ui.main.MainContract
import code.challenge.moviedb.ui.main.MainPresenter
import code.challenge.moviedb.ui.splash.SplashContract
import code.challenge.moviedb.ui.splash.SplashPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule (private val activity:Activity){

    @Provides
    fun provideSplashActivity():Activity{
        return activity
    }

    @Provides
    fun provideSplashPresenter(): SplashContract.Presenter{
        return SplashPresenter()
    }

    @Provides
    fun provideMainActivity():Activity{
        return activity
    }

    @Provides
    fun provideMainPresenter(): MainContract.Presenter{
        return MainPresenter()
    }

    @Provides
    fun provideDetailActivity():Activity{
        return activity
    }

    @Provides
    fun provideDetailPresenter(): DetailContract.Presenter{
        return DetailPresenter()
    }
}
package code.challenge.moviedb.di.component

import code.challenge.moviedb.ui.splash.SplashActivity
import code.challenge.moviedb.di.module.ActivityModule
import code.challenge.moviedb.ui.detail.DetailActivity
import code.challenge.moviedb.ui.main.MainActivity
import dagger.Component

@Component(modules = [ActivityModule::class])
interface ActivityComponent{

    fun inject(splashActivity: SplashActivity)
    fun inject(mainActivity: MainActivity)
    fun inject(detailActivity: DetailActivity)
}
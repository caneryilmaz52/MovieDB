package code.challenge.moviedb.di.component

import code.challenge.moviedb.BaseApp
import code.challenge.moviedb.di.module.ApplicationModule
import dagger.Component

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(application: BaseApp)
}
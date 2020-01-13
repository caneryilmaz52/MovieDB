package code.challenge.moviedb

import android.app.Application
import code.challenge.moviedb.di.component.ApplicationComponent
import code.challenge.moviedb.di.component.DaggerApplicationComponent
import code.challenge.moviedb.di.module.ApplicationModule

class BaseApp : Application(){

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        setup()
    }

    fun setup() {
        component = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: BaseApp private set
    }
}
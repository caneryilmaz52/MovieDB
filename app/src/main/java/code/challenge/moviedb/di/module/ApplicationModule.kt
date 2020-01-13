package code.challenge.moviedb.di.module

import android.app.Application
import code.challenge.moviedb.BaseApp
import code.challenge.moviedb.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule (private val baseApp:BaseApp){

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication():Application{
        return baseApp
    }

}
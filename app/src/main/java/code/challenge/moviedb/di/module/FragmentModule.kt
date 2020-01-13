package code.challenge.moviedb.di.module

import code.challenge.moviedb.api.IApiService
import code.challenge.moviedb.ui.movie.MovieContract
import code.challenge.moviedb.ui.movie.MoviePresenter
import code.challenge.moviedb.ui.profile.ProfileContract
import code.challenge.moviedb.ui.profile.ProfilePresenter
import code.challenge.moviedb.ui.tv.TvContract
import code.challenge.moviedb.ui.tv.TvPresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {
    @Provides
    fun provideMoviePresenter(): MovieContract.Presenter {
        return MoviePresenter()
    }

    @Provides
    fun provideTvPresenter(): TvContract.Presenter {
        return TvPresenter()
    }

    @Provides
    fun provideProfilePresenter(): ProfileContract.Presenter {
        return ProfilePresenter()
    }

    @Provides
    fun provideApiService(): IApiService{
        return IApiService.create()
    }
}
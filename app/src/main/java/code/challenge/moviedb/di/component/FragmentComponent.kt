package code.challenge.moviedb.di.component

import code.challenge.moviedb.di.module.FragmentModule
import code.challenge.moviedb.ui.movie.MovieFragment
import code.challenge.moviedb.ui.profile.ProfileFragment
import code.challenge.moviedb.ui.tv.TvFragment
import dagger.Component

@Component(modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject(movieFragment: MovieFragment)
    fun inject(tvFragment: TvFragment)
    fun inject(profileFragment: ProfileFragment)

}
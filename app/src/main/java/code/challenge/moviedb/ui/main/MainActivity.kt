package code.challenge.moviedb.ui.main

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import code.challenge.moviedb.R
import code.challenge.moviedb.di.component.DaggerActivityComponent
import code.challenge.moviedb.di.module.ActivityModule

import code.challenge.moviedb.ui.movie.MovieFragment
import code.challenge.moviedb.ui.profile.ProfileFragment
import code.challenge.moviedb.ui.tv.TvFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View{

    @Inject lateinit var presenter:MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDependency()
        presenter.attach(this)
    }

    override fun showBottomNavigation() {
        val bottomNavigation = bottom_navigation
        bottomNavigation.background = ColorDrawable(Color.WHITE)

        bottomNavigation.setOnNavigationItemSelectedListener {menuItem: MenuItem ->
            when(menuItem.itemId){
                R.id.navigation_movies ->{
                    presenter.onBottomNavigationItemClick(this,0)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_tv -> {
                    presenter.onBottomNavigationItemClick(this,1)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_profile ->{
                    presenter.onBottomNavigationItemClick(this,2)
                    return@setOnNavigationItemSelectedListener true
                }
                else ->{
                    return@setOnNavigationItemSelectedListener true
                }
            }
        }
    }

    override fun showMovieFragment() {
        supportFragmentManager.beginTransaction()
            .disallowAddToBackStack()
            .replace(R.id.main_frame_layout, MovieFragment().newInstance(), MovieFragment.TAG)
            .commit()
    }

    override fun showTVFragment() {
        supportFragmentManager.beginTransaction()
            .disallowAddToBackStack()
            .replace(R.id.main_frame_layout, TvFragment().newInstance(), TvFragment.TAG)
            .commit()
    }

    override fun showProfileFragment() {
        supportFragmentManager.beginTransaction()
            .disallowAddToBackStack()
            .replace(R.id.main_frame_layout, ProfileFragment().newInstance(), ProfileFragment.TAG)
            .commit()
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        val fragment = fragmentManager.findFragmentByTag(MovieFragment.TAG)

        if (fragment == null) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder().activityModule(ActivityModule(this)).build()
        activityComponent.inject(this)
    }

}
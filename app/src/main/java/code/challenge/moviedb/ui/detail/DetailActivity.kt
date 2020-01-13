package code.challenge.moviedb.ui.detail


import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import code.challenge.moviedb.R
import code.challenge.moviedb.di.component.DaggerActivityComponent
import code.challenge.moviedb.di.module.ActivityModule
import code.challenge.moviedb.models.Genre
import code.challenge.moviedb.models.MovieDetailList
import code.challenge.moviedb.models.TvDetailList
import code.challenge.moviedb.util.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

import javax.inject.Inject

class DetailActivity : AppCompatActivity(), View.OnClickListener, DetailContract.View{

    @Inject lateinit var presenter:DetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        injectDependency()

        val itemID = intent.getIntExtra("itemID",0)
        val isMovie= intent.getBooleanExtra("isMovie",true)

        presenter.attach(this)
        presenter.loadData(itemID,isMovie)
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            detail_progressBar.visibility = View.VISIBLE
        } else {
            detail_progressBar.visibility = View.GONE
        }
    }

    override fun showErrorMessage(error: String) {
        Log.e("Error", error)
    }

    override fun loadDataSuccess(movieList: MovieDetailList?,tvList:TvDetailList?) {

        if(movieList != null){
            val movieDetail = movieList.movieDetail
            Picasso.with(this).load("${Constants.BASE_IMAGE_URL}${movieDetail.backdrop_path}").into(detail_cover_iv)
            Picasso.with(this).load("${Constants.BASE_IMAGE_URL}${movieDetail.poster_path}").into(detail_poster_iv)

            detail_title_tv.text = movieDetail.title

            detail_summary_tv.movementMethod = ScrollingMovementMethod()
            detail_summary_tv.text = movieDetail.overview
            detail_vote_tv.text = movieDetail.vote_average.toString()

            var genresText =""
            movieDetail.genres.forEach { genre: Genre ->
                genresText += "${genre.name}, "
            }

            detail_genre_tv.text = genresText.substring(0,genresText.lastIndex-1)

            detail_rating_bar.rating = (movieDetail.vote_average/2).toFloat()

            val creditsLayoutManager = LinearLayoutManager(this@DetailActivity)
            creditsLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            val topRatedAdapter = DetailAdapter(this@DetailActivity, movieList.credits)
            detail_credits_rv!!.layoutManager = creditsLayoutManager
            detail_credits_rv!!.adapter = topRatedAdapter

            detail_back_ib.setOnClickListener(this)

            detail_back_tv.setOnClickListener(this)
        }else{
            val tvDetail = tvList!!.tvDetail
            Picasso.with(this).load("${Constants.BASE_IMAGE_URL}${tvDetail.backdrop_path}").into(detail_cover_iv)
            Picasso.with(this).load("${Constants.BASE_IMAGE_URL}${tvDetail.poster_path}").into(detail_poster_iv)

            detail_title_tv.text = tvDetail.name
            detail_summary_tv.movementMethod = ScrollingMovementMethod()
            detail_summary_tv.text = tvDetail.overview
            detail_vote_tv.text = tvDetail.vote_average.toString()

            var genresText =""
            tvDetail.genres.forEach { genre: Genre ->
                genresText += "${genre.name}, "
            }

            detail_genre_tv.text = genresText.substring(0,genresText.lastIndex-1)

            detail_rating_bar.rating = (tvDetail.vote_average/2).toFloat()

            val creditsLayoutManager = LinearLayoutManager(this@DetailActivity)
            creditsLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            val topRatedAdapter = DetailAdapter(this@DetailActivity, tvList.credits)
            detail_credits_rv!!.layoutManager = creditsLayoutManager
            detail_credits_rv!!.adapter = topRatedAdapter

            detail_back_ib.setOnClickListener(this)

            detail_back_tv.setOnClickListener(this)
        }
    }

    override fun backPress() {
        super.onBackPressed()
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder().activityModule(ActivityModule(this)).build()
        activityComponent.inject(this)
    }

    override fun onClick(v: View?) {
        presenter.backPress()
    }
}
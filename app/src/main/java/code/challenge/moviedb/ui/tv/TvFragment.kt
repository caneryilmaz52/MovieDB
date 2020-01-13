package code.challenge.moviedb.ui.tv

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import code.challenge.moviedb.R
import code.challenge.moviedb.di.component.DaggerFragmentComponent
import code.challenge.moviedb.di.module.FragmentModule
import code.challenge.moviedb.models.TvList
import code.challenge.moviedb.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_tv.*

import javax.inject.Inject

class TvFragment : Fragment(),TvContract.View,TvAdapter.IOnItemClickListener{

    @Inject
    lateinit var presenter: TvContract.Presenter

    private lateinit var rootView: View

    fun newInstance(): TvFragment {
        return TvFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_tv, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            tv_progressBar.visibility = View.VISIBLE
        } else {
            tv_progressBar.visibility = View.GONE
        }
    }

    override fun showErrorMessage(error: String) {
        Log.e("Error", error)
    }


    override fun loadDataSuccess(list: TvList) {
        val topRatedLayoutManager = LinearLayoutManager(activity)
        topRatedLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        val topRatedAdapter = TvAdapter(activity!!, list.topRated.results.toMutableList(), this,
            isHorizontal = false,
            voteBadgeShow = false
        )
        top_rated_tv_rv!!.layoutManager = topRatedLayoutManager
        top_rated_tv_rv!!.adapter = topRatedAdapter

        val poplularLayoutManager = LinearLayoutManager(activity)
        poplularLayoutManager.orientation = LinearLayoutManager.VERTICAL
        val popularAdapter = TvAdapter(activity!!, list.popular.results.toMutableList(), this,
            isHorizontal = true,
            voteBadgeShow = true
        )
        popular_tv_rv!!.layoutManager = poplularLayoutManager
        popular_tv_rv!!.adapter = popularAdapter
    }

    override fun itemDetail(tvID: Int) {
        val detailIntent = Intent(this@TvFragment.activity, DetailActivity::class.java)
        detailIntent.putExtra("itemID",tvID)
        detailIntent.putExtra("isMovie", false)
        startActivity(detailIntent)
    }

    private fun injectDependency() {
        val listComponent = DaggerFragmentComponent.builder().fragmentModule(FragmentModule()).build()
        listComponent.inject(this)
    }

    private fun initView() {
        presenter.loadData()
    }

    companion object {
        val TAG: String = "TvFragment"
    }

}
package code.challenge.moviedb.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import code.challenge.moviedb.R
import code.challenge.moviedb.di.component.DaggerFragmentComponent
import code.challenge.moviedb.di.module.FragmentModule

import javax.inject.Inject

class ProfileFragment : Fragment(),ProfileContract.View{

    @Inject
    lateinit var presenter: ProfileContract.Presenter

    private lateinit var rootView: View

    fun newInstance(): ProfileFragment {
        return ProfileFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_profile, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun showErrorMessage(error: String) {
        Log.e("Error", error)
    }

    private fun injectDependency() {
        val listComponent = DaggerFragmentComponent.builder().fragmentModule(FragmentModule()).build()
        listComponent.inject(this)
    }

    companion object {
        val TAG: String = "ProfileFragment"
    }

}
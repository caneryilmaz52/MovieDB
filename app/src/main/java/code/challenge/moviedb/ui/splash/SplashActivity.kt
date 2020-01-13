package code.challenge.moviedb.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import code.challenge.moviedb.R
import code.challenge.moviedb.di.component.DaggerActivityComponent
import code.challenge.moviedb.di.module.ActivityModule
import code.challenge.moviedb.ui.main.MainActivity
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), SplashContract.View {

    @Inject
    lateinit var presenter:SplashContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        this.actionBar?.hide()
        setContentView(R.layout.activity_splash)

        injectDependency()
        presenter.attach(this)
    }

    override fun finishSplash() {
        val delayTime = 2000
        val homeIntent = Intent(this@SplashActivity, MainActivity::class.java)
        Handler().postDelayed({
            startActivity(homeIntent)
            finish()
        }, delayTime.toLong())
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder().activityModule(ActivityModule(this)).build()
        activityComponent.inject(this)
    }
}

package com.sweetmay.githubclient
import android.os.Bundle
import com.sweetmay.App
import com.sweetmay.githubclient.presenter.MainPresenter
import com.sweetmay.githubclient.view.MainView
import com.sweetmay.githubclient.view.fragments.BackButtonListener
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.android.support.SupportAppNavigator


class MainActivity : MvpAppCompatActivity(), MainView {

    private val presenter by moxyPresenter { MainPresenter() }
    private val navigatorHolder = App.instance.getNavigationHolder()
    private val navigator = SupportAppNavigator(this, R.id.fragment_container)


    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        for (fragment in supportFragmentManager.fragments) {
            if (fragment is BackButtonListener && (fragment as BackButtonListener).backPressed()) {
                return
            }
        }
    }
}
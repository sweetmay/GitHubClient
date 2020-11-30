package com.sweetmay.githubclient.presenter

import com.sweetmay.App
import com.sweetmay.githubclient.navigation.Screens
import com.sweetmay.githubclient.view.MainView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    init {
        App.instance.appComponent.inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        router.replaceScreen(Screens.UsersScreen())
    }

}
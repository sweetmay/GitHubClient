package com.sweetmay.githubclient.presenter

import com.sweetmay.App
import com.sweetmay.githubclient.navigation.Screens
import com.sweetmay.githubclient.view.MainView
import moxy.MvpPresenter

class MainPresenter : MvpPresenter<MainView>() {
    private val router = App.instance.getRouter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        router.replaceScreen(Screens.UsersScreen())
    }

    fun backClicked() {
        router.exit()
    }
}
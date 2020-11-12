package com.sweetmay.githubclient.presenter

import com.sweetmay.App
import com.sweetmay.githubclient.view.PersonalView
import moxy.MvpPresenter

class PersonalPresenter : MvpPresenter<PersonalView>() {
    private val router = App.instance.getRouter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.renderData()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
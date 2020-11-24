package com.sweetmay.githubclient.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface ReposView: MvpView {
    fun initRV()
    fun updateList()
    fun renderUserName()
}
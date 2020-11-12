package com.sweetmay.githubclient.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface PersonalView: MvpView {
    fun renderData()
}
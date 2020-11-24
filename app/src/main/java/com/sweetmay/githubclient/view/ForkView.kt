package com.sweetmay.githubclient.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface ForkView: MvpView {
    fun showForks(numberOfForks: String)
}
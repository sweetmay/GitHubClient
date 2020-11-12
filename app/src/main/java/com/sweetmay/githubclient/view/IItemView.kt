package com.sweetmay.githubclient.view

import moxy.MvpView

interface IItemView : MvpView {
    fun getPos(): Int
}
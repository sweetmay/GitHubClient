package com.sweetmay.githubclient.view

import moxy.MvpView

interface UserItemView : IItemView {
    fun setLogin(text: String)
    fun setAvatar(url: String)
}
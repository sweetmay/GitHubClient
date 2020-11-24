package com.sweetmay.githubclient.view.image

import android.view.View

interface IImageLoader<T: View> {
    fun loadImage(url: String, viewContainer: T)
}
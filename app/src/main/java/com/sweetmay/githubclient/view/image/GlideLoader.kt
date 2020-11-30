package com.sweetmay.githubclient.view.image

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideLoader: IImageLoader<ImageView>{
    override fun loadImage(url: String, viewContainer: ImageView) {
        Glide.with(viewContainer.context).load(url).into(viewContainer)
    }
}
package com.sweetmay.githubclient.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.sweetmay.githubclient.R
import com.sweetmay.githubclient.presenter.list.IUserListPresenter
import com.sweetmay.githubclient.view.UserItemView
import com.sweetmay.githubclient.view.image.GlideLoader
import com.sweetmay.githubclient.view.image.IImageLoader
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapterRV(val presenter: IUserListPresenter, val loader: IImageLoader<ImageView>) : RecyclerView.Adapter<UserAdapterRV.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemPos = position
        holder.itemView.setOnClickListener {
            presenter.onItemClick(holder)
        }
        presenter.bindView(holder)
    }

    override fun getItemCount(): Int {
        return presenter.getCount()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), UserItemView {

        var itemPos = -1

        override fun setLogin(text: String) {
            itemView.user_login.text = text
        }

        override fun setAvatar(url: String) {
            loader.loadImage(url, itemView.avatar_img)
        }


        override fun getPos(): Int {
            return itemPos
        }
    }


}
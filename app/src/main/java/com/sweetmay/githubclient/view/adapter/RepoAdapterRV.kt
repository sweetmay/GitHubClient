package com.sweetmay.githubclient.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sweetmay.githubclient.R
import com.sweetmay.githubclient.presenter.list.IRepoListPresenter
import com.sweetmay.githubclient.view.RepoItemView
import kotlinx.android.synthetic.main.item_repo.view.*

class RepoAdapterRV(val presenter: IRepoListPresenter): RecyclerView.Adapter<RepoAdapterRV.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false))
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

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), RepoItemView {

        var itemPos = -1

        override fun setName(name: String) {
            itemView.repo_name.text = name
        }

        override fun getPos(): Int {
            return itemPos
        }

    }

}
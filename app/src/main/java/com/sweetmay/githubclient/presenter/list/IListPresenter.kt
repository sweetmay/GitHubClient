package com.sweetmay.githubclient.presenter.list

import com.sweetmay.githubclient.view.IItemView

interface IListPresenter<V: IItemView> {
    fun onItemClick(view: V)
    fun bindView(view: V)
    fun getCount(): Int
}
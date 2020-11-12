package com.sweetmay.githubclient.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sweetmay.githubclient.R
import com.sweetmay.githubclient.presenter.UsersPresenter
import com.sweetmay.githubclient.view.UsersView
import com.sweetmay.githubclient.view.adapter.UserAdapterRV
import kotlinx.android.synthetic.main.fragment_users.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(R.layout.fragment_users), UsersView, BackButtonListener {

    private val presenter by moxyPresenter { UsersPresenter() }
    lateinit var adapterRV: UserAdapterRV

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun init() {
        users_rv.layoutManager = LinearLayoutManager(context)
        adapterRV = UserAdapterRV(presenter.usersListPresenter)
        users_rv.adapter = adapterRV
    }

    override fun updateList() {
        adapterRV.notifyDataSetChanged()
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }

}
package com.sweetmay.githubclient.presenter

import com.sweetmay.App
import com.sweetmay.githubclient.model.entity.GitHubUser
import com.sweetmay.githubclient.model.entity.GitHubUserRepo
import com.sweetmay.githubclient.navigation.Screens
import com.sweetmay.githubclient.presenter.list.IUserListPresenter
import com.sweetmay.githubclient.view.UserItemView
import com.sweetmay.githubclient.view.UsersView
import moxy.MvpPresenter

class UsersPresenter: MvpPresenter<UsersView>() {

    private val gitHubUserRepo = GitHubUserRepo()
    private val router = App.instance.getRouter()

    inner class UsersListPresenter : IUserListPresenter {

        var users = ArrayList<GitHubUser>()

        override fun onItemClick(view: UserItemView) {
            router.replaceScreen(Screens.PersonalScreen(users[view.getPos()]))
        }

        override fun bindView(view: UserItemView) {
            view.setLogin(users[view.getPos()].login)
        }

        override fun getCount(): Int {
            return users.size
        }

    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData(){
        val users = gitHubUserRepo.users
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean{
        router.exit()
        return true
    }
}
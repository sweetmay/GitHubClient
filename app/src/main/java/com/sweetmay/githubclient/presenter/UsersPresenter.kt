package com.sweetmay.githubclient.presenter

import android.util.Log
import com.sweetmay.App
import com.sweetmay.githubclient.model.entity.GitHubUser
import com.sweetmay.githubclient.model.repo.retrofit.RetrofitUsersRepo
import com.sweetmay.githubclient.model.repo.retrofit.api.IDataSource
import com.sweetmay.githubclient.navigation.Screens
import com.sweetmay.githubclient.presenter.list.IUserListPresenter
import com.sweetmay.githubclient.view.UserItemView
import com.sweetmay.githubclient.view.UsersView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class UsersPresenter(dataSource: IDataSource, val scheduler: Scheduler): MvpPresenter<UsersView>() {

    companion object{
        private val TAG: String = this::class.java.name
    }
    private val router = App.instance.getRouter()
    private val usersRepo = RetrofitUsersRepo(dataSource)

    inner class UsersListPresenter : IUserListPresenter {

        var users = ArrayList<GitHubUser>()

        override fun onItemClick(view: UserItemView) {
            router.navigateTo(Screens.RepoScreen(users[view.getPos()]))
        }

        override fun bindView(view: UserItemView) {
            view.setLogin(users[view.getPos()].login)
            view.setAvatar(users[view.getPos()].avatar_url)
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
        usersRepo.getUsers().observeOn(scheduler).subscribe({list ->
            usersListPresenter.users.clear()
            usersListPresenter.users.addAll(list)
            viewState.updateList()
        }, {err ->
            Log.w(TAG, err.message.toString())
        })
    }

}
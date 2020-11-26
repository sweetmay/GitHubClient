package com.sweetmay.githubclient.presenter

import android.util.Log
import com.sweetmay.App
import com.sweetmay.githubclient.model.cache.room.RoomCache
import com.sweetmay.githubclient.model.entity.GitHubUser
import com.sweetmay.githubclient.model.entity.db.RoomUser
import com.sweetmay.githubclient.model.repo.retrofit.RetrofitUsersRepo
import com.sweetmay.githubclient.model.repo.retrofit.api.IDataSource
import com.sweetmay.githubclient.navigation.Screens
import com.sweetmay.githubclient.presenter.list.IUserListPresenter
import com.sweetmay.githubclient.view.UserItemView
import com.sweetmay.githubclient.view.UsersView
import com.sweetmay.utils.NetworkStatus
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class UsersPresenter(dataSource: IDataSource, private val scheduler: Scheduler, val networkStatus: NetworkStatus, private val cache: RoomCache): MvpPresenter<UsersView>() {

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
        networkStatus.isOnlineSingle().subscribe { isOnline ->
            if (isOnline) {
                loadDataRetrofit()
            } else {
                loadDataFromCache()
            }
        }
    }

    private fun loadDataFromCache() {
        cache.getUsers().observeOn(scheduler).subscribe { list ->
            usersListPresenter.users.clear()
            list.forEach {
                usersListPresenter.users.add(
                    GitHubUser(
                        it.login,
                        it.id,
                        it.avatar_url,
                        it.repos_url
                    )
                )
            }
            viewState.updateList()
        }
    }

    private fun loadDataRetrofit() {
        usersRepo.getUsers().observeOn(scheduler).subscribe({ list ->
            usersListPresenter.users.clear()
            usersListPresenter.users.addAll(list)
            viewState.updateList()
            saveUsersToCache(list)
        }, { err ->
            Log.w(TAG, err.message.toString())
        })
    }

    private fun saveUsersToCache(list: List<GitHubUser>?) {
        val users = arrayListOf<RoomUser>()
        list?.forEach {
            users.add(RoomUser(it.login, it.id, it.avatar_url, it.repos_url))
        }
        cache.insertUsers(users).subscribe()
    }

}
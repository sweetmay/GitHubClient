package com.sweetmay.githubclient.presenter

import android.util.Log
import com.sweetmay.App
import com.sweetmay.githubclient.model.cache.IUsersCache
import com.sweetmay.githubclient.model.cache.room.RoomCache
import com.sweetmay.githubclient.model.entity.GitHubUser
import com.sweetmay.githubclient.model.entity.UsersRepo
import com.sweetmay.githubclient.model.entity.db.RoomUser
import com.sweetmay.githubclient.model.network.INetworkStatus
import com.sweetmay.githubclient.model.repo.IUsersRepo
import com.sweetmay.githubclient.model.repo.retrofit.RetrofitUsersRepo
import com.sweetmay.githubclient.model.repo.retrofit.api.IDataSource
import com.sweetmay.githubclient.navigation.Screens
import com.sweetmay.githubclient.presenter.list.IUserListPresenter
import com.sweetmay.githubclient.view.UserItemView
import com.sweetmay.githubclient.view.UsersView
import com.sweetmay.utils.NetworkStatus
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class UsersPresenter(): MvpPresenter<UsersView>() {

    @Inject
    lateinit var usersRepo: IUsersRepo
    @Inject
    lateinit var scheduler: Scheduler
    @Inject
    lateinit var router: Router

    init {
        App.instance.appComponent.inject(this)
    }

    companion object{
        private val TAG: String = this::class.java.name
    }

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
        usersRepo.getUsers().observeOn(scheduler).subscribe({ list->
            usersListPresenter.users.clear()
            usersListPresenter.users.addAll(list)
            viewState.updateList()
        }, {
            Log.d(TAG, it.toString())
    })
}
}
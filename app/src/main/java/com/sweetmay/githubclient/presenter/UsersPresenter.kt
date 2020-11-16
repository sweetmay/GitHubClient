package com.sweetmay.githubclient.presenter

import com.sweetmay.App
import com.sweetmay.githubclient.model.entity.GitHubUser
import com.sweetmay.githubclient.model.entity.GitHubUserRepo
import com.sweetmay.githubclient.navigation.Screens
import com.sweetmay.githubclient.presenter.list.IUserListPresenter
import com.sweetmay.githubclient.view.UserItemView
import com.sweetmay.githubclient.view.UsersView
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class UsersPresenter: MvpPresenter<UsersView>() {

    private val gitHubUserRepo = GitHubUserRepo()
    private val router = App.instance.getRouter()

    private val observer = object : Observer<List<GitHubUser>>{
        override fun onSubscribe(d: Disposable?) {
        }
        override fun onNext(t: List<GitHubUser>?) {
            if (t != null) {
                usersListPresenter.users.addAll(t)
            }
        }

        override fun onError(e: Throwable?) {
        }

        override fun onComplete() {
            viewState.updateList()
        }
    }

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
        gitHubUserRepo.execJust().subscribe(observer)
    }

    fun backPressed(): Boolean{
        router.exit()
        return true
    }
}
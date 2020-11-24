package com.sweetmay.githubclient.presenter

import android.util.Log
import com.sweetmay.App
import com.sweetmay.githubclient.model.entity.GitHubUser
import com.sweetmay.githubclient.model.entity.UsersRepo
import com.sweetmay.githubclient.model.repo.retrofit.api.IDataSource
import com.sweetmay.githubclient.navigation.Screens
import com.sweetmay.githubclient.presenter.list.IRepoListPresenter
import com.sweetmay.githubclient.view.RepoItemView
import com.sweetmay.githubclient.view.ReposView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class RepoPresenter(val dataSource: IDataSource, private val scheduler: Scheduler): MvpPresenter<ReposView>() {

    companion object{
        private val TAG: String = this::class.java.name
    }

    private val router = App.instance.getRouter()

    val repoListPresenter = RepoListPresenter()

    inner class RepoListPresenter: IRepoListPresenter{

        var repos = ArrayList<UsersRepo>()

        override fun onItemClick(view: RepoItemView) {
            router.navigateTo(Screens.ForkScreen(repos[view.getPos()]))
        }

        override fun bindView(view: RepoItemView) {
            view.setName(repos[view.getPos()].name)
        }

        override fun getCount(): Int {
            return repos.size
        }

    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initRV()
        viewState.renderUserName()
    }

    fun loadRepos(gitHubUser: GitHubUser) {
        dataSource.getRepos(gitHubUser.repos_url).observeOn(scheduler).subscribe({list ->
            repoListPresenter.repos.clear()
            repoListPresenter.repos.addAll(list)
            viewState.updateList()
        }, {err ->
            Log.w(TAG, err.message.toString())
        })
}
}
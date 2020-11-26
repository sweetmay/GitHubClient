package com.sweetmay.githubclient.presenter

import android.util.Log
import com.sweetmay.App
import com.sweetmay.githubclient.model.cache.room.RoomCache
import com.sweetmay.githubclient.model.entity.GitHubUser
import com.sweetmay.githubclient.model.entity.UsersRepo
import com.sweetmay.githubclient.model.entity.db.RoomRepo
import com.sweetmay.githubclient.model.repo.retrofit.api.IDataSource
import com.sweetmay.githubclient.navigation.Screens
import com.sweetmay.githubclient.presenter.list.IRepoListPresenter
import com.sweetmay.githubclient.view.RepoItemView
import com.sweetmay.githubclient.view.ReposView
import com.sweetmay.utils.NetworkStatus
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class RepoPresenter(private val dataSource: IDataSource, private val scheduler: Scheduler, val networkStatus: NetworkStatus, val cache: RoomCache): MvpPresenter<ReposView>() {

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
        networkStatus.isOnlineSingle().subscribe { isOnline ->
            if (isOnline) {
                loadDataRetrofit(gitHubUser)
            } else {
                loadDataFromCache(gitHubUser)
            }
        }

}

    private fun loadDataFromCache(gitHubUser: GitHubUser) {
        cache.getRepos(gitHubUser).observeOn(scheduler).subscribe { list ->
            val repos = arrayListOf<UsersRepo>()
            list.forEach { repos.add(UsersRepo(it.id, it.name, it.forks_url)) }
            repoListPresenter.repos.clear()
            repoListPresenter.repos.addAll(repos)
            viewState.updateList()
        }
    }

    private fun loadDataRetrofit(gitHubUser: GitHubUser) {
        dataSource.getRepos(gitHubUser.repos_url).observeOn(scheduler).subscribe({list ->
            repoListPresenter.repos.clear()
            repoListPresenter.repos.addAll(list)
            viewState.updateList()
            saveToCache(list, gitHubUser)
        }, {err ->
            Log.w(TAG, err.message.toString())
        })

    }

    private fun saveToCache(
        list: List<UsersRepo>,
        gitHubUser: GitHubUser
    ) {
        val repos = arrayListOf<RoomRepo>()
        list.forEach { repos.add(RoomRepo(it.id, it.name, it.forks_url, gitHubUser.id.toString())) }
        cache.insertRepos(repos).subscribe()
    }
}
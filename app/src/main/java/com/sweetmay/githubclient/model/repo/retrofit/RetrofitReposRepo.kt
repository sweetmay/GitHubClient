package com.sweetmay.githubclient.model.repo.retrofit

import com.sweetmay.githubclient.model.cache.IRepoCache
import com.sweetmay.githubclient.model.cache.IUsersCache
import com.sweetmay.githubclient.model.entity.GitHubUser
import com.sweetmay.githubclient.model.entity.UsersRepo
import com.sweetmay.githubclient.model.network.INetworkStatus
import com.sweetmay.githubclient.model.repo.IReposRepo
import com.sweetmay.githubclient.model.repo.retrofit.api.IDataSource
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitReposRepo(val dataSource: IDataSource,
                        val networkStatus: INetworkStatus,
                        val repoCache: IRepoCache): IReposRepo {

    override fun getRepos(gitHubUser: GitHubUser): Single<List<UsersRepo>> {
        return networkStatus.isOnlineSingle().flatMap{ isOnline ->
            if (isOnline){
                dataSource.getRepos(gitHubUser.repos_url).subscribeOn(Schedulers.io()).doAfterSuccess{
                    repoCache.insertRepos(it, gitHubUser).subscribe()
                }
            }else {
                repoCache.getRepos(gitHubUser).subscribeOn(Schedulers.io())
            }
        }
    }
}
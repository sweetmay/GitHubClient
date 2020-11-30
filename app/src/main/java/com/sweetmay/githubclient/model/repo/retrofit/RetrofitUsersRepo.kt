package com.sweetmay.githubclient.model.repo.retrofit

import android.util.Log
import com.sweetmay.githubclient.model.cache.IUsersCache
import com.sweetmay.githubclient.model.entity.GitHubUser
import com.sweetmay.githubclient.model.network.INetworkStatus
import com.sweetmay.githubclient.model.repo.IUsersRepo
import com.sweetmay.githubclient.model.repo.retrofit.api.IDataSource
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitUsersRepo(val dataSource: IDataSource,
                        val networkStatus: INetworkStatus,
                        val userCache: IUsersCache): IUsersRepo{

    override fun getUsers(): Single<List<GitHubUser>> {
        return networkStatus.isOnlineSingle().flatMap{
            if (it){
                dataSource.getUsers().subscribeOn(Schedulers.io()).doAfterSuccess {list->
                    userCache.insertUsers(list).subscribe()
                }
            }else {
                userCache.getUsers().subscribeOn(Schedulers.io())
            }
        }
    }
}
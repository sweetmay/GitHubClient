package com.sweetmay.githubclient.di.modules

import com.sweetmay.githubclient.model.cache.IRepoCache
import com.sweetmay.githubclient.model.cache.IUsersCache
import com.sweetmay.githubclient.model.cache.room.RoomCache
import com.sweetmay.githubclient.model.network.INetworkStatus
import com.sweetmay.githubclient.model.repo.IReposRepo
import com.sweetmay.githubclient.model.repo.IUsersRepo
import com.sweetmay.githubclient.model.repo.retrofit.RetrofitReposRepo
import com.sweetmay.githubclient.model.repo.retrofit.RetrofitUsersRepo
import com.sweetmay.githubclient.model.repo.retrofit.api.IDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun userRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IUsersCache): IUsersRepo{
        return RetrofitUsersRepo(api, networkStatus, cache)
    }

    @Singleton
    @Provides
    fun reposRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IRepoCache): IReposRepo{
        return RetrofitReposRepo(api, networkStatus, cache)
    }


}
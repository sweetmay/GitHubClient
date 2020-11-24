package com.sweetmay.githubclient.model.repo.retrofit

import com.sweetmay.githubclient.model.repo.retrofit.api.IDataSource
import com.sweetmay.githubclient.model.entity.GitHubUser
import com.sweetmay.githubclient.model.entity.UsersRepo
import com.sweetmay.githubclient.model.repo.IReposRepo
import com.sweetmay.githubclient.model.repo.IUsersRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitUsersRepo(private val dataSource: IDataSource): IUsersRepo, IReposRepo{

    override fun getUsers(): Single<List<GitHubUser>> {
        return dataSource.getUsers().subscribeOn(Schedulers.io())
    }

    override fun getRepos(url: String): Single<List<UsersRepo>> {
        return dataSource.getRepos(url).subscribeOn(Schedulers.io())
    }
}
package com.sweetmay.githubclient.model.repo.retrofit.api

import com.sweetmay.githubclient.model.entity.GitHubUser
import com.sweetmay.githubclient.model.entity.RepoFork
import com.sweetmay.githubclient.model.entity.UsersRepo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface IDataSource {
    @GET("/users")
    fun getUsers(): Single<List<GitHubUser>>

    @GET
    fun getRepos(@Url url: String): Single<List<UsersRepo>>

    @GET
    fun getForks(@Url url: String): Single<List<RepoFork>>
}
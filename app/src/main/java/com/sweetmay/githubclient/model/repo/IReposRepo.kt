package com.sweetmay.githubclient.model.repo

import com.sweetmay.githubclient.model.entity.UsersRepo
import io.reactivex.rxjava3.core.Single

interface IReposRepo {
    fun getRepos(url: String): Single<List<UsersRepo>>
}
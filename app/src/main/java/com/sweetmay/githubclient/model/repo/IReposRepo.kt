package com.sweetmay.githubclient.model.repo

import com.sweetmay.githubclient.model.entity.GitHubUser
import com.sweetmay.githubclient.model.entity.UsersRepo
import com.sweetmay.githubclient.model.entity.db.RoomRepo
import io.reactivex.rxjava3.core.Single

interface IReposRepo {
    fun getRepos(gitHubUser: GitHubUser): Single<List<UsersRepo>>
}
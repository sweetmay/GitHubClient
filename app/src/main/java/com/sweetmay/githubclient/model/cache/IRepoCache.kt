package com.sweetmay.githubclient.model.cache

import com.sweetmay.githubclient.model.entity.GitHubUser
import com.sweetmay.githubclient.model.entity.db.RoomRepo
import com.sweetmay.githubclient.model.entity.db.RoomUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IRepoCache {
    fun getRepos(gitHubUser: GitHubUser): Single<List<RoomRepo>>
    fun insertRepos(users: List<RoomRepo>): Completable
}
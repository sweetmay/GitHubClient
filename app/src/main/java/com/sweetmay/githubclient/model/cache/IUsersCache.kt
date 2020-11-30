package com.sweetmay.githubclient.model.cache

import com.sweetmay.githubclient.model.entity.GitHubUser
import com.sweetmay.githubclient.model.entity.db.RoomUser
import dagger.Provides
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IUsersCache {
    fun getUsers(): Single<List<GitHubUser>>
    fun insertUsers(users: List<GitHubUser>): Completable
}
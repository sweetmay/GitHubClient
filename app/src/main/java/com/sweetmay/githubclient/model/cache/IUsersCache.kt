package com.sweetmay.githubclient.model.cache

import com.sweetmay.githubclient.model.entity.db.RoomUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IUsersCache {
    fun getUsers(): Single<List<RoomUser>>
    fun insertUsers(users: List<RoomUser>): Completable
}
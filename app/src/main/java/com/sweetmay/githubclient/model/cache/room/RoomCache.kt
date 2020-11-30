package com.sweetmay.githubclient.model.cache.room

import com.sweetmay.githubclient.model.cache.IRepoCache
import com.sweetmay.githubclient.model.cache.IUsersCache
import com.sweetmay.githubclient.model.entity.GitHubUser
import com.sweetmay.githubclient.model.entity.db.Database
import com.sweetmay.githubclient.model.entity.db.RoomRepo
import com.sweetmay.githubclient.model.entity.db.RoomUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomCache(private val db: Database): IUsersCache, IRepoCache {

    override fun getUsers(): Single<List<RoomUser>> {
        return Single.fromCallable{
            db.getUserDao().getAll()
        }.subscribeOn(Schedulers.io())
    }

    override fun insertUsers(users: List<RoomUser>): Completable {
        return Completable.fromCallable{
            db.getUserDao().insert(users)
        }.subscribeOn(Schedulers.io())
    }

    override fun getRepos(gitHubUser: GitHubUser): Single<List<RoomRepo>> {
        return Single.fromCallable{
            db.getRepoDao().findByUser(gitHubUser.id.toString())
        }.subscribeOn(Schedulers.io())
    }

    override fun insertRepos(repos: List<RoomRepo>): Completable {
        return Completable.fromCallable{
            db.getRepoDao().insert(repos)
        }.subscribeOn(Schedulers.io())
    }
}
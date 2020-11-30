package com.sweetmay.githubclient.model.cache.room

import com.sweetmay.githubclient.model.cache.IRepoCache
import com.sweetmay.githubclient.model.cache.IUsersCache
import com.sweetmay.githubclient.model.entity.GitHubUser
import com.sweetmay.githubclient.model.entity.UsersRepo
import com.sweetmay.githubclient.model.entity.db.Database
import com.sweetmay.githubclient.model.entity.db.RoomRepo
import com.sweetmay.githubclient.model.entity.db.RoomUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomCache(private val db: Database): IUsersCache, IRepoCache {

    override fun getUsers(): Single<List<GitHubUser>> {
        return Single.fromCallable{
            dbEntityToUser(db.getUserDao().getAll())
        }.subscribeOn(Schedulers.io())
    }

    override fun insertUsers(users: List<GitHubUser>): Completable {
        return Completable.fromCallable{
            db.getUserDao().insert(userToDBEntity(users))
        }.subscribeOn(Schedulers.io())
    }

    override fun getRepos(gitHubUser: GitHubUser): Single<List<UsersRepo>> {
        return Single.fromCallable{
            roomRepoToRepo(db.getRepoDao().findByUser(gitHubUser.id.toString()))
        }.subscribeOn(Schedulers.io())
    }


    override fun insertRepos(repos: List<UsersRepo>, user: GitHubUser): Completable {
        return Completable.fromCallable{
            db.getRepoDao().insert(repoToRoom(repos, user))
        }.subscribeOn(Schedulers.io())
    }

    //Converters

    private fun dbEntityToUser(list: List<RoomUser>): List<GitHubUser>{
        val usersArray = arrayListOf<GitHubUser>()
        list.forEach {
            usersArray.add(
                    GitHubUser(
                            it.login,
                            it.id,
                            it.avatar_url,
                            it.repos_url
                    )
            )
        }
        return usersArray;
    }

    private fun userToDBEntity(list: List<GitHubUser>): List<RoomUser>{
        val usersArray = arrayListOf<RoomUser>()
        list.forEach {
            usersArray.add(RoomUser(it.login, it.id, it.avatar_url, it.repos_url))
        }
        return usersArray;
    }

    private fun roomRepoToRepo(list: List<RoomRepo>): List<UsersRepo>{
        val repos = arrayListOf<UsersRepo>()
        list.forEach {
            repos.add(UsersRepo(it.id, it.name, it.forks_url))
        }
        return repos
    }

    private fun repoToRoom(list: List<UsersRepo>, user: GitHubUser): List<RoomRepo>{
        val repos = arrayListOf<RoomRepo>()
        list.forEach {
            repos.add(RoomRepo(it.id, it.name, it.forks_url, user.id.toString()))
        }
        return repos
    }
}
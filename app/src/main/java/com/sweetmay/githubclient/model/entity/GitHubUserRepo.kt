package com.sweetmay.githubclient.model.entity

import io.reactivex.rxjava3.core.Observable


class GitHubUserRepo {
    val users = listOf(
            GitHubUser("user1"),
            GitHubUser("user2"),
            GitHubUser("user3"),
            GitHubUser("user4"),
            GitHubUser("user5")
    )

    fun execJust(): Observable<List<GitHubUser>>{
        return Observable.just(users)
    }
}
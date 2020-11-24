package com.sweetmay.githubclient.model.repo

import com.sweetmay.githubclient.model.entity.GitHubUser
import io.reactivex.rxjava3.core.Single

interface IUsersRepo {
    fun getUsers(): Single<List<GitHubUser>>
}
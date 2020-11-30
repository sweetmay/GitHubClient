package com.sweetmay.githubclient.di

import com.sweetmay.githubclient.di.modules.*
import com.sweetmay.githubclient.model.repo.retrofit.RetrofitReposRepo
import com.sweetmay.githubclient.model.repo.retrofit.RetrofitUsersRepo
import com.sweetmay.githubclient.presenter.ForkPresenter
import com.sweetmay.githubclient.presenter.MainPresenter
import com.sweetmay.githubclient.presenter.RepoPresenter
import com.sweetmay.githubclient.presenter.UsersPresenter
import com.sweetmay.githubclient.view.MainActivity
import dagger.Component
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApiModule::class,
    AppModule::class,
    CacheModule::class,
    CiceroneModule::class,
    RepoModule::class
])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(repoPresenter: RepoPresenter)
    fun inject(forkPresenter: ForkPresenter)
//    fun inject(retrofitUsersRepo: RetrofitUsersRepo)
//    fun inject(retrofitReposRepo: RetrofitReposRepo)
}
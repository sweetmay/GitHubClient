package com.sweetmay.githubclient.presenter

import android.util.Log
import com.sweetmay.App
import com.sweetmay.githubclient.model.repo.retrofit.api.IDataSource
import com.sweetmay.githubclient.view.ForkView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class ForkPresenter(): MvpPresenter<ForkView>() {
    @Inject
    lateinit var dataSource: IDataSource
    @Inject
    lateinit var scheduler: Scheduler

    init {
        App.instance.appComponent.inject(this)
    }

    companion object{
        private val TAG: String = this::class.java.name
    }

    fun loadForks(url: String) {
        dataSource.getForks(url).observeOn(scheduler).subscribe({list ->
            viewState.showForks(list.size.toString())
        }, {err ->
            Log.w(TAG, err.message.toString())
        })
    }
}
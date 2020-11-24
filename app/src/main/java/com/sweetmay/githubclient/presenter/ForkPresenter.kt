package com.sweetmay.githubclient.presenter

import android.util.Log
import com.sweetmay.githubclient.model.repo.retrofit.api.IDataSource
import com.sweetmay.githubclient.view.ForkView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class ForkPresenter(private val dataSource: IDataSource, private val scheduler: Scheduler): MvpPresenter<ForkView>() {

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
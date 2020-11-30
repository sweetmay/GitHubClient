package com.sweetmay.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import com.sweetmay.App
import com.sweetmay.githubclient.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject

class NetworkStatus: INetworkStatus {

    private val TAG: String = this.javaClass.name
    private val statusObject: BehaviorSubject<Boolean> = BehaviorSubject.create()

    init {
        statusObject.onNext(false)
        val connectivityManager = App.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkRequest = NetworkRequest.Builder().build()

        connectivityManager.registerNetworkCallback(networkRequest, object : ConnectivityManager.NetworkCallback(){
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                statusObject.onNext(true)
            }

            override fun onUnavailable() {
                super.onUnavailable()
                statusObject.onNext(false)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                statusObject.onNext(false)
            }
        })
    }

    override fun isOnline(): Observable<Boolean> {
        return statusObject
    }

    override fun isOnlineSingle(): Single<Boolean> {
        return statusObject.first(false)
    }
}
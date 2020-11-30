package com.sweetmay

import android.app.Application
import android.util.Log
import com.sweetmay.githubclient.di.AppComponent
import com.sweetmay.githubclient.di.DaggerAppComponent
import com.sweetmay.githubclient.di.modules.AppModule

class App : Application() {

    companion object{
        lateinit var instance: App
        private set
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }



}
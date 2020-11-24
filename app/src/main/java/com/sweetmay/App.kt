package com.sweetmay

import android.app.Application
import com.sweetmay.githubclient.ApiHolder
import com.sweetmay.githubclient.R
import com.sweetmay.githubclient.model.repo.retrofit.api.IDataSource
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

class App : Application() {

    companion object{
        private lateinit var cicerone: Cicerone<Router>
        private lateinit var apiHolder: ApiHolder
        lateinit var instance: App
        private set
    }

    override fun onCreate() {
        super.onCreate()
        initCicerone()
        apiHolder = ApiHolder(getString(R.string.base_url))
        instance = this
    }

    private fun initCicerone(){
        cicerone = Cicerone.create()
    }

    fun getRouter(): Router{
        return cicerone.router
    }

    fun getNavigationHolder(): NavigatorHolder {
        return cicerone.navigatorHolder
    }

    fun getApi(): IDataSource{
        return apiHolder.dataSource
    }
}
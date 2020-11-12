package com.sweetmay

import android.app.Application
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

class App : Application() {

    companion object{
        private lateinit var cicerone: Cicerone<Router>
        lateinit var instance: App
        private set
    }

    override fun onCreate() {
        super.onCreate()
        initCicerone()
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
}
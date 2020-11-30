package com.sweetmay

import android.app.Application
import androidx.room.Room
import com.sweetmay.utils.ApiHolder
import com.sweetmay.githubclient.R
import com.sweetmay.githubclient.model.entity.db.Database
import com.sweetmay.githubclient.model.repo.retrofit.api.IDataSource
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

class App : Application() {


    companion object{
        private val synchObj = Any()
        private const val DB_NAME = "database.db"
        private lateinit var db: Database
        private lateinit var cicerone: Cicerone<Router>
        private lateinit var apiHolder: ApiHolder
        lateinit var instance: App
        private set
    }

    override fun onCreate() {
        super.onCreate()
        initCicerone()
        initDb()
        apiHolder = ApiHolder(getString(R.string.base_url))
        instance = this
    }

    private fun initDb() {
        db = Room.databaseBuilder(this, Database::class.java, DB_NAME).build()
    }

    fun getDb(): Database{
        synchronized(synchObj){
            return db
        }
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
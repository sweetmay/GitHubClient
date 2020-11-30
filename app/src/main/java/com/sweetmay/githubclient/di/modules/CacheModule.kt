package com.sweetmay.githubclient.di.modules


import android.app.Application
import androidx.room.Room
import com.sweetmay.App
import com.sweetmay.githubclient.R
import com.sweetmay.githubclient.model.cache.IRepoCache
import com.sweetmay.githubclient.model.cache.IUsersCache
import com.sweetmay.githubclient.model.cache.room.RoomCache
import com.sweetmay.githubclient.model.entity.db.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun db(app: App): Database {
        return Room.databaseBuilder(app, Database::class.java, app.getString(R.string.db_name)).build()
    }

    @Singleton
    @Provides
    fun userCache(db: Database): IUsersCache{
        return RoomCache(db)
    }

    @Singleton
    @Provides
    fun repoCache(db: Database): IRepoCache{
        return RoomCache(db)
    }
}
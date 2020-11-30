package com.sweetmay.githubclient.di.modules

import android.app.Application
import com.sweetmay.App
import com.sweetmay.githubclient.R
import com.sweetmay.githubclient.model.network.INetworkStatus
import com.sweetmay.githubclient.model.repo.retrofit.api.IDataSource
import com.sweetmay.utils.ApiHolder
import com.sweetmay.utils.NetworkStatus
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton
import javax.sql.DataSource

@Module
class ApiModule {

    @Named("baseUrl")
    @Provides
    fun baseUrl(app: App): String{
        return app.getString(R.string.base_url)
    }

    @Singleton
    @Provides
    fun dataSource(@Named("baseUrl") url: String): IDataSource {
        return ApiHolder(url).dataSource
    }

    @Singleton
    @Provides
    fun networkStatus(): INetworkStatus{
        return NetworkStatus()
    }

}
package com.sweetmay.utils

import com.sweetmay.githubclient.model.repo.retrofit.api.IDataSource
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiHolder(baseUrl: String) {

    val dataSource: IDataSource

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        dataSource = retrofit.create(IDataSource::class.java)
    }
}
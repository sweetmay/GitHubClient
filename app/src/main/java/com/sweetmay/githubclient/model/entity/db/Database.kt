package com.sweetmay.githubclient.model.entity.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sweetmay.githubclient.model.entity.db.dao.RepoDao
import com.sweetmay.githubclient.model.entity.db.dao.UserDao

@Database(entities = [RoomUser::class, RoomRepo::class], version = 1)

abstract class Database: RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getRepoDao(): RepoDao
}
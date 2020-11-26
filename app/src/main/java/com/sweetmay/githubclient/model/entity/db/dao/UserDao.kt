package com.sweetmay.githubclient.model.entity.db.dao

import androidx.room.*
import com.sweetmay.githubclient.model.entity.db.RoomUser


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomUser>)

    @Update
    fun update(user: RoomUser)

    @Update
    fun update(users: List<RoomUser>)

    @Delete
    fun delete(user: RoomUser)

    @Delete
    fun delete(users: List<RoomUser>)

    @Query("SELECT * FROM RoomUser")
    fun getAll(): List<RoomUser>

    @Query("SELECT * FROM RoomUser WHERE login = :login LIMIT 1")
    fun findByLogin(login: String?): RoomUser
}
package com.sweetmay.githubclient.model.entity.db.dao

import androidx.room.*
import com.sweetmay.githubclient.model.entity.db.RoomRepo


@Dao
interface RepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repo: RoomRepo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repo: List<RoomRepo>)

    @Update
    fun update(repo: RoomRepo)

    @Update
    fun update(repo: List<RoomRepo>)

    @Delete
    fun delete(repo: RoomRepo)

    @Delete
    fun delete(repo: List<RoomRepo>)


    @Query("SELECT * FROM RoomRepo")
    fun getAll(): List<RoomRepo>

    @Query("SELECT * FROM RoomRepo WHERE user_id = :userId")
    fun findByUser(userId: String?): List<RoomRepo>
}
package com.sweetmay.githubclient.model.entity.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(
    entity = RoomUser::class,
    parentColumns = ["id"],
    childColumns = ["user_id"],
    onDelete = ForeignKey.CASCADE
)])
data class RoomRepo(@PrimaryKey val id: String, val name: String, val forks_url: String, val user_id: String)

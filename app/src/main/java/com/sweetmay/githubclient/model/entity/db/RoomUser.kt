package com.sweetmay.githubclient.model.entity.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomUser(val login: String, @PrimaryKey val id: Int, val avatar_url: String, val repos_url: String)

package com.sweetmay.githubclient.model.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class UsersRepo(val id: String, val name: String, val forks_url: String): Parcelable
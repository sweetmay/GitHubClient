package com.sweetmay.githubclient.model.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitHubUser(val login: String) : Parcelable
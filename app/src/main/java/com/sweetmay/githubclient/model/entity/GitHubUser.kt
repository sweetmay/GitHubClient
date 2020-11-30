package com.sweetmay.githubclient.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitHubUser(val login: String, val id: Int, val avatar_url: String, val repos_url: String) : Parcelable
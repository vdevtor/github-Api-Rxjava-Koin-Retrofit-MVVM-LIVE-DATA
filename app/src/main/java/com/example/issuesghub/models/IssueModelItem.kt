package com.example.issuesghub.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.util.*

@Parcelize
data class IssueModelItem(
        val title: String?,
        val body: String?,
        @SerializedName("created_at")
        val createdAt: Date?,
        @SerializedName("html_url")
        val htmlUrl: String?,
        val state: String,
        val url: String?,
        val user: User?
) : Parcelable
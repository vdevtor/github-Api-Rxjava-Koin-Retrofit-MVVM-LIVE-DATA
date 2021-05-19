package com.example.issuesghub.network.api

import com.example.issuesghub.models.IssueModelItem
import io.reactivex.Single
import retrofit2.http.GET

interface GitHubApi {

    @GET("kotlin/issues")
    fun getIssues(): Single<List<IssueModelItem>>
}
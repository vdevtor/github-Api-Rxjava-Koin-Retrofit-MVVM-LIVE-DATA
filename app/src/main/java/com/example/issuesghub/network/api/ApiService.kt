package com.example.issuesghub.network.api

import com.example.issuesghub.models.IssueModelItem
import io.reactivex.Single
import org.koin.core.KoinComponent
import org.koin.core.get

object ApiService : KoinComponent {
    val gitHubBuilder = get<ApiBuilder>()
    val gitHubApi = gitHubBuilder.getGitHubApiClient()

    fun getIssues(): Single<List<IssueModelItem>> {
        return gitHubApi.getIssues()
    }
}
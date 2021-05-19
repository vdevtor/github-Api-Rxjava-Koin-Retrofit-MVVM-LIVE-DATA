package com.example.issuesghub.network.api

import androidx.appcompat.app.AppCompatActivity
import com.example.issuesghub.models.IssueModelItem
import io.reactivex.Single
import org.koin.android.ext.android.get

object ApiService : AppCompatActivity() {
    val gitHubBuilder = get<ApiBuilder>()
    val gitHubApi = gitHubBuilder.getGitHubApiClient()

    fun getIssues(): Single<List<IssueModelItem>> {
        return gitHubApi.getIssues()
    }
}
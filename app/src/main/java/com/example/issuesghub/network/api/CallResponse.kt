package com.example.issuesghub.network.api

import com.example.issuesghub.models.IssueModelItem
import io.reactivex.Single

class CallResponse() {

    fun responseCallIssue(): Single<List<IssueModelItem>> {
        return apiServiceReturnIssue()
    }

    private fun apiServiceReturnIssue() = ApiService.gitHubApi.getIssues()
}
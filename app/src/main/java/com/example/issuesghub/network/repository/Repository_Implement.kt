package com.example.issuesghub.network.repository

import com.example.issuesghub.models.IssueModelItem
import com.example.issuesghub.network.api.CallResponse
import io.reactivex.Single
import org.koin.core.KoinComponent
import org.koin.core.get

class Repository_Implement(): Repository,KoinComponent {

    override fun getIssues(): Single<List<IssueModelItem>> {
        val callResponse = get<CallResponse>()
        return callResponse.responseCallIssue()
    }
}
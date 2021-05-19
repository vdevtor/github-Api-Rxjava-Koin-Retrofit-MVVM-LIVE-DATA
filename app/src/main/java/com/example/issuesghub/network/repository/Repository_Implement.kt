package com.example.issuesghub.network.repository

import androidx.appcompat.app.AppCompatActivity
import com.example.issuesghub.models.IssueModelItem
import com.example.issuesghub.network.api.CallResponse
import io.reactivex.Single
import org.koin.android.ext.android.get

class Repository_Implement(): Repository,AppCompatActivity() {

    override fun getIssues(): Single<List<IssueModelItem>> {
        val callResponse = get<CallResponse>()
        return callResponse.responseCallIssue()
    }
}
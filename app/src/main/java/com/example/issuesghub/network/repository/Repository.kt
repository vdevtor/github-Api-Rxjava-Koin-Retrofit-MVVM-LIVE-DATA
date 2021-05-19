package com.example.issuesghub.network.repository

import com.example.issuesghub.models.IssueModelItem
import io.reactivex.Single

interface Repository {
    fun getIssues() : Single<List<IssueModelItem>>
}
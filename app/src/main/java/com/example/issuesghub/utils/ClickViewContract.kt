package com.example.issuesghub.utils

import com.example.issuesghub.models.IssueModelItem

interface ClickViewContract {
    fun onClick(result : IssueModelItem)
}
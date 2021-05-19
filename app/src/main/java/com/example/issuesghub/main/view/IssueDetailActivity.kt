package com.example.issuesghub.main.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.issuesghub.databinding.ActivityIssueDetailBinding
import com.example.issuesghub.models.IssueModelItem
import com.example.issuesghub.utils.formatDate
import com.example.issuesghub.utils.load

class IssueDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIssueDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIssueDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val issue = intent.getParcelableExtra<IssueModelItem>("result")

        binding.apply {
            issueTitleDetail.text = issue?.title
            issueBody.text = issue?.body
            issue?.user?.avatarUrl?.let { avatarUser.load(it) }
            val date = formatDate()

            if (issue != null) {
                createdAt.text = date.format(issue.createdAt).toString()
            }

            linkBtn.setOnClickListener {
                issue?.htmlUrl?.let { it1 -> goToUrl(it1) }
            }
        }

    }

    fun goToUrl(s: String) {
        val uri = Uri.parse(s)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }
}
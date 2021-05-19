package com.example.issuesghub.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.issuesghub.databinding.ItemIssueBinding
import com.example.issuesghub.models.IssueModelItem
import com.example.issuesghub.utils.ClickViewContract

class IssueAdapter(
        private var issueList: List<IssueModelItem>,
        private val listener: ClickViewContract
) : RecyclerView.Adapter<IssueAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemIssueBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(issue: IssueModelItem) = with(binding) {
            issueTitle.text = issue.title
            statusIssue.text = issue.state
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemIssueBinding.inflate(layoutInflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val issue = issueList[position]
        holder.bind(issue)
        holder.itemView.setOnClickListener {
            listener.onClick(issue)
        }
    }

    override fun getItemCount() = issueList.size
}
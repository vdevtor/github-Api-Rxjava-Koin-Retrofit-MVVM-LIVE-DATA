package com.example.issuesghub.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.issuesghub.databinding.ActivityMainBinding
import com.example.issuesghub.main.adapter.IssueAdapter
import com.example.issuesghub.main.viewmodels.MainViewModel
import com.example.issuesghub.models.IssueModelItem
import com.example.issuesghub.utils.ClickViewContract
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), ClickViewContract {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getIssues()
        setupObservables()
    }

    fun setupObservables() {
        viewModel.onResultListOfIssues.observe(this, { list ->
            if (list != null) {
                setupRecyclerView(list)
            }
        })

        viewModel.issueLoadError.observe(this,{isError ->
            isError?.let {
                binding.recyclerView.visibility = if (!it) View.VISIBLE else View.GONE
                binding.listError.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
    }

    fun setupRecyclerView(list: List<IssueModelItem>) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = IssueAdapter(list, this@MainActivity)
        }
    }

    override fun onClick(result: IssueModelItem) {
        val intent = Intent(this, IssueDetailActivity::class.java)
        intent.putExtra("result", result)
        startActivity(intent)
    }
}
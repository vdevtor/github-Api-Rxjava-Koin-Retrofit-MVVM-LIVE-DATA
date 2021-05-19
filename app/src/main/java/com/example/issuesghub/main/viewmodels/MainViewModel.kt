package com.example.issuesghub.main.viewmodels

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.issuesghub.models.IssueModelItem
import com.example.issuesghub.network.repository.Repository_Implement
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainViewModel(application: Application) : AndroidViewModel(application), KoinComponent {

    private val disposable = CompositeDisposable()
    var onResultListOfIssues = MutableLiveData<List<IssueModelItem>?>()
    val issueLoadError = MutableLiveData<Boolean>()
    private val repository: Repository_Implement by inject()

    fun getIssues() {
        fetchIssues(getApplication())
    }

    private fun fetchIssues(context: Context) {
        disposable.add(
                repository.getIssues()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<List<IssueModelItem>>() {
                            override fun onSuccess(value: List<IssueModelItem>?) {
                                onResultListOfIssues.value = value
                                issueLoadError.value = false

                            }

                            override fun onError(e: Throwable?) {
                                issueLoadError.value = true
                                Toast.makeText(context, e?.message, Toast.LENGTH_SHORT).show()
                            }
                        })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}
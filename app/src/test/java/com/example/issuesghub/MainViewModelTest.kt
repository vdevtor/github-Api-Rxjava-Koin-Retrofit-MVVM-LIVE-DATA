package com.example.issuesghub

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.issuesghub.di.homeModule
import com.example.issuesghub.main.viewmodels.MainViewModel
import com.example.issuesghub.models.IssueModelItem
import com.example.issuesghub.models.User
import com.example.issuesghub.network.api.ApiService
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import junit.framework.Assert.assertEquals
import junitparams.JUnitParamsRunner
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.mockito.InjectMocks
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.*
import java.util.concurrent.Callable
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

@RunWith(JUnitParamsRunner::class)
class MainViewModelTest : KoinTest{

    val viewModel by inject<MainViewModel>()
    val apiService  = Mockito.mock(ApiService::class.java)

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
       androidLogger(Level.ERROR)
        modules(homeModule)
    }

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }


    @InjectMocks
    var user = User("www.souurl.coom", "www.sououtraurl.com",
            "55", "www.gihthub.com", 88, "www.meurepositorio",
            "active", "ultimaUrl")

    private var testSingle: Single<List<IssueModelItem>>? = null

    @Test
    fun getIssueSucess() {
        val issue = IssueModelItem("issueName", "um problemão aqui", Date(),
                "www.umaurlaqui", "open", "www.outraurlaqui", user)


        val issueList = arrayListOf(issue)
        testSingle = Single.just(issueList)

        `when`(apiService.gitHubApi.getIssues()).thenReturn(testSingle)
        viewModel.getIssues()
        assertEquals(1, viewModel.onResultListOfIssues.value?.size)
        assertEquals(false, viewModel.issueLoadError.value)
    }

    @Test
    fun getIssueFail() {
        testSingle = Single.error(Throwable())
        `when`(apiService.gitHubApi.getIssues()).thenReturn(testSingle)
        viewModel.getIssues()
        assertEquals(true, viewModel.issueLoadError.value)
    }


    @Before
    fun setUp() {

        MockitoAnnotations.openMocks(this)
    }

    @Before
    fun setUpRxSchedulers() {
        val immediate = object : Scheduler() {
            override fun schedulePeriodicallyDirect(run: Runnable?, initialDelay: Long, period: Long, unit: TimeUnit?): Disposable {
                return super.schedulePeriodicallyDirect(run, 0, period, unit)
            }

            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
            }
        }
        RxJavaPlugins.setInitIoSchedulerHandler { scheduler: Callable<Scheduler> -> immediate }
        RxJavaPlugins.setInitIoSchedulerHandler { immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }

    }

}
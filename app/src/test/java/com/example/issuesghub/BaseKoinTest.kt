package com.example.issuesghub

import org.junit.Rule
import org.koin.test.KoinTestRule

abstract class BaseKoinTest {
        @get:Rule
        val koinTestRule =  KoinTestRule
}
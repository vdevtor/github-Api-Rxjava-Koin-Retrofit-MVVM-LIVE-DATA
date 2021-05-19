package com.example.issuesghub.utils

import java.text.SimpleDateFormat
import java.util.*

fun formatDate() : SimpleDateFormat{
    return SimpleDateFormat("MMM dd, yyyy", Locale.US)
}
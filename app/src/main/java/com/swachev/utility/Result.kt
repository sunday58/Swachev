package com.swachev.utility

class Result<out T>(
    val status: State,
    val message: String? = null,
    val data: T? = null,
    val error: Throwable? = null,
    val isRefreshing: Boolean? = null
)
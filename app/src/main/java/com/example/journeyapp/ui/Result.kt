package com.example.journeyapp.ui

sealed class Result<out T : Any> {
    data class Success<T : Any>(val result: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

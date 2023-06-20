package com.codewithteju.findapp.common

/*
    Usage : A generic class to handle the network response which contains the data and the potential error message
 */
sealed class ResourceResponse<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : ResourceResponse<T>(data)
    class Error<T>(message: String, data: T? = null) : ResourceResponse<T>(data, message)
    class Loading<T>(data: T? = null) : ResourceResponse<T>(data)
}

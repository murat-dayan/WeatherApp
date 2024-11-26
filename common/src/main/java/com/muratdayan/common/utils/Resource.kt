package com.muratdayan.common.utils


// bu sınıf api'den dönen verileri temsil edecek
// api'den dönen veri ya success ya error ya da loading durumunda olcak
sealed class Resource<T>(val data: T? = null, val msg: String? = null) {
    class Idle<T> : Resource<T>()
    class Success<T>(data: T?) : Resource<T>(data = data)
    class Error<T>(msg: String) : Resource<T>(msg = msg)
    class Loading<T> : Resource<T>()
}
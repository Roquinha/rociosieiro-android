package com.example.applicationrociosieiro.data.functions

import com.example.applicationrociosieiro.common.entities.ErrorEntity
import com.example.base.common.Either
import retrofit2.Response

fun <T> handleRetrofitResponseFunc(r: Response<T>) = if (r.isSuccessful) {
    val b = r.body()
    if (b != null) {
        Either.Value(b)
    } else {
        Either.Error(ErrorEntity.BodyNull)
    }
} else {
    Either.Error(ErrorEntity.Service(r.code()))
}
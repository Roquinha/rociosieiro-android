package com.example.base.common

sealed class Either<out E, out V> {
    data class Error<out E>(val error: E) : Either<E, Nothing>()
    data class Value<out V>(val value: V) : Either<Nothing, V>()

    inline fun <A> fold(e: (E) -> A, v: (V) -> A): A = when (this) {
        is Error -> e(error)
        is Value -> v(value)
    }
}

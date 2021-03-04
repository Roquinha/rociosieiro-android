package com.example.applicationrociosieiro.common.entities

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize


@Keep
sealed class ErrorEntity : Parcelable {

    @Parcelize
    object Unknown : ErrorEntity()

    @Parcelize
    object BodyNull : ErrorEntity()

    @Parcelize
    data class Service(val code: Int) : ErrorEntity()

    @Parcelize
    object EmptyResult : ErrorEntity()

}
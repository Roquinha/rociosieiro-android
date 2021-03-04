package com.example.applicationrociosieiro.common.entities.artists

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
class ImageEntity(
    @SerializedName("height") val height: Int? = -1,
    @SerializedName("url") val url: String? = "",
    @SerializedName("width") val width: Int? = -1): Parcelable
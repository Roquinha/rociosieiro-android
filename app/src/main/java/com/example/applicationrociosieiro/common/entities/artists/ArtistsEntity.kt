package com.example.applicationrociosieiro.common.entities.artists

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
class ArtistsEntity (
        @SerializedName("href") val href: String? = "",
        @SerializedName("items") val artistItems: List<ArtistItemEntity>? = listOf(),
        @SerializedName("limit") val limit: Int? = -1,
        @SerializedName("next") val next: String? = "",
        @SerializedName("offset") val offset: Int? = -1,
        @SerializedName("previous") val previous: String? = "",
        @SerializedName("total") val total: Int? = -1
): Parcelable
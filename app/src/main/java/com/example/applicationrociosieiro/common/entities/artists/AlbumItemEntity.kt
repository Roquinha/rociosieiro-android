package com.example.applicationrociosieiro.common.entities.artists

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
class AlbumItemEntity(
        @SerializedName("album_group") val albumGroup: String? = "",
        @SerializedName("images") val images: List<ImageEntity>? = listOf(),
        @SerializedName("name") val name: String? = "",
        @SerializedName("release_date") val releaseDate: String? = "",
        @SerializedName("uri") val uri: String? = "",
        var zoom: Int = 2
): Parcelable
package com.example.applicationrociosieiro.common.entities.artists

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class AlbumEntity (
        @SerializedName("items") val albumItems: List<AlbumItemEntity>? = listOf()
): Parcelable
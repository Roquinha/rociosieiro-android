package com.example.applicationrociosieiro.common.entities.artists

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
class SearchResult(@SerializedName("artists") val artists: ArtistsEntity? = null): Parcelable



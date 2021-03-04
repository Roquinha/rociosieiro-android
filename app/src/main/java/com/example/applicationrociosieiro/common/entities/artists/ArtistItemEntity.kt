package com.example.applicationrociosieiro.common.entities.artists

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
class ArtistItemEntity(
    @SerializedName("genres") val genres: List<String>? = listOf(),
    @SerializedName("href") val href: String? = "",
    @SerializedName("id") val id: String? = "",
    @SerializedName("images") val images: List<ImageEntity>? = listOf(),
    @SerializedName("name") val name: String? = "",
    @SerializedName("popularity") val popularity: Int? = -1,
    @SerializedName("type") val type: String? = "",
    @SerializedName("uri") val uri: String? = ""
): Parcelable
package com.example.applicationrociosieiro.presentation.search.artistdetail

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
sealed class ArtistDetailViewEntry: Parcelable {
    @Parcelize
    data class ArtistEntry(val id: String = ""): ArtistDetailViewEntry()
}
package com.ramces.ricknmortyapp.data.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class CharacterApi(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: @RawValue DataApi,
    val location: @RawValue DataApi,
    val image: String,
    val episode: ArrayList<String>
) : Parcelable {}

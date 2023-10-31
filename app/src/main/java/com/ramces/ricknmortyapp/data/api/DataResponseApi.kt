package com.ramces.ricknmortyapp.data.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class DataResponseApi(
    val info: @RawValue InfoApi,
    val results: ArrayList<CharacterApi>
) : Parcelable

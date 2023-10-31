package com.ramces.ricknmortyapp.data.api

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class DataResponseApi(
    val info: @RawValue InfoApi,
    val results: ArrayList<CharacterApi>
) : Parcelable

package com.ramces.ricknmortyapp.data.api

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataApi(
    val name: String,
    val url: String
) : Parcelable

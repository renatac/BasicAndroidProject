package com.example.basicandroidproject.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Language(var number: Int?, var name: String?) : Parcelable
package com.example.midterm.second_task

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CardData(
    val amount: Double,
    val fullName: String,
    val cvv: String,
    val expiry: String
): Parcelable
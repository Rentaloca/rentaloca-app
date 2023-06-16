package com.rentaloca.rentalocaapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DressModel (
    val photo: Int,
    val dressname: String,
    val price: String,
    val days: String,
    val description: String,
    val bodyshape: String,
) : Parcelable

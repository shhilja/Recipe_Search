package com.example.recipesearch.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HealthLabels(
    var healthLabelName: String
):Parcelable

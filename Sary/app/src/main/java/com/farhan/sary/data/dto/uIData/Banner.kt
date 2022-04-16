package com.farhan.sary.data.dto.uIData


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = false)
@Parcelize
data class Banner(
        @Json(name = "id")
        val id: Int = 0,
        @Json(name = "title")
        val title: String = "",
        @Json(name = "description")
        val description: String = "",
        @Json(name = "button_text")
        val button_text: String = "",
        @Json(name = "expiry_status")
        val expiry_status: Boolean = false,
        @Json(name = "created_at")
        val created_at: String = "",
        @Json(name = "start_date")
        val start_date: String = "",
        @Json(name = "expiry_date")
        val expiry_date: String = "",
        @Json(name = "image")
        val image: String = "",
        @Json(name = "priority")
        val priority: Int = 0,
        @Json(name = "photo")
        val photo: String = "",
        @Json(name = "link")
        val link: String = "",
        @Json(name = "level")
        val level: String = "",
        @Json(name = "is_available")
        val is_available: Boolean = false,
        @Json(name = "branch")
        val branch: Int = 0
) : Parcelable

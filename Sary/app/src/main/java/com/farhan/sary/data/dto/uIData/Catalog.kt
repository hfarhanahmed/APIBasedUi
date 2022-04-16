package com.farhan.sary.data.dto.uIData


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Catalog(
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "title")
    val title: String = "",
    @Json(name = "data")
    val data: List<Data> = listOf(),
    @Json(name = "data_type")
    val data_type: String = "",
    @Json(name = "show_title")
    val show_title: Boolean = false,
    @Json(name = "ui_type")
    val ui_type: String = "",
    @Json(name = "row_count")
    val row_count: Int = 0,
) : Parcelable

@Parcelize
data class Data(
    @Json(name = "group_id")
    val group_id: Int = 0,
//    @Json(name = "filters")
//    val filters: List<Filter> = listOf(),
    @Json(name = "name")
    val name: String = "",
    @Json(name = "image")
    val image: String = "",
    @Json(name = "empty_content_image")
    val empty_content_image: String = "",
    @Json(name = "empty_content_message")
    val empty_content_message: String = "",
    @Json(name = "has_data")
    val has_data: Boolean = false,
    @Json(name = "show_unavailable_items")
    val show_unavailable_items: Boolean = false,
    @Json(name = "show_in_brochure_link")
    val show_in_brochure_link: Boolean = false
): Parcelable

//@Parcelize
//data class Filter(
//    @Json(name = "filter_id")
//    val filter_id: Int = 0,
//    @Json(name = "name")
//    val name: String = "",
//): Parcelable
package com.farhan.sary.data.dto.uIData

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Catalogs(@Json(name= "result")
                   val result: List<Catalog>,
                    @Json(name= "other")
                    val other: OtherCatalog,
                    @Json(name= "message")
                    val message: String,
                    @Json(name= "status")
                    val status: Boolean
                   )

data class OtherCatalog(
    @Json(name = "show_special_order_view")
    val show_special_order_view: Boolean,
    @Json(name = "uncompleted_profile_settings")
    val uncompleted_profile_settings: UncompletedProfileSetting,
    @Json(name = "business_status")
    val business_status: BusinessStatus,
    )

data class UncompletedProfileSetting(
    @Json(name= "show_tag")
    val show_tag: Boolean,
    @Json(name= "message")
    val message: String,
    @Json(name= "image")
    val image: String,
    @Json(name= "is_completed_profile")
    val is_completed_profile: Boolean,
)

data class BusinessStatus(
    @Json(name= "id")
    val id: Int,
    @Json(name= "title")
    val title: String,
)
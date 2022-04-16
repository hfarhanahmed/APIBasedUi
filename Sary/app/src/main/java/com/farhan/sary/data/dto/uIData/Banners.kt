package com.farhan.sary.data.dto.uIData

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Banners(@Json(name= "result")
                   val result: List<Banner>,
                   @Json(name= "status")
                   val status: Boolean
                   )

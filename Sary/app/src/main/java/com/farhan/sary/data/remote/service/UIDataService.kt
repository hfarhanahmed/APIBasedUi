package com.farhan.sary.data.remote.service

import com.farhan.sary.data.dto.uIData.Banners
import com.farhan.sary.data.dto.uIData.Catalogs
import retrofit2.Response
import retrofit2.http.GET



interface UIDataService {
    @GET("v2.5.1/baskets/325514/banners/")
    suspend fun fetchBanner(): Response<Banners>

    @GET("baskets/325514/catalog/")
    suspend fun fetchCatalog(): Response<Catalogs>
}

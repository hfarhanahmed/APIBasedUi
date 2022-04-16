package com.farhan.sary.data.remote

import com.farhan.sary.data.Resource
import com.farhan.sary.data.dto.uIData.Banners
import com.farhan.sary.data.dto.uIData.Catalogs


internal interface RemoteDataSource {
    suspend fun requestBanners(): Resource<Banners>
    suspend fun requestCatalogs(): Resource<Catalogs>
}

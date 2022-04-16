package com.farhan.sary.data

import com.farhan.sary.data.dto.uIData.Banners
import com.farhan.sary.data.dto.uIData.Catalogs
import kotlinx.coroutines.flow.Flow



interface DataRepositorySource {
    suspend fun requestBanners(): Flow<Resource<Banners>>
    suspend fun requestCatalogs(): Flow<Resource<Catalogs>>
}

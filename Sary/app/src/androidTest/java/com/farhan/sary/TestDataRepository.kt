package com.farhan.sary

import com.farhan.sary.TestUtil.dataStatus
import com.farhan.sary.TestUtil.initData
import com.farhan.sary.data.DataRepositorySource
import com.farhan.sary.data.Resource
import com.farhan.sary.data.dto.uIData.*
import com.farhan.sary.data.error.NETWORK_ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject




class TestDataRepository @Inject constructor() : DataRepositorySource {

    override suspend fun requestBanners(): Flow<Resource<Banners>> {
        return when (dataStatus) {
            DataStatus.Success -> {
                flow { emit(Resource.Success(initData())) }
            }
            DataStatus.Fail -> {
                flow { emit(Resource.DataError<Banners>(errorCode = NETWORK_ERROR)) }
            }
            DataStatus.EmptyResponse -> {
                flow { emit(Resource.Success(Banners(arrayListOf(),true))) }
            }
        }
    }

    override suspend fun requestCatalogs(): Flow<Resource<Catalogs>> {
        return when (dataStatus) {
            DataStatus.Success -> {
                flow { emit(Resource.Success(initData())) }
            }
            DataStatus.Fail -> {
                flow { emit(Resource.DataError<Catalogs>(errorCode = NETWORK_ERROR)) }
            }
            DataStatus.EmptyResponse -> {
                flow { emit(Resource.Success(Catalogs(arrayListOf(), OtherCatalog(false, UncompletedProfileSetting(false,"","",false), BusinessStatus(0,"")),"",true))) }
            }
        }
    }


}

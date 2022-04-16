package com.farhan.sary.data

import com.farhan.sary.data.dto.uIData.Banners
import com.farhan.sary.data.dto.uIData.Catalogs
import com.farhan.sary.data.remote.RemoteData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext




class DataRepository @Inject constructor(private val remoteRepository: RemoteData, private val ioDispatcher: CoroutineContext) : DataRepositorySource {

    override suspend fun requestBanners(): Flow<Resource<Banners>> {
        return flow {
            emit(remoteRepository.requestBanners())
        }.flowOn(ioDispatcher)
    }

    override suspend fun requestCatalogs(): Flow<Resource<Catalogs>> {
        return flow {
            emit(remoteRepository.requestCatalogs())
        }.flowOn(ioDispatcher)
    }
}

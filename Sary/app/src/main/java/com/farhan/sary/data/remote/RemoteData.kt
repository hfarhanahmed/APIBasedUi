package com.farhan.sary.data.remote

import com.farhan.sary.data.Resource
import com.farhan.sary.data.dto.uIData.Banners
import com.farhan.sary.data.dto.uIData.Banner
import com.farhan.sary.data.dto.uIData.Catalogs
import com.farhan.sary.data.error.NETWORK_ERROR
import com.farhan.sary.data.error.NO_INTERNET_CONNECTION
import com.farhan.sary.data.remote.service.UIDataService
import com.farhan.sary.utils.NetworkConnectivity
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject




class RemoteData @Inject
constructor(private val serviceGenerator: ServiceGenerator, private val networkConnectivity: NetworkConnectivity) : RemoteDataSource {
    override suspend fun requestBanners(): Resource<Banners> {
        val uiDataService = serviceGenerator.createService(UIDataService::class.java)
        return when (val response = processCall(uiDataService::fetchBanner)) {
            is Banners -> {
                Resource.Success(data = response)
            }
            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }

    override suspend fun requestCatalogs(): Resource<Catalogs> {
        val uiDataService = serviceGenerator.createService(UIDataService::class.java)
        return when (val response = processCall(uiDataService::fetchCatalog)) {
            is Catalogs -> {
                Resource.Success(data = response)
            }
            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }

    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!networkConnectivity.isConnected()) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }
}

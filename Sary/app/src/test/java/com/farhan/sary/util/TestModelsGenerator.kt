package com.farhan.sary.util

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.farhan.sary.data.dto.uIData.Banner
import com.farhan.sary.data.dto.uIData.Banners
import com.farhan.sary.data.remote.moshiFactories.MyKotlinJsonAdapterFactory
import com.farhan.sary.data.remote.moshiFactories.MyStandardJsonAdapters
import java.io.File
import java.lang.reflect.Type




class TestModelsGenerator {
    private var banners: Banners = Banners(listOf(),true)

    init {
        val moshi = Moshi.Builder()
                .add(MyKotlinJsonAdapterFactory())
                .add(MyStandardJsonAdapters.FACTORY)
                .build()
        val type: Type = Types.newParameterizedType(List::class.java, Banner::class.java)
        val adapter: JsonAdapter<List<Banner>> = moshi.adapter(type)
        val jsonString = getJson("RecipesApiResponse.json")
        adapter.fromJson(jsonString)?.let {
            banners = Banners(it,true)
        }
        print("this is $banners")
    }

    fun generateBanners(): Banners {
        return banners
    }

    fun generateBannersModelWithEmptyList(): Banners {
        return Banners(arrayListOf(),true)
    }

    fun generateBannersItemModel(): Banner {
        return banners.result[0]
    }


    /**
     * Helper function which will load JSON from
     * the path specified
     *
     * @param path : Path of JSON file
     * @return json : JSON from file at given path
     */

    private fun getJson(path: String): String {
        // Load the JSON response
        val uri = this.javaClass.classLoader?.getResource(path)
        val file = File(uri?.path)
        return String(file.readBytes())
    }
}

package com.farhan.sary

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.farhan.sary.data.dto.uIData.Banner
import com.farhan.sary.data.dto.uIData.Banners
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.farhan.sary.data.remote.moshiFactories.MyKotlinJsonAdapterFactory
import com.farhan.sary.data.remote.moshiFactories.MyStandardJsonAdapters
import java.io.InputStream
import java.lang.reflect.Type



object TestUtil {
    var dataStatus: DataStatus = DataStatus.Success
    var recipes: Banners = Banners(arrayListOf(),true)
    fun initData(): Banners {
        val moshi = Moshi.Builder()
                .add(MyKotlinJsonAdapterFactory())
                .add(MyStandardJsonAdapters.FACTORY)
                .build()
        val type: Type = Types.newParameterizedType(List::class.java, Banner::class.java)
        val adapter: JsonAdapter<List<Banner>> = moshi.adapter(type)
        val jsonString = getJson("RecipesApiResponse.json")
        adapter.fromJson(jsonString)?.let {
            recipes = Banners(ArrayList(it),true)
            return recipes
        }
        return Banners(arrayListOf(),true)
    }

    private fun getJson(path: String): String {
        val ctx: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val inputStream: InputStream = ctx.classLoader.getResourceAsStream(path)
        return inputStream.bufferedReader().use { it.readText() }
    }
}

package com.farhan.sary.ui.component.mainActivity.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.farhan.sary.data.dto.uIData.Data
import com.farhan.sary.databinding.CatalogGroupBinding



class CatalogGroupBannerAdapter(private val catalogData: List<Data>) : RecyclerView.Adapter<CatalogGroupBannerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogGroupBannerViewHolder {
        val itemBinding = CatalogGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatalogGroupBannerViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holderBanner: CatalogGroupBannerViewHolder, position: Int) {
        holderBanner.bind(catalogData[position])
    }

    override fun getItemCount(): Int {
        return catalogData.size
    }
}


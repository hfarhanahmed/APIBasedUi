package com.farhan.sary.ui.component.mainActivity.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.farhan.sary.data.dto.uIData.Data
import com.farhan.sary.databinding.CatalogSmartBinding


class CatalogSmartAdapter(private val catalogData: List<Data>) : RecyclerView.Adapter<CatalogSmartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogSmartViewHolder {
        val itemBinding = CatalogSmartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatalogSmartViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CatalogSmartViewHolder, position: Int) {
        holder.bind(catalogData[position])
    }

    override fun getItemCount(): Int {
        return catalogData.size
    }
}


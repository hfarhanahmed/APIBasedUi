package com.farhan.sary.ui.component.mainActivity.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.farhan.sary.data.dto.uIData.Data
import com.farhan.sary.databinding.CatalogSmartBinding


class CatalogSmartViewHolder(private val itemBinding: CatalogSmartBinding) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(dataItem: Data) {
        if(dataItem.image.isNotEmpty())
            Picasso.get().load(dataItem.image).into(itemBinding.ivCatalogGroupItemImage)
        itemBinding.name.text = dataItem.name
    }
}


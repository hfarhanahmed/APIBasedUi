package com.farhan.sary.ui.component.mainActivity.home

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.farhan.sary.data.Resource
import com.farhan.sary.data.dto.uIData.Banners
import com.farhan.sary.data.dto.uIData.Catalog
import com.farhan.sary.data.dto.uIData.Catalogs
import com.farhan.sary.databinding.FragmentHomeBinding
import com.farhan.sary.ui.base.BaseFragment
import com.farhan.sary.ui.component.mainActivity.home.adapter.CatalogGroupBannerAdapter
import com.farhan.sary.ui.component.mainActivity.home.adapter.CatalogSmartAdapter
import com.farhan.sary.utils.*
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@AndroidEntryPoint
@FragmentScoped
class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null

    private val homeViewModel: HomeViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        observeViewModel()
        homeViewModel.getBanners()
        homeViewModel.getCatalogs()
        return root
    }

    private fun observeSnackBarMessages(event: LiveData<SingleEvent<Any>>) {
        binding.root.setupSnackbar(this, event, Snackbar.LENGTH_LONG)
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }



    private fun bindSliderData(banners: Banners) {
        if (!(banners.result.isNullOrEmpty())) {
            val imageList = ArrayList<SlideModel>()
            banners.result.forEach { banner ->
                Log.d("images",banner.image)
                imageList.add(SlideModel(imageUrl = banner.image))
            }
            binding.imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP)
            binding.imageSlider.setItemClickListener(object  : ItemClickListener {
                override fun onItemSelected(position: Int) {
                    Toast.makeText(context, banners.result[position].link,Toast.LENGTH_LONG).show()
                }
            })
            showDataView(true)
        } else {
            showDataView(false)
        }
    }

    private fun handleBannersList(status: Resource<Banners>) {
        when (status) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> status.data?.let { bindSliderData(banners = it) }
            is Resource.DataError -> {
                showDataView(false)
                status.errorCode?.let { homeViewModel.showToastMessage(it) }
            }
        }
    }

    private fun bindCatalogData(catalogs: Catalogs) {
        if (!(catalogs.result.isNullOrEmpty())) {
            catalogs.result.forEach { catalog ->
                context?.let {
                    val recyclerView = getRecyclerView(it, catalog)

                    if(catalog.show_title){
                        val titleText = TextView(it)
                        titleText.text = catalog.title
                        val params  = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT)
                        params.topMargin = 16
                        titleText.layoutParams = params
                        titleText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
                        titleText.setTextColor(Color.BLACK)
                        binding.listLayout.addView(titleText)
                    }

                    binding.listLayout.addView(recyclerView)
                }
            }
            showDataView(true)
        } else {
            showDataView(false)
        }
    }

    private fun getRecyclerView(context: Context, catalog: Catalog): RecyclerView {
        val recyclerView    =   RecyclerView(context)
        val params = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
        recyclerView.layoutParams = params
        val gridLM = GridLayoutManager(context, catalog.row_count)
        recyclerView.layoutManager = gridLM

        if(catalog.data_type == "smart"){
            recyclerView.adapter = CatalogSmartAdapter(catalog.data)
        }else{
            recyclerView.adapter = CatalogGroupBannerAdapter(catalog.data)
        }

        return recyclerView
    }

    private fun handleCatalogsList(status: Resource<Catalogs>) {
        when (status) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> status.data?.let { bindCatalogData(catalogs = it) }
            is Resource.DataError -> {
                showDataView(false)
                status.errorCode?.let { homeViewModel.showToastMessage(it) }
            }
        }
    }

    fun observeViewModel() {
        observe(homeViewModel.bannersLiveDataPrivate, ::handleBannersList)
        observe(homeViewModel.catalogsLiveDataPrivate, ::handleCatalogsList)
        observeSnackBarMessages(homeViewModel.showSnackBar)
        observeToast(homeViewModel.showToast)
    }

    private fun showLoadingView() {
        binding.pbLoading.toVisible()
        binding.tvNoData.toGone()
        binding.rootView.toGone()
    }

    private fun showDataView(show: Boolean) {
        binding.tvNoData.visibility = if (show) View.GONE else View.VISIBLE
        binding.rootView.visibility = if (show) View.VISIBLE else View.GONE
        binding.pbLoading.toGone()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
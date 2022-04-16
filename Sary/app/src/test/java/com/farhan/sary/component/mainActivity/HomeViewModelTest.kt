package com.farhan.sary.ui.component.mainActivity.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.farhan.sary.data.DataRepository
import com.farhan.sary.data.Resource
import com.farhan.sary.data.dto.uIData.Banners
import com.farhan.sary.data.error.NETWORK_ERROR
import com.farhan.sary.util.InstantExecutorExtension
import com.farhan.sary.util.MainCoroutineRule
import com.farhan.sary.util.TestModelsGenerator
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
class HomeViewModelTest {
    // Subject under test
    private lateinit var homeViewModel: HomeViewModel

    // Use a fake UseCase to be injected into the viewModel
    private val dataRepository: DataRepository = mockk()

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var recipeTitle: String
    private val testModelsGenerator: TestModelsGenerator = TestModelsGenerator()

    @Test
    fun `get Banners List`() {
        // Let's do an answer for the liveData
        val bannersModel = testModelsGenerator.generateBanners()

        //1- Mock calls
        coEvery { dataRepository.requestBanners() } returns flow {
            emit(Resource.Success(bannersModel))
        }

        //2-Call
        homeViewModel = HomeViewModel(dataRepository)
        homeViewModel.getBanners()
        //active observer for livedata
        homeViewModel.bannersLiveDataPrivate.observeForever { }

        //3-verify
        val isEmptyList = homeViewModel.bannersLiveDataPrivate.value?.data?.result.isNullOrEmpty()
        assertEquals(bannersModel, homeViewModel.bannersLiveDataPrivate.value?.data)
        assertEquals(false,isEmptyList)
    }

    @Test
    fun `get Banners Empty List`() {
        // Let's do an answer for the liveData
        val recipesModel = testModelsGenerator.generateBannersModelWithEmptyList()

        //1- Mock calls
        coEvery { dataRepository.requestBanners() } returns flow {
            emit(Resource.Success(recipesModel))
        }

        //2-Call
        homeViewModel = HomeViewModel(dataRepository)
        homeViewModel.getBanners()
        //active observer for livedata
        homeViewModel.bannersLiveDataPrivate.observeForever { }

        //3-verify
        val isEmptyList = homeViewModel.bannersLiveDataPrivate.value?.data?.result.isNullOrEmpty()
        assertEquals(recipesModel, homeViewModel.bannersLiveDataPrivate.value?.data)
        assertEquals(true, isEmptyList)
    }

    @Test
    fun `get Banners Error`() {
        // Let's do an answer for the liveData
        val error: Resource<Banners> = Resource.DataError(NETWORK_ERROR)

        //1- Mock calls
        coEvery { dataRepository.requestBanners() } returns flow {
            emit(error)
        }

        //2-Call
        homeViewModel = HomeViewModel(dataRepository)
        homeViewModel.getBanners()
        //active observer for livedata
        homeViewModel.bannersLiveDataPrivate.observeForever { }

        //3-verify
        assertEquals(NETWORK_ERROR, homeViewModel.bannersLiveDataPrivate.value?.errorCode)
    }
}

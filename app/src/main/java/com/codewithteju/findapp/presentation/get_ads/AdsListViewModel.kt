package com.codewithteju.findapp.presentation.get_ads

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithteju.findapp.common.ResourceResponse
import com.codewithteju.findapp.domain.model.Advertisement
import com.codewithteju.findapp.domain.use_case.FavoriteUseCases
import com.codewithteju.findapp.domain.use_case.GetAdsUseCase
import com.codewithteju.findapp.presentation.favorites.FavoriteAdsEvent
import com.codewithteju.findapp.presentation.favorites.FavoritesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdsListViewModel @Inject constructor(
    private val getAdsUseCase: GetAdsUseCase,
    private val favoriteUseCases: FavoriteUseCases
) : ViewModel() {

    private val _adsListState = mutableStateOf(AdsListState())
    val adsListState = _adsListState

    private val _favoritesState = mutableStateOf(FavoritesState())
    val favoritesState = _favoritesState

    private var getFavoritesJob: Job? = null

    init {
        getAds()
        getAllFavorites()
    }


    private fun getAds() {
        getAdsUseCase().onEach { result ->
            when (result) {
                is ResourceResponse.Success -> {
                    _adsListState.value = AdsListState(ads = result.data ?: emptyList())
                }

                is ResourceResponse.Error -> {
                    _adsListState.value = AdsListState(
                        error = result.message ?: "Unexpected Error!!"
                    )
                }

                is ResourceResponse.Loading -> {
                    _adsListState.value = AdsListState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }


    fun onEvent(event: FavoriteAdsEvent) {
        when (event) {
            is FavoriteAdsEvent.AddFavoriteAd -> {
                viewModelScope.launch {
                    favoriteUseCases.insertAd(event.adv)
                }
            }

            is FavoriteAdsEvent.DeleteFavoriteAd -> {
                viewModelScope.launch {
                    favoriteUseCases.deleteAd(event.adv)
                }
            }
        }
    }

    private fun getAllFavorites() {
        getFavoritesJob?.cancel()
        getFavoritesJob = favoriteUseCases.getAds()
            .onEach { advertisements ->
                _favoritesState.value = favoritesState.value.copy(favoriteAds = advertisements)
                Log.i("TAG", "FAV LIST from DB ${_favoritesState.value.favoriteAds.size}")
                Log.i("TAG", "FAV LIST from DB ${advertisements.size}")
                //_favoritesState.value = FavoritesState(favoriteAds = advertisements)
                //_favoritesState.value = color.value
            }
            .launchIn(viewModelScope)
    }

    fun isOrNot(advertisement: Advertisement): Boolean {
        return _favoritesState.value.favoriteAds.contains(advertisement)
    }

    fun updateOneItem(adv: Advertisement, newVal:Boolean){
        _adsListState.value.ads.map {
            if(it.id == adv.id) {
                it.isFavorite = newVal

            }
        }
    }

}
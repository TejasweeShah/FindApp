package com.codewithteju.findapp.presentation.favorites

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithteju.findapp.domain.use_case.FavoriteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoriteUseCases: FavoriteUseCases
) : ViewModel() {

    // Tha state will contain the values our UI will observe
    private val _favoritesState = mutableStateOf(FavoritesState())
    val favoritesState = _favoritesState

    private val _color = MutableStateFlow(false)
    val color = _color.asStateFlow()


    private var getFavoritesJob: Job? = null

    init {
        getAllFavorites()
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
            }
            .launchIn(viewModelScope)
    }

}
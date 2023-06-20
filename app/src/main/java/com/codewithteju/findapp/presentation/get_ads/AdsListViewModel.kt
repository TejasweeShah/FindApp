package com.codewithteju.findapp.presentation.get_ads

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithteju.findapp.common.ResourceResponse
import com.codewithteju.findapp.domain.use_case.GetAdsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AdsListViewModel @Inject constructor(
    private val getAdsUseCase: GetAdsUseCase
) : ViewModel() {
    private val _adsListState = mutableStateOf(AdsListState())
    val adsListState = _adsListState

    init {
        getAds()
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
}
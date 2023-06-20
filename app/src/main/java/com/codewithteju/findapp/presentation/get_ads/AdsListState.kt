package com.codewithteju.findapp.presentation.get_ads

import com.codewithteju.findapp.domain.model.Advertisement


data class AdsListState(
    val isLoading: Boolean = false,
    val ads: List<Advertisement> = emptyList(),
    val error: String = ""
)

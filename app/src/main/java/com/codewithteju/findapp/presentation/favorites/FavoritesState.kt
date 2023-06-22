package com.codewithteju.findapp.presentation.favorites

import com.codewithteju.findapp.domain.model.Advertisement

data class FavoritesState(
    val favoriteAds: List<Advertisement> = emptyList(),
    val error: String = "",
    val isFavorite: Boolean = false,
    val isLoading: Boolean = false,
)
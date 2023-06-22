package com.codewithteju.findapp.domain.use_case

import com.codewithteju.findapp.domain.model.Advertisement
import com.codewithteju.findapp.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow

class GetFavoritesUseCase(
    private val favoriteRepository: FavoriteRepository
) {
    operator fun invoke(): Flow<List<Advertisement>> {
        return favoriteRepository.getAllAds()
    }
}
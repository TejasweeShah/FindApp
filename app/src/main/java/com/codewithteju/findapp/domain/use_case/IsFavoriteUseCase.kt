package com.codewithteju.findapp.domain.use_case

import com.codewithteju.findapp.domain.repository.FavoriteRepository

class IsFavoriteUseCase(
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(advId: Int): Boolean {
        return favoriteRepository.isFavorite(advId)
    }
}

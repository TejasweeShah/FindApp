package com.codewithteju.findapp.domain.use_case

import com.codewithteju.findapp.domain.model.Advertisement
import com.codewithteju.findapp.domain.repository.FavoriteRepository

class DeleteFavoriteAdUseCase(
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(advertisement: Advertisement) {
        favoriteRepository.deleteAd(advertisement)
    }

}
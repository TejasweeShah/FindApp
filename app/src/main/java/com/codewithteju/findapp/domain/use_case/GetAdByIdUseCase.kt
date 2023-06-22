package com.codewithteju.findapp.domain.use_case

import com.codewithteju.findapp.domain.model.Advertisement
import com.codewithteju.findapp.domain.repository.FavoriteRepository

class GetAdByIdUseCase(
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(id: Int): Advertisement? {
        return favoriteRepository.getAdvById(id)
    }
}
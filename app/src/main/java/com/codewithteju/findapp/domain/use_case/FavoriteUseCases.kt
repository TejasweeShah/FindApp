package com.codewithteju.findapp.domain.use_case

data class FavoriteUseCases(
    val getAds: GetFavoritesUseCase,
    val deleteAd: DeleteFavoriteAdUseCase,
    val insertAd: AddFavoriteAdUseCase
)

package com.codewithteju.findapp.presentation.favorites

import com.codewithteju.findapp.domain.model.Advertisement

sealed class FavoriteAdsEvent {
    data class AddFavoriteAd(val adv: Advertisement) : FavoriteAdsEvent()
    data class DeleteFavoriteAd(val adv: Advertisement) : FavoriteAdsEvent()
    data class CheckIsFavorite(val id: Int) : FavoriteAdsEvent()
    data class GetAdvById(val advId: Int) : FavoriteAdsEvent()
}

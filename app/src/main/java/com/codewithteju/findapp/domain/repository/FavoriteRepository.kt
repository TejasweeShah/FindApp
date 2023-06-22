package com.codewithteju.findapp.domain.repository

import com.codewithteju.findapp.domain.model.Advertisement
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    fun getAllAds(): Flow<List<Advertisement>>

    suspend fun insertAd(advertisement: Advertisement)

    suspend fun deleteAd(advertisement: Advertisement)

    suspend fun isFavorite(id: Int): Boolean

    suspend fun getAdvById(id: Int): Advertisement?
}
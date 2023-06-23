package com.codewithteju.findapp.data.repository

import com.codewithteju.findapp.data.data_source.FindAppDao
import com.codewithteju.findapp.domain.model.Advertisement
import com.codewithteju.findapp.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow

class FavoriteRepositoryImpl(
    private val findAppDao: FindAppDao
) : FavoriteRepository {

    override fun getAllAds(): Flow<List<Advertisement>> {
        return findAppDao.getAllAds()
    }

    override suspend fun insertAd(advertisement: Advertisement) {
        findAppDao.insertAd(adv = advertisement)
    }

    override suspend fun deleteAd(advertisement: Advertisement) {
        findAppDao.deleteAd(advertisement)
    }

}
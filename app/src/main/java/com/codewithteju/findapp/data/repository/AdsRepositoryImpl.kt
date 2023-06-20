package com.codewithteju.findapp.data.repository

import com.codewithteju.findapp.data.remote.FindAppApi
import com.codewithteju.findapp.data.remote.dto.AdsDto
import com.codewithteju.findapp.domain.repository.AdsRepository
import javax.inject.Inject

class AdsRepositoryImpl @Inject constructor(
    private val findAppApi: FindAppApi
) : AdsRepository {

    override suspend fun getAds(): AdsDto {
        return findAppApi.getFinnAds()
    }
}
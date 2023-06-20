package com.codewithteju.findapp.domain.repository

import com.codewithteju.findapp.data.remote.dto.AdsDto

interface AdsRepository {

    suspend fun getAds(): AdsDto
}
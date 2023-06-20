package com.codewithteju.findapp.data.remote

import com.codewithteju.findapp.data.remote.dto.AdsDto
import retrofit2.http.GET

interface FindAppApi {

    @GET("discover.json")
    suspend fun getFinnAds(): AdsDto
}
package com.codewithteju.findapp.di

import com.codewithteju.findapp.common.Constants
import com.codewithteju.findapp.data.remote.FindAppApi
import com.codewithteju.findapp.data.repository.AdsRepositoryImpl
import com.codewithteju.findapp.domain.repository.AdsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideFindAppApi(): FindAppApi {
        return Retrofit.Builder()
            .baseUrl(Constants.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FindAppApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAdsRepository(findAppApi: FindAppApi): AdsRepository {
        return AdsRepositoryImpl(findAppApi)
    }
}
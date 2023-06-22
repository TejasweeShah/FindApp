package com.codewithteju.findapp.di

import android.app.Application
import androidx.room.Room
import com.codewithteju.findapp.data.data_source.FindAppDatabase
import com.codewithteju.findapp.data.repository.FavoriteRepositoryImpl
import com.codewithteju.findapp.domain.repository.FavoriteRepository
import com.codewithteju.findapp.domain.use_case.AddFavoriteAdUseCase
import com.codewithteju.findapp.domain.use_case.DeleteFavoriteAdUseCase
import com.codewithteju.findapp.domain.use_case.FavoriteUseCases
import com.codewithteju.findapp.domain.use_case.GetAdByIdUseCase
import com.codewithteju.findapp.domain.use_case.GetFavoritesUseCase
import com.codewithteju.findapp.domain.use_case.IsFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideFindAppDatabase(appContext: Application): FindAppDatabase {
        return Room.databaseBuilder(
            appContext,
            FindAppDatabase::class.java,
            FindAppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoritesRepository(findAppDb: FindAppDatabase): FavoriteRepository {
        return FavoriteRepositoryImpl(findAppDb.findAppDao)
    }

    @Provides
    @Singleton
    fun provideFavoriteUseCases(favoriteRepository: FavoriteRepository): FavoriteUseCases {
        return FavoriteUseCases(
            getAds = GetFavoritesUseCase(favoriteRepository),
            deleteAd = DeleteFavoriteAdUseCase(favoriteRepository),
            insertAd = AddFavoriteAdUseCase(favoriteRepository),
            checkFavorite = IsFavoriteUseCase(favoriteRepository),
            getAdByIdUseCase = GetAdByIdUseCase(favoriteRepository)
        )
    }

}
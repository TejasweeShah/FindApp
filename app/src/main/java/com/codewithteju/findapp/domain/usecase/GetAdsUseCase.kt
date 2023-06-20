package com.codewithteju.findapp.domain.usecase

import com.codewithteju.findapp.common.ResourceResponse
import com.codewithteju.findapp.data.remote.dto.toAdvertisement
import com.codewithteju.findapp.domain.model.Advertisement
import com.codewithteju.findapp.domain.repository.AdsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAdsUseCase @Inject constructor(
    private val adsRepository: AdsRepository
) {
    operator fun invoke(): Flow<ResourceResponse<List<Advertisement>>> = flow {
        try {
            emit(ResourceResponse.Loading())
            val ads = adsRepository.getAds().items.map { it.toAdvertisement() }
            emit(ResourceResponse.Success(ads))
        } catch (exp: HttpException) {
            emit(ResourceResponse.Error(exp.localizedMessage ?: "An Unexpected Error Occurred!"))
        } catch (exp: IOException) {
            emit(ResourceResponse.Error("Could reach server. Check Internet Connection"))
        }
    }
}
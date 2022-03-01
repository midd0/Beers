package com.beer.data

import com.beer.data.common.RetrofitModule
import com.beer.data.mapper.BeerMapper
import com.beer.data.remote.api.PunkApiService
import com.beer.data.repository.PunkRepositoyImpl
import com.beer.domain.PunkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [RetrofitModule::class])
@InstallIn(SingletonComponent::class)
class PunkServiceProvider {

    @Singleton
    @Provides
    fun providePunkApi(retrofit: Retrofit): PunkApiService {
        return retrofit.create(PunkApiService::class.java)
    }

    @Singleton
    @Provides
    fun providePunkRepository(apiService: PunkApiService, mapper: BeerMapper): PunkRepository {
        return PunkRepositoyImpl(apiService, mapper)
    }
}
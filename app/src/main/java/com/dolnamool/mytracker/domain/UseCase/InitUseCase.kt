package com.dolnamool.mytracker.domain.UseCase

import com.dolnamool.mytracker.data.model.Address
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@InstallIn(SingletonComponent::class)
@Module
class InitUseCase @Inject constructor(private val initDataRepository: InitDataRepository) {

    suspend fun getInitData(): List<Address> {
        return initDataRepository
    }
}
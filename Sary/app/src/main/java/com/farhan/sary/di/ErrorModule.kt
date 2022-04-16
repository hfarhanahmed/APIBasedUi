package com.farhan.sary.di

import com.farhan.sary.data.error.mapper.ErrorMapper
import com.farhan.sary.data.error.mapper.ErrorMapperSource
import com.farhan.sary.usecases.errors.ErrorUseCase
import com.farhan.sary.usecases.errors.ErrorManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// with @Module we Telling Dagger that, this is a Dagger module
@Module
@InstallIn(SingletonComponent::class)
abstract class ErrorModule {
    @Binds
    @Singleton
    abstract fun provideErrorFactoryImpl(errorManager: ErrorManager): ErrorUseCase

    @Binds
    @Singleton
    abstract fun provideErrorMapper(errorMapper: ErrorMapper): ErrorMapperSource
}

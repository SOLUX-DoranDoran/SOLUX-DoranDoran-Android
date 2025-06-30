package com.solux.dorandoran.app.di


import com.solux.dorandoran.data.repositoryimpl.ExampleRepositoryImpl
import com.solux.dorandoran.domain.repository.ExampleRepository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindExampleRepository(exampleRepositoryImpl: ExampleRepositoryImpl): ExampleRepository

}
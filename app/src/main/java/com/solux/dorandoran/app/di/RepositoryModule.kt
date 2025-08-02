package com.solux.dorandoran.app.di


import com.solux.dorandoran.data.repositoryimpl.ArgumentRepositoryImpl
import com.solux.dorandoran.data.repositoryimpl.BookRepositoryImpl
import com.solux.dorandoran.data.repositoryimpl.DiscussRepositoryImpl
import com.solux.dorandoran.data.repositoryimpl.ExampleRepositoryImpl
import com.solux.dorandoran.domain.repository.ArgumentRepository
import com.solux.dorandoran.domain.repository.BookRepository
import com.solux.dorandoran.domain.repository.DiscussRepository
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

    @Binds
    @Singleton
    abstract fun bindDiscussRepository(discussRepositoryImpl: DiscussRepositoryImpl): DiscussRepository

    @Binds
    @Singleton
    abstract fun bindArgumentRepository(argumentRepositoryImpl: ArgumentRepositoryImpl): ArgumentRepository

    @Binds
    @Singleton
    abstract fun bindBookRepository(bookRepositoryImpl: BookRepositoryImpl): BookRepository

}
package com.solux.dorandoran.app.di

import com.solux.dorandoran.data.datasource.ArgumentDataSource
import com.solux.dorandoran.data.datasource.BookDataSource
import com.solux.dorandoran.data.datasource.DiscussDataSource
import com.solux.dorandoran.data.datasource.ExampleDataSource
import com.solux.dorandoran.data.datasourceimpl.BookDataSourceImpl
import com.solux.dorandoran.data.datasourceimpl.ArgumentDataSourceImpl
import com.solux.dorandoran.data.datasourceimpl.DiscussDataSourceImpl
import com.solux.dorandoran.data.datasourceimpl.ExampleDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindExampleDataSource(exampleDataSourceImpl: ExampleDataSourceImpl): ExampleDataSource

    @Binds
    @Singleton
    abstract fun bindDiscussDataSource(discussDataSourceImpl: DiscussDataSourceImpl): DiscussDataSource

    @Binds
    @Singleton
    abstract fun bindArgumentDataSource(argumentDataSourceImpl: ArgumentDataSourceImpl): ArgumentDataSource

    @Binds
    @Singleton
    abstract fun bindBookDataSource(bookDataSourceImpl: BookDataSourceImpl): BookDataSource


}
package com.solux.dorandoran.app.di

import com.solux.dorandoran.data.datasource.BookDataSource
import com.solux.dorandoran.data.datasource.ExampleDataSource
import com.solux.dorandoran.data.datasource.QuoteDataSource
import com.solux.dorandoran.data.datasource.RecommendedBookDataSource
import com.solux.dorandoran.data.datasource.ReviewDataSource
import com.solux.dorandoran.data.datasourceimpl.BookDataSourceImpl
import com.solux.dorandoran.data.datasourceimpl.ExampleDataSourceImpl
import com.solux.dorandoran.data.datasourceimpl.QuoteDataSourceImpl
import com.solux.dorandoran.data.datasourceimpl.RecommendedBookDataSourceImpl
import com.solux.dorandoran.data.datasourceimpl.ReviewDataSourceImpl
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
    abstract fun bindBookDataSource(bookDataSourceImpl: BookDataSourceImpl): BookDataSource

    @Binds
    @Singleton
    abstract fun bindRecommendedBookDataSource(recommendedBookDataSourceImpl: RecommendedBookDataSourceImpl)
    : RecommendedBookDataSource

    @Binds
    @Singleton
    abstract fun bindQuoteDataSource(quoteDataSourceImpl: QuoteDataSourceImpl): QuoteDataSource

    @Binds
    @Singleton
    abstract fun bindReviewDataSource(reviewDataSourceImpl: ReviewDataSourceImpl): ReviewDataSource
}
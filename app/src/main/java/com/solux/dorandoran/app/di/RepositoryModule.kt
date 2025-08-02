package com.solux.dorandoran.app.di


import com.solux.dorandoran.data.repositoryimpl.BookRepositoryImpl
import com.solux.dorandoran.data.repositoryimpl.DiscussionRepositoryImpl
import com.solux.dorandoran.data.repositoryimpl.ExampleRepositoryImpl
import com.solux.dorandoran.data.repositoryimpl.QuoteRepositoryImpl
import com.solux.dorandoran.data.repositoryimpl.RecommendedBookRepositoryImpl
import com.solux.dorandoran.data.repositoryimpl.ReviewRepositoryImpl
import com.solux.dorandoran.domain.repository.BookRepository
import com.solux.dorandoran.domain.repository.DiscussionRepository
import com.solux.dorandoran.domain.repository.ExampleRepository
import com.solux.dorandoran.domain.repository.QuoteRepository
import com.solux.dorandoran.domain.repository.RecommendedBookRepository
import com.solux.dorandoran.domain.repository.ReviewRepository

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
    abstract fun bindBookRepository(bookRepositoryImpl: BookRepositoryImpl): BookRepository

    @Binds
    @Singleton
    abstract fun bindRecommendedBookRepository(recommendedBookRepositoryImpl: RecommendedBookRepositoryImpl):
            RecommendedBookRepository

    @Binds
    @Singleton
    abstract fun bindQuoteRepository(quoteRepositoryImpl: QuoteRepositoryImpl): QuoteRepository

    @Binds
    @Singleton
    abstract fun bindReviewRepository(reviewRepositoryImpl: ReviewRepositoryImpl): ReviewRepository

    @Binds
    @Singleton
    abstract fun bindDiscussionRepository(discussionRepositoryImpl: DiscussionRepositoryImpl): DiscussionRepository
}
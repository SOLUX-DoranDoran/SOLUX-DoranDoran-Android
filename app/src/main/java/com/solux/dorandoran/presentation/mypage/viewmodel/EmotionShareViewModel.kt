package com.solux.dorandoran.presentation.mypage.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solux.dorandoran.domain.entity.QuoteEntity
import com.solux.dorandoran.domain.entity.QuoteLikeEntity
import com.solux.dorandoran.domain.repository.QuoteRepository
import com.solux.dorandoran.domain.repository.QuoteLikeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmotionShareViewModel @Inject constructor(
    private val quoteRepository: QuoteRepository,
    private val quoteLikeRepository: QuoteLikeRepository
) : ViewModel() {

    private val _quoteList = mutableStateOf<List<QuoteEntity>>(emptyList())
    val quoteList: State<List<QuoteEntity>> = _quoteList

    private val _quoteLikeMap = mutableStateOf<Map<Long, QuoteLikeEntity>>(emptyMap())
    val quoteLikeMap: State<Map<Long, QuoteLikeEntity>> = _quoteLikeMap

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    init {
        fetchQuotes()
    }

    fun fetchQuotes(
        sort: String = "recent",
        page: Int = 1,
        size: Int = 10
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            val result = quoteRepository.getQuotes(sort, page, size)

            result.onSuccess { quotes ->
                _quoteList.value = quotes
                initializeQuoteLikes(quotes)
            }.onFailure { exception ->
                _errorMessage.value = exception.message ?: "명언을 불러오는데 실패했습니다."
            }

            _isLoading.value = false
        }
    }

    fun postQuote(content: String, bookId: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            val result = quoteRepository.postQuote(content, bookId)

            result.onSuccess { quoteId ->
                // 성공 시 목록 새로고침
                fetchQuotes()
            }.onFailure { exception ->
                _errorMessage.value = exception.message ?: "명언 등록에 실패했습니다."
            }

            _isLoading.value = false
        }
    }

    fun toggleLike(quoteId: Long) {
        viewModelScope.launch {
            _errorMessage.value = null

            val result = quoteLikeRepository.toggleQuoteLike(quoteId)

            result.onSuccess { quoteLikeEntity ->
                // 서버 응답으로 로컬 상태 업데이트
                val currentMap = _quoteLikeMap.value.toMutableMap()
                currentMap[quoteId] = quoteLikeEntity
                _quoteLikeMap.value = currentMap
            }.onFailure { exception ->
                _errorMessage.value = exception.message ?: "좋아요 처리에 실패했습니다."
            }
        }
    }

    private fun initializeQuoteLikes(quotes: List<QuoteEntity>) {
        val likeMap = mutableMapOf<Long, QuoteLikeEntity>()
        quotes.forEach { quote ->
            likeMap[quote.id] = QuoteLikeEntity(
                success = true,
                message = "",
                likeCount = 0 // 기본값: 좋아요 0개
            )
        }
        _quoteLikeMap.value = likeMap
    }

    fun getQuoteLike(quoteId: Long): QuoteLikeEntity {
        return _quoteLikeMap.value[quoteId] ?: QuoteLikeEntity(
            success = true,
            message = "",
            likeCount = 0
        )
    }

    fun refreshQuotes() {
        fetchQuotes()
    }

    fun clearErrorMessage() {
        _errorMessage.value = null
    }
}
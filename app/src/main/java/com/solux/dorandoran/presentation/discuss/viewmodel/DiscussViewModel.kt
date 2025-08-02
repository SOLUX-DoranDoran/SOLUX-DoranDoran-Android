package com.solux.dorandoran.presentation.discuss.viewmodel

import androidx.lifecycle.ViewModel
import com.solux.dorandoran.domain.entity.DiscussPageEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.viewModelScope
import com.solux.dorandoran.domain.repository.DiscussRepository
import kotlinx.coroutines.launch


@HiltViewModel
class DiscussViewModel @Inject constructor(
    private val discussRepository: DiscussRepository
) : ViewModel() {

    private val _discussions = mutableStateOf<List<DiscussPageEntity>>(emptyList())
    val discussions: State<List<DiscussPageEntity>> = _discussions

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    private val _selectedDiscussion = mutableStateOf<DiscussPageEntity?>(null)
    val selectedDiscussion: State<DiscussPageEntity?> = _selectedDiscussion

    private val _bookDiscussions = mutableStateOf<List<DiscussPageEntity>>(emptyList())
    val bookDiscussions: State<List<DiscussPageEntity>> = _bookDiscussions

    private val _selectedTabIndex = mutableIntStateOf(0) // 0: 토론 목록, 1: 토론 생성
    val selectedTabIndex: State<Int> = _selectedTabIndex

    private val _discussionTopic = mutableStateOf("")
    val discussionTopic: State<String> = _discussionTopic

    private val _argumentContent = mutableStateOf("")
    val argumentContent: State<String> = _argumentContent

    private val _selectedBookTitle = mutableStateOf<String?>(null)
    val selectedBookTitle: State<String?> = _selectedBookTitle


    private val pageSize = 10

    init {
        loadDiscussions()
    }

    fun loadDiscussions() {
        if (_isLoading.value) return // 이미 로딩 중이면 중복 호출 방지

        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            discussRepository.getDiscussions(
                page = 0,
                size = pageSize
            ).fold(
                onSuccess = { discussions ->
                        _discussions.value = discussions
                            },
                onFailure = { exception ->
                    _errorMessage.value = exception.message ?: "토론 목록을 불러오는데 실패했습니다."
                }
            )

            _isLoading.value = false
        }
    }

    fun refreshDiscussions() {
        loadDiscussions()
    }

    fun onDiscussionClicked(discussion: DiscussPageEntity) {
        _selectedDiscussion.value = discussion
    }

    fun selectDiscussion(discussion: DiscussPageEntity) {
        _selectedDiscussion.value = discussion
        loadDiscussionsForBook(discussion.bookTitle)
    }

    fun loadDiscussionsForBook(bookTitle: String) {
        viewModelScope.launch {
            _isLoading.value = true

            // 전체 토론에서 해당 책의 토론들만 필터링
            val filteredDiscussions = _discussions.value.filter {
                it.bookTitle == bookTitle
            }
            _bookDiscussions.value = filteredDiscussions

            _isLoading.value = false
        }
    }

    fun updateSelectedTab(index: Int) {
        _selectedTabIndex.intValue = index
    }

    fun onDiscussionTopicChange(newTopic: String) {
        _discussionTopic.value = newTopic
    }

    fun onArgumentContentChange(newContent: String) {
        _argumentContent.value = newContent
    }

    fun selectBookForDiscussion(bookTitle: String) {
        _selectedBookTitle.value = bookTitle
    }

    fun createDiscussion() {
        val bookId = _selectedBookTitle.value ?: return
        val topic = _discussionTopic.value
        val content = _argumentContent.value

        if (topic.isBlank() || content.isBlank()) {
            _errorMessage.value = "토론 주제와 내용을 모두 입력해주세요."
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            // 실제 API 호출
            try {
                discussRepository.createDiscussion(bookId, topic, content)
                .fold(
                    onSuccess = {
                        clearDiscussionForm()
                        loadDiscussions()
                        _selectedTabIndex.intValue = 0 // 목록 탭으로 이동
                    },
                    onFailure = { exception ->
                        _errorMessage.value = exception.message ?: "토론 생성에 실패했습니다."
                     }
                )

            } catch (e: Exception) {
                _errorMessage.value = "토론 생성에 실패했습니다."
            }

            _isLoading.value = false
        }
    }

    private fun clearDiscussionForm() {
        _discussionTopic.value = ""
        _argumentContent.value = ""
        _selectedBookTitle.value = null
    }

    fun getDiscussionById(discussionId: Int): DiscussPageEntity? {
        return _discussions.value.find { it.id == discussionId }
    }

    fun clearError() {
        _errorMessage.value = null
    }
}
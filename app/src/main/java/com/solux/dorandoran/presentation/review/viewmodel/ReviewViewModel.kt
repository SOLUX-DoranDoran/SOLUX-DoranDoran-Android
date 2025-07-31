package com.solux.dorandoran.presentation.review.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solux.dorandoran.domain.entity.BookEntity
import com.solux.dorandoran.domain.entity.BookReviewsResponseEntity
import com.solux.dorandoran.domain.entity.ReviewCommentEntity
import com.solux.dorandoran.domain.entity.ReviewDetailEntity
import com.solux.dorandoran.domain.repository.BookRepository
import com.solux.dorandoran.domain.repository.ReviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val bookRepository: BookRepository,
    private val reviewRepository: ReviewRepository
) : ViewModel() {

    // 탭 선택 상태
    private val _selectedTabIndex = mutableIntStateOf(0)
    val selectedTabIndex: State<Int> = _selectedTabIndex

    // 현재 선택된 책 정보
    private val _currentBook = mutableStateOf<BookEntity?>(null)
    val currentBook: State<BookEntity?> = _currentBook

    // 해당 도서의 리뷰 목록
    private val _bookReviews = mutableStateOf<BookReviewsResponseEntity?>(null)
    val bookReviews: State<BookReviewsResponseEntity?> = _bookReviews

    // 리뷰 상세 정보 (좋아요, 댓글 포함)
    private val _reviewDetails = mutableStateOf<Map<Long, ReviewDetailEntity>>(emptyMap())
    val reviewDetails: State<Map<Long, ReviewDetailEntity>> = _reviewDetails

    // 새 리뷰 작성
    private val _newReviewRating = mutableIntStateOf(0)
    val newReviewRating: State<Int> = _newReviewRating

    private val _newReviewContent = mutableStateOf("")
    val newReviewContent: State<String> = _newReviewContent

    // 댓글 입력
    private val _commentInputText = mutableStateOf("")
    val commentInputText: State<String> = _commentInputText

    private val _selectedReviewForComment = mutableStateOf<Long?>(null)
    val selectedReviewForComment: State<Long?> = _selectedReviewForComment

    // 로딩 상태
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    // 현재 bookId
    private var currentBookId: Long = 1L

    fun initializeWithBookId(bookId: Long) {
        currentBookId = bookId
        loadBookInfo(bookId)
        loadBookReviews(bookId)
    }

    private fun loadBookInfo(bookId: Long) {
        viewModelScope.launch {
            bookRepository.getBookInfo(bookId)
                .onSuccess { book ->
                    _currentBook.value = book
                }
                .onFailure { error ->
                    _errorMessage.value = error.message ?: "책 정보를 불러오는데 실패했습니다."
                }
        }
    }

    private fun loadBookReviews(bookId: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            reviewRepository.getBookReviews(bookId = bookId)
                .onSuccess { bookReviewsResponse ->
                    _bookReviews.value = bookReviewsResponse

                    // 각 리뷰에 대한 상세 정보 초기화
                    val reviewDetailsMap = bookReviewsResponse.reviews.associate { review ->
                        review.id to ReviewDetailEntity(
                            id = review.id,
                            bookTitle = review.bookTitle,
                            coverImageUrl = review.coverImageUrl,
                            content = review.content,
                            rating = review.rating,
                            createdAt = review.createdAt,
                            nickname = review.nickname,
                            profileImage = review.profileImage,
                            isLiked = false, // 초기값
                            likeCount = 0, // 초기값
                            commentCount = 0, // 초기값
                            comments = emptyList(),
                            isCommentsVisible = false
                        )
                    }
                    _reviewDetails.value = reviewDetailsMap

                    // 각 리뷰의 댓글 수 로드
                    loadCommentsForReviews(bookReviewsResponse.reviews.map { it.id })
                }
                .onFailure { error ->
                    _errorMessage.value = error.message ?: "리뷰를 불러오는데 실패했습니다."
                }

            _isLoading.value = false
        }
    }

    private fun loadCommentsForReviews(reviewIds: List<Long>) {
        reviewIds.forEach { reviewId ->
            loadReviewComments(reviewId)
        }
    }

    private fun loadReviewComments(reviewId: Long) {
        viewModelScope.launch {
            reviewRepository.getReviewComments(reviewId = reviewId)
                .onSuccess { commentsResponse ->
                    val currentDetail = _reviewDetails.value[reviewId] ?: return@onSuccess
                    val updatedDetail = currentDetail.copy(
                        commentCount = commentsResponse.totalComments,
                        comments = commentsResponse.comments
                    )
                    _reviewDetails.value = _reviewDetails.value + (reviewId to updatedDetail)
                }
                .onFailure { error ->
                    // 댓글 로드 실패는 조용히 처리 (필수가 아니므로)
                }
        }
    }

    // 탭 전환
    fun updateSelectedTab(index: Int) {
        _selectedTabIndex.intValue = index
    }

    // 새 리뷰 작성 관련
    fun updateNewReviewRating(rating: Int) {
        _newReviewRating.intValue = rating
    }

    fun updateNewReviewContent(content: String) {
        _newReviewContent.value = content
    }

    fun submitReview() {
        if (_newReviewRating.intValue > 0 && _newReviewContent.value.isNotBlank()) {
            viewModelScope.launch {
                _isLoading.value = true

                reviewRepository.createReview(
                    bookId = currentBookId,
                    content = _newReviewContent.value,
                    rating = _newReviewRating.intValue
                )
                    .onSuccess { response ->
                        // 리뷰 작성 성공 - 목록 새로고침
                        loadBookReviews(currentBookId)

                        // 폼 초기화
                        _newReviewRating.intValue = 0
                        _newReviewContent.value = ""
                        _selectedTabIndex.intValue = 0 // 사용자 평 탭으로 이동
                    }
                    .onFailure { error ->
                        _errorMessage.value = error.message ?: "리뷰 작성에 실패했습니다."
                    }

                _isLoading.value = false
            }
        }
    }

    // 좋아요 토글
    fun toggleLike(reviewId: Long) {
        viewModelScope.launch {
            reviewRepository.toggleReviewLike(reviewId = reviewId)
                .onSuccess { likeResponse ->
                    val currentDetail = _reviewDetails.value[reviewId] ?: return@onSuccess
                    val updatedDetail = currentDetail.copy(
                        isLiked = likeResponse.success,
                        likeCount = likeResponse.likeCount
                    )
                    _reviewDetails.value = _reviewDetails.value + (reviewId to updatedDetail)
                }
                .onFailure { error ->
                    _errorMessage.value = error.message ?: "좋아요 처리에 실패했습니다."
                }
        }
    }

    // 댓글 관련
    fun updateCommentInput(text: String) {
        _commentInputText.value = text
    }

    fun selectReviewForComment(reviewId: Long?) {
        _selectedReviewForComment.value = reviewId
    }

    fun submitComment() {
        val selectedReviewId = _selectedReviewForComment.value ?: return
        val commentText = _commentInputText.value

        if (commentText.isNotBlank()) {
            val currentDetail = _reviewDetails.value[selectedReviewId] ?: return

            val newComment = ReviewCommentEntity(
                id = System.currentTimeMillis(),
                nickname = "나",
                profileImage = null,
                content = commentText,
                createdAt = "방금 전"
            )

            val updatedDetail = currentDetail.copy(
                comments = currentDetail.comments + newComment,
                commentCount = currentDetail.commentCount + 1,
                isCommentsVisible = true
            )

            _reviewDetails.value = _reviewDetails.value + (selectedReviewId to updatedDetail)

            // 입력 폼 초기화
            _commentInputText.value = ""
            _selectedReviewForComment.value = null
        }
    }

    // 댓글 표시/숨김 토글
    fun toggleCommentsVisibility(reviewId: Long) {
        val currentDetail = _reviewDetails.value[reviewId] ?: return
        val updatedDetail = currentDetail.copy(
            isCommentsVisible = !currentDetail.isCommentsVisible
        )

        _reviewDetails.value = _reviewDetails.value + (reviewId to updatedDetail)
    }

    // Helper 함수: 리뷰 목록을 상세 정보와 함께 반환
    fun getReviewsWithDetails(): List<ReviewDetailEntity> {
        val reviews = _bookReviews.value?.reviews ?: emptyList()
        return reviews.mapNotNull { review ->
            _reviewDetails.value[review.id]
        }
    }

    fun clearErrorMessage() {
        _errorMessage.value = null
    }

    fun refreshReviews() {
        loadBookReviews(currentBookId)
    }
}
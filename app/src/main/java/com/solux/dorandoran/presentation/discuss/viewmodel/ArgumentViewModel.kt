package com.solux.dorandoran.presentation.discuss.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solux.dorandoran.domain.entity.DiscussCommentEntity
import com.solux.dorandoran.domain.repository.ArgumentRepository
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.State
import javax.inject.Inject

class ArgumentViewModel @Inject constructor(
    private val argumentRepository: ArgumentRepository
) : ViewModel() {

    private val _uiState = mutableStateOf<ArgumentUiState>(ArgumentUiState.Loading)
    val uiState: State<ArgumentUiState> get() = _uiState

    var argumentInput by mutableStateOf("")
        private set

    fun updateArgumentInput(newInput: String) {
        argumentInput = newInput
    }

    fun getArguments(boardId: Int) {
        _uiState.value = ArgumentUiState.Loading
        viewModelScope.launch {
            val result = argumentRepository.getArgumentsForDiscussion(boardId)
            result.onSuccess { arguments ->
                _uiState.value = ArgumentUiState.Success(arguments)
            }.onFailure { e ->
                _uiState.value = ArgumentUiState.Error(e.message ?: "알 수 없는 오류가 발생했습니다.")
            }
        }
    }

    fun postArgument(boardId: Int) {
        if (argumentInput.isBlank()) return
        viewModelScope.launch {
            val result = argumentRepository.createArgument(boardId, argumentInput)
            result.onSuccess { newArgument ->
                val current = (_uiState.value as? ArgumentUiState.Success)?.arguments ?: emptyList()
                _uiState.value = ArgumentUiState.Success(listOf(newArgument) + current)
                argumentInput = ""
            }
        }
    }

    fun postComment(boardId: Int, parentId: Int, content: String) {
        if (content.isBlank()) return
        viewModelScope.launch {
            val result = argumentRepository.createComment(boardId, content, parentId)
            result.onSuccess { newComment ->
                val current = (_uiState.value as? ArgumentUiState.Success)?.arguments ?: emptyList()
                _uiState.value = ArgumentUiState.Success(current + newComment)
            }
        }
    }

}


sealed interface ArgumentUiState {
    object Loading : ArgumentUiState
    data class Success(val arguments: List<DiscussCommentEntity>) : ArgumentUiState
    data class Error(val message: String) : ArgumentUiState
}
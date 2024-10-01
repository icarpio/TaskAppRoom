package com.example.taskapproom.addtasks.ui

import com.example.taskapproom.addtasks.ui.model.TaskModel

sealed interface TaskUiState {
    object Loading:TaskUiState
    data class Error(val throwable: Throwable):TaskUiState
    data class Success(val tasks:List<TaskModel>):TaskUiState
}
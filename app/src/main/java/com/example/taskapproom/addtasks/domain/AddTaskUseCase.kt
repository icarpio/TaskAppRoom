package com.example.taskapproom.addtasks.domain

import com.example.taskapproom.addtasks.data.TaskRepository
import com.example.taskapproom.addtasks.ui.model.TaskModel
import javax.inject.Inject

class AddTaskUseCase  @Inject constructor(private val taskRepository: TaskRepository){
    suspend operator fun invoke(taskModel: TaskModel){
        taskRepository.add(taskModel)
    }
}
package com.bhavesh.to_dolist.ui.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bhavesh.to_dolist.ui.data.repository.TaskRepository
import com.bhavesh.to_dolist.ui.data.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val taskRepository: TaskRepository) : ViewModel() {

    fun addTaskData(taskModel: TaskModel) {
        taskRepository.addTaskToRoom(taskModel)
    }

    fun getTasks(): LiveData<List<TaskModel>> {
        return taskRepository.getTaskData()
    }

    fun getSearchTask(search: String): LiveData<List<TaskModel>> {
        return taskRepository.getSearchData(search)
    }

    fun updateTaskData(taskModel: TaskModel) {
        taskRepository.updateRoutineData(taskModel)
    }

    fun deleteRoutineData(taskModel: TaskModel) {
        taskRepository.deleteRoutine(taskModel)
    }

    fun deleteAllTask() {
        taskRepository.deleteAllRoutine()
    }
}
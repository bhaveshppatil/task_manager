package com.bhavesh.to_dolist.ui.data.repository

import androidx.lifecycle.LiveData
import com.bhavesh.to_dolist.ui.data.model.TaskModel
import com.bhavesh.to_dolist.ui.data.roomDB.TaskDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TaskRepository @Inject constructor(val taskDAO: TaskDAO) {

    fun addTaskToRoom(taskModel: TaskModel) {
        CoroutineScope(Dispatchers.IO).launch {
            taskDAO.addRoutine(taskModel)
        }
    }

    fun getTaskData(): LiveData<List<TaskModel>> {
        return taskDAO.getRoutineData()
    }

    fun getSearchData(search: String): LiveData<List<TaskModel>> {
        return taskDAO.getSearchData(search)
    }

    fun updateRoutineData(taskModel: TaskModel) {
        CoroutineScope(Dispatchers.IO).launch {
            taskDAO.updateRoutine(taskModel)
        }
    }

    fun deleteRoutine(taskModel: TaskModel) {
        CoroutineScope(Dispatchers.IO).launch {
            taskDAO.deleteRoutine(taskModel)
        }
    }

    fun deleteAllRoutine() {
        CoroutineScope(Dispatchers.IO).launch {
            taskDAO.deleteAllRoutine()
        }
    }
}
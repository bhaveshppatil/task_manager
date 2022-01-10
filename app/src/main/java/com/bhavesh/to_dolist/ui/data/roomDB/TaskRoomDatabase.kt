package com.bhavesh.to_dolist.ui.data.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bhavesh.to_dolist.ui.data.model.TaskModel

@Database(entities = [TaskModel::class], version = 1)
abstract class TaskRoomDatabase : RoomDatabase() {

    abstract fun getRoutineDAO(): TaskDAO
}
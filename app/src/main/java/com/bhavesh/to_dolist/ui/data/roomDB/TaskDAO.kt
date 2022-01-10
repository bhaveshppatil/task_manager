package com.bhavesh.to_dolist.ui.data.roomDB

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bhavesh.to_dolist.ui.data.model.TaskModel

@Dao
interface TaskDAO {

    //Insert the data into database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRoutine(taskModel: TaskModel)

    //It will return the list of task in desc order
    @Query("select * from task_manager order by id desc ")
    fun getRoutineData(): LiveData<List<TaskModel>>

    @Query("delete from task_manager")
    fun deleteAllRoutine()

    //Update the data into database
    @Update
    fun updateRoutine(taskModel: TaskModel)

    @Query("SELECT * FROM task_manager WHERE title LIKE :search")
    fun getSearchData(search: String?): LiveData<List<TaskModel>>

    //Delete the record from Database
    @Delete
    fun deleteRoutine(taskModel: TaskModel)
}
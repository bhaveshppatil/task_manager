package com.bhavesh.to_dolist.ui.ui.clicklistener

import com.bhavesh.to_dolist.ui.data.model.TaskModel

interface OnTaskItemClicked {

    fun onDeleteClicked(taskModel: TaskModel)
    fun onEditClicked(taskModel: TaskModel)

}
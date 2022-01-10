package com.bhavesh.to_dolist.ui.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bhavesh.to_dolist.R
import com.bhavesh.to_dolist.ui.data.model.TaskModel
import com.bhavesh.to_dolist.ui.ui.clicklistener.OnTaskItemClicked
import com.bhavesh.to_dolist.ui.ui.viewholder.TaskViewHolder


class TaskAdapter(
    val context: Context,
    val taskList: MutableList<TaskModel>,
    val clickListener: OnTaskItemClicked
) : RecyclerView.Adapter<TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(context)
        val view1: View = inflater.inflate(R.layout.routine_item_row, parent, false)
        return TaskViewHolder(view1)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {

        val routineModel = taskList[position]
        holder.setData(routineModel, context, clickListener)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}
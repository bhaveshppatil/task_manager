package com.bhavesh.to_dolist.ui.ui.viewholder

import android.content.Context
import android.view.View
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhavesh.to_dolist.R
import com.bhavesh.to_dolist.ui.data.model.TaskModel
import com.bhavesh.to_dolist.ui.ui.clicklistener.OnTaskItemClicked

class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val title: TextView = itemView.findViewById(R.id.tvTaskTitle)
    val date: TextView = itemView.findViewById(R.id.tvTaskDate)
    val time: TextView = itemView.findViewById(R.id.tvTaskTime)
    val menuBar: TextView = itemView.findViewById(R.id.tvMenu)

    fun setData(taskModel: TaskModel, context: Context, clickListener: OnTaskItemClicked) {
        title.text = taskModel.title

        date.text = taskModel.date
        time.text = taskModel.time

        menuBar.setOnClickListener {
            val popupMenu = PopupMenu(context, menuBar)
            popupMenu.inflate(R.menu.query_menu_list)

            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.update -> {
                        clickListener.onEditClicked(taskModel)
                    }
                    R.id.delete -> {
                        clickListener.onDeleteClicked(taskModel)
                    }
                }
                false
            })
            popupMenu.show()
        }
    }
}
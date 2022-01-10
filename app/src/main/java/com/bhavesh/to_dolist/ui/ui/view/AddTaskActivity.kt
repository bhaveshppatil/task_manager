package com.bhavesh.to_dolist.ui.ui.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bhavesh.to_dolist.R
import com.bhavesh.to_dolist.ui.data.model.TaskModel
import com.bhavesh.to_dolist.ui.ui.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_add_task.*
import java.util.*

@AndroidEntryPoint
class AddTaskActivity : AppCompatActivity() {
    var timeNotify: String = ""
    private val taskViewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        ivSelectTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar[Calendar.HOUR_OF_DAY]
            val minute = calendar[Calendar.MINUTE]
            val timePickerDialog = TimePickerDialog(
                this,
                { timePicker, i, i1 ->
                    timeNotify = "$i:$i1"
                    tvTime.text = FormatTime(i, i1).toString()
                }, hour, minute, false
            )
            timePickerDialog.show()
        }
        ivSelectDate.setOnClickListener(View.OnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar[Calendar.YEAR]
            val month = calendar[Calendar.MONTH]
            val day = calendar[Calendar.DAY_OF_MONTH]
            val datePickerDialog = DatePickerDialog(
                this,
                { datePicker, year, month, day ->
                    tvDate.text = day.toString() + "-" + (month + 1) + "-" + year
                }, year, month, day
            )
            datePickerDialog.show()
        })

        btnTaskDone.setOnClickListener {

            val title = etRoutine.text.toString()
            val date = tvDate.text.toString()
            val time = tvTime.text.toString()

            if (title.isNotEmpty() && date.isNotEmpty() && time.isNotEmpty()) {
                val routineModel = TaskModel(title, date, time)
                taskViewModel.addTaskData(routineModel)
            } else {
                showToast("check all fields")
            }
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun FormatTime(hour: Int, minute: Int): String {
        var time: String = ""
        val formattedMinute: String = if (minute / 10 == 0) {
            "0$minute"
        } else {
            "" + minute
        }
        time = when {
            hour == 0 -> {
                "12:$formattedMinute AM"
            }
            hour < 12 -> {
                "$hour:$formattedMinute AM"
            }
            hour == 12 -> {
                "12:$formattedMinute PM"
            }
            else -> {
                val temp = hour - 12
                "$temp:$formattedMinute PM"
            }
        }
        return time
    }
}
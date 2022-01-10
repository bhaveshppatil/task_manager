package com.bhavesh.to_dolist.ui.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bhavesh.to_dolist.R
import com.bhavesh.to_dolist.ui.data.model.TaskModel
import com.bhavesh.to_dolist.ui.ui.adapter.TaskAdapter
import com.bhavesh.to_dolist.ui.ui.clicklistener.OnTaskItemClicked
import com.bhavesh.to_dolist.ui.ui.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), OnTaskItemClicked {

    private val taskModelList = mutableListOf<TaskModel>()
    private lateinit var taskAdapter: TaskAdapter
    private val taskViewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        setRecyclerView()

        btnFab.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java);
            startActivity(intent)
        }

        taskViewModel.getTasks().observe(this, Observer {
            taskModelList.clear()
            taskModelList.addAll(it)
            updateUI(taskModelList)
            taskAdapter.notifyDataSetChanged()
        })

    }

    private fun setRecyclerView() {
        taskAdapter = TaskAdapter(this, taskModelList, this)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = taskAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)
        val searchView = menu.findItem(R.id.search_bar)
        val searchManager = searchView.actionView as SearchView
        searchManager.queryHint = "search task here"
        searchManager.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                taskViewModel.getSearchTask(query!!).observe(this@HomeActivity, Observer {
                    taskModelList.clear()
                    taskModelList.addAll(it)
                    updateUI(taskModelList)
                    taskAdapter.notifyDataSetChanged()
                })
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.deleteAll -> {
                deleteAllRoutines()
            }
            R.id.theme -> {
                showToast("currently not available")
            }
            R.id.setting -> {
                showToast("currently not available")
            }
            R.id.search_bar -> {
                showToast("currently not available")
            }
        }
        return true
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun deleteAllRoutines() {
        val builder = AlertDialog.Builder(this)
        builder.apply {
            setTitle("Do you want to remove all routine??")
            setPositiveButton("Yes") { _, _ ->
                if (taskModelList.size == 0) {
                    showToast("List is already empty")
                } else {
                    taskViewModel.deleteAllTask()
                    showToast("Task Deleted Successfully")
                }
            }
            setNegativeButton("No") { _, _ -> }
            create()
            show()
        }
    }

    private fun updateUI(routineModelList: List<TaskModel>) {

        if (routineModelList.isEmpty()) {
            recyclerView.visibility = View.GONE
            layoutEmptyList.visibility = View.VISIBLE
        } else {
            layoutEmptyList.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }

    override fun onDeleteClicked(taskModel: TaskModel) {
        val builder = AlertDialog.Builder(this)
        builder.apply {
            setTitle("Do you want to remove this routine??")
            setPositiveButton("Yes") { _, _ ->
                taskViewModel.deleteRoutineData(taskModel)
                showToast("Routine deleted successfully")
            }
            setNegativeButton("No") { _, _ -> }
            create()
            show()
        }
    }

    override fun onEditClicked(taskModel: TaskModel) {
        showToast("Not available")
    }
}
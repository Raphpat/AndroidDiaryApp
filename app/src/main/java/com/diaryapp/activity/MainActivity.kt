package com.diaryapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diaryapp.DiaryApplication
import com.diaryapp.viewModel.FragmentViewModel
import com.diaryapp.viewModel.FragmentViewModelFactory
import com.diaryapp.R
import com.diaryapp.adapter.RecyclerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private val viewModel: FragmentViewModel by viewModels {
        FragmentViewModelFactory(
            (this.application as DiaryApplication).database
                .noteDao()
        )
    }

    val recyclerAdapter: RecyclerAdapter by lazy {
        RecyclerAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addDiaryEntry = findViewById<FloatingActionButton>(R.id.addNote)
        addDiaryEntry.setOnClickListener { onClick(null) }

        // RecycleView to contain the list of entries
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val llm = LinearLayoutManager(this)

        recyclerView.layoutManager = llm
        recyclerView.adapter = recyclerAdapter
        // Add a listener to the data in the database to update the cards on screen
        viewModel.getAllNotes().observe(this) { notes ->
            recyclerAdapter.setData(notes)
        }
    }

    // Launch the diary entry activity
    fun onClick(v: View?) {
        val i = Intent(applicationContext, DiaryActivity::class.java)
        startActivity(i)
    }


}

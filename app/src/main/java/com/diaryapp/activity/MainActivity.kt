package com.diaryapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.db.data.Note
import com.diaryapp.DiaryApplication
import com.diaryapp.R
import com.diaryapp.adapter.OnItemClickedListener
import com.diaryapp.adapter.OnItemDeleteListener
import com.diaryapp.adapter.RecyclerAdapter
import com.diaryapp.viewModel.FragmentViewModel
import com.diaryapp.viewModel.FragmentViewModel.Companion.DATE_ASC
import com.diaryapp.viewModel.FragmentViewModel.Companion.DATE_DESC
import com.diaryapp.viewModel.FragmentViewModel.Companion.TITLE_ASC
import com.diaryapp.viewModel.FragmentViewModel.Companion.TITLE_DESC
import com.diaryapp.viewModel.FragmentViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private val viewModel: FragmentViewModel by viewModels {
        FragmentViewModelFactory(
            (this.application as DiaryApplication).database
                .noteDao()
        )
    }

    private val recyclerAdapter: RecyclerAdapter by lazy {
        RecyclerAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Do toolbar things
        val toolbar = findViewById<Toolbar>(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        val addDiaryEntry = findViewById<FloatingActionButton>(R.id.addNote)
        addDiaryEntry.setOnClickListener { onClick(null) }

        // RecycleView to contain the list of entries
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val llm = LinearLayoutManager(this)
        recyclerView.layoutManager = llm
        recyclerView.adapter = recyclerAdapter
        // Set the method that deletes the diary entries from the database here
        recyclerAdapter.setOnItemDeleteListener(object : OnItemDeleteListener {
            override fun onDeleteButtonClicked(note: Note) {
                viewModel.deleteNote(note)
            }
        })
        // Set the method that opens the diary entries in the DiaryActivity here
        recyclerAdapter.setOnItemClickedListener(object : OnItemClickedListener{
            override fun onItemClick(noteId: Int) {
                val i = Intent(applicationContext, DiaryActivity::class.java)
                i.putExtra("ID", noteId)
                startActivity(i)
            }
        })
        // Add a listener to the data in the database to update the cards on screen
        viewModel.getAllNotes().observe(this) { notes ->
            recyclerAdapter.setData(notes)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.filter, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.dateDesc -> {
                viewModel.setSelectedSort(DATE_DESC)
                true
            }
            R.id.dateAsc -> {
                viewModel.setSelectedSort(DATE_ASC)
                true
            }
            R.id.titleDesc -> {
                viewModel.setSelectedSort(TITLE_DESC)
                true
            }
            R.id.titleAsc -> {
                viewModel.setSelectedSort(TITLE_ASC)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Launch the diary entry activity
    fun onClick(v: View?) {
        val i = Intent(applicationContext, DiaryActivity::class.java)
        startActivity(i)
    }

}


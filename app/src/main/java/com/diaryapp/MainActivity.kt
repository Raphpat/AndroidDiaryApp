package com.diaryapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    //TODO List the diary entries
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addDiaryEntry = findViewById<FloatingActionButton>(R.id.addNote)
        addDiaryEntry.setOnClickListener{onClick(null)}
    }

    // Launch the diary entry activity
    fun onClick(v: View?) {
        val i = Intent(applicationContext, DiaryActivity::class.java)
        startActivity(i)
    }
}


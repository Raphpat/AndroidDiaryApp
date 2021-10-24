package com.diaryapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

class TextEditorFragment : Fragment() {
    //TODO pass the date along
//    private var date = ""
//    companion object {
//
//        @JvmStatic
//        fun newInstance(date: String) = TextEditorFragment().apply {
//            arguments = Bundle().apply {
//                putString("date", date)
//            }
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.text_editor_fragment, container, false)
        val dateReminder = view.findViewById<TextView>(R.id.dateReminder)
        dateReminder.text = SimpleDateFormat("dd/MM/yyyy").format(Date())
        return view

        //TODO save the note
    }


}
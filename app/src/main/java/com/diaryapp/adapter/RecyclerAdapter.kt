package com.diaryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.db.data.Note
import com.diaryapp.R

/**
 * Adapter that transforms Note objects into cards that display inside a RecycleView
 */
class RecyclerAdapter :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var dataSet = listOf<Note>()

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView
        val contentView: TextView
        val deleteButton: Button

        init {
            // Define click listener for the ViewHolder's View.
            titleView = view.findViewById(R.id.titleText)
            contentView = view.findViewById(R.id.contentText)
            deleteButton = view.findViewById(R.id.btnDelete)
        }

        fun bind(note: Note?) {
            if (note != null) {
                titleView.text = note.title
                contentView.text = note.content
                deleteButton.setOnClickListener { v ->
                    // TODO delete diary entry
                }
            }
        }

        // TODO onclick open the diary entry
//        fun onClick(v: View) {
//            val pos = adapterPosition
//            Toast.makeText(v.context, dataSet.get(pos), Toast.LENGTH_LONG).show()
//        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.note_card_layout, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.bind(dataSet[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    fun setData(newData: List<Note>) {
        dataSet = newData
        notifyDataSetChanged()
    }

}

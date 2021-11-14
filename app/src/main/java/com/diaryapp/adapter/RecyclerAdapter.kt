package com.diaryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.db.data.Note
import com.diaryapp.R
import java.time.format.DateTimeFormatter

/**
 * Adapter that transforms Note objects into cards that display inside a RecycleView
 */
class RecyclerAdapter() :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var dataSet = listOf<Note>()
    private lateinit var onItemDeleteListener: OnItemDeleteListener
    private lateinit var onItemClickedListener: OnItemClickedListener

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        val titleView: TextView
        val dateView: TextView
        val contentView: TextView
        val deleteButton: Button

        init {
            // Define click listener for the ViewHolder's View.
            titleView = view.findViewById(R.id.titleText)
            dateView = view.findViewById(R.id.dateText)
            contentView = view.findViewById(R.id.contentText)
            deleteButton = view.findViewById(R.id.btnDelete)
        }

        fun bind(note: Note?) {
            if (note != null) {
                titleView.text = note.title
                dateView.text = dateFormat.format(note.date)
                contentView.text = note.content
            }
        }
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
        val position = viewHolder.adapterPosition
        // Adds event listener to delete the diary entry
        viewHolder.deleteButton.setOnClickListener { v ->
            val note = dataSet[position]
            (dataSet as MutableList).remove(note)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, dataSet.size)
            // Call the custom delete interface that was created in main activity and delete the note in the room db
            onItemDeleteListener.onDeleteButtonClicked(note)
        }
        // Adds event listener to open a diary entry
        viewHolder.itemView.setOnClickListener{
            onItemClickedListener.onItemClick(dataSet[position].id)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    fun setData(newData: List<Note>) {
        dataSet = newData
        notifyDataSetChanged()
    }

    // A setter for the empty delete interface
    fun setOnItemDeleteListener(l: OnItemDeleteListener) {
        onItemDeleteListener = l
    }

    // A setter for the empty onClick interface
    fun setOnItemClickedListener(l: OnItemClickedListener) {
        onItemClickedListener = l
    }
}


/**
 * Interface with an empty method to be overrided in the main activty for the delete button
 */
interface OnItemDeleteListener {
    fun onDeleteButtonClicked(note: Note)
}

/**
 * Interface with an empty method to be overrided in the main activty for the whole note
 */
interface OnItemClickedListener {
    fun onItemClick(noteId: Int)
}

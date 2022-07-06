package com.example.arti.ui.bookList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.arti.R
import com.example.arti.data.Book

class ListAdapter(
    private val context: Context,
    private val dataset: List<Book>
    ) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    class ListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item_title)
        val imageView: ImageView = view.findViewById(R.id.item_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        return ListViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text = context.resources.getString(item.stringResourceId)
        holder.imageView.setImageResource(item.imageResourceId)
        }

    override fun getItemCount() = dataset.size
}
package com.example.arti.ui.adapters
import OpenLibraryBook
import OpenLibrarySearchResponse
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.arti.R
import retrofit2.Response


class BooksListAdapter(private val clickListener: (OpenLibraryBook) -> Unit) :
    ListAdapter<OpenLibraryBook, BooksListAdapter.ListViewHolder>(DiffCallback) {

    class ListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.item_name)
        val authorTextView: TextView = view.findViewById(R.id.item_author)
        val imageView: ImageView = view.findViewById(R.id.item_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        return ListViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = getItem(position)
            holder.imageView.setImageResource(item.cover_i)
            holder.authorTextView.text = item.author_alternative_name[0]
            holder.nameTextView.text = item.title

        holder.view.setOnClickListener {
                clickListener(item)
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<OpenLibraryBook>() {
        override fun areItemsTheSame(oldItem: OpenLibraryBook, newItem: OpenLibraryBook): Boolean {
            return oldItem.title == newItem.title
        }
        override fun areContentsTheSame(oldItem: OpenLibraryBook, newItem: OpenLibraryBook): Boolean {
            return oldItem.ia == newItem.ia
        }
    }
}


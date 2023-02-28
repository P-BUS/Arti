package com.example.arti.ui.adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.arti.databinding.ItemViewBinding
import com.example.arti.utils.ImageLoader
import com.example.arti.utils.ImageSize
import com.example.model.Book


class BooksListAdapter(
    private val onItemClicked: (com.example.model.Book) -> Unit
) : ListAdapter<com.example.model.Book, BooksListAdapter.ListViewHolder>(DiffCallback) {

    class ListViewHolder(private var binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(book: com.example.model.Book) {
            binding.itemAuthor.text = book.title
            binding.itemName.text = book.authorName[0]
            //Load the image from web service using Coil
            ImageLoader.loadImage(binding.itemImage, book.coverI, ImageSize.M)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val viewHolder = ListViewHolder(
            ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<com.example.model.Book>() {
        override fun areItemsTheSame(oldItem: com.example.model.Book, newItem: com.example.model.Book): Boolean {
            return oldItem.key == newItem.key
        }
        override fun areContentsTheSame(oldItem: com.example.model.Book, newItem: com.example.model.Book): Boolean {
            return oldItem == newItem
        }
    }
}


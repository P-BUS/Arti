package com.example.arti.ui.adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.arti.data.model.Book
import com.example.arti.databinding.ItemViewBinding
import com.example.arti.utils.ImageLoader
import com.example.arti.utils.ImageSize


class BooksListAdapter(
    private val onItemClicked: (Book) -> Unit
) : ListAdapter<Book, BooksListAdapter.ListViewHolder>(DiffCallback) {

    class ListViewHolder(private var binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {
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

    companion object DiffCallback : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.key == newItem.key
        }
        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }
    }
}


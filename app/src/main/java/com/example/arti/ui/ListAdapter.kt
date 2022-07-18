package com.example.arti.ui
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
    private val dataset: List<Book>,
    private val goToDetails: () -> Unit,
    private val clickListener: (Book) -> Unit,
    private val updatePrice: () -> Unit

) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

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
        val item = dataset[position]
        holder.imageView.setImageResource(item.bookImageId)
        holder.authorTextView.text = context.resources.getString(item.bookAuthorId)
        holder.nameTextView.text = context.resources.getString(item.bookNameId)

        holder.view.setOnClickListener {
            goToDetails()
            clickListener(item)
            updatePrice()
        }
    }

    override fun getItemCount() = dataset.size
}


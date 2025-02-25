package com.example.tabbedactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(private val books: List<Book>, private val onClick: (Book) -> Unit) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.book_title)
        val authorTextView: TextView = view.findViewById(R.id.book_author)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.titleTextView.text = book.title
        holder.authorTextView.text = book.author
        holder.itemView.setOnClickListener {
            showBookDescriptionDialog(holder, book)
        }
    }

    private fun showBookDescriptionDialog(holder: BookViewHolder, book: Book) {
        val dialogBuilder = AlertDialog.Builder(holder.itemView.context)
        dialogBuilder.setTitle(book.title)
        dialogBuilder.setMessage(book.description)
        dialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }

    override fun getItemCount() = books.size
}

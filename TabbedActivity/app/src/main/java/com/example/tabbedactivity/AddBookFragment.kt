package com.example.tabbedactivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class AddBookFragment : Fragment() {

    private val books = mutableListOf<Book>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragmentaddbook, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleEditText = view.findViewById<EditText>(R.id.edit_text_title)
        val authorEditText = view.findViewById<EditText>(R.id.edit_text_author)
        val descriptionEditText = view.findViewById<EditText>(R.id.edit_text_description)
        val addButton = view.findViewById<Button>(R.id.button_add)

        addButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val author = authorEditText.text.toString()
            val description = descriptionEditText.text.toString()

            if (title.isNotEmpty() && author.isNotEmpty()) {
                val newBook = Book(title, author, description)
                books.add(newBook)

                titleEditText.text.clear()
                authorEditText.text.clear()
                descriptionEditText.text.clear()

                Toast.makeText(context, "Книга добавлена!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

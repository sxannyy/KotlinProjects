package com.example.tabbedactivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class BookDetailFragment : Fragment() {

    private lateinit var titleTextView: TextView
    private lateinit var authorTextView: TextView
    private lateinit var descriptionTextView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragmentbookdetail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        titleTextView = view.findViewById(R.id.text_view_title)
        authorTextView = view.findViewById(R.id.text_view_author)
        descriptionTextView = view.findViewById(R.id.text_view_description)

        arguments?.let {
            titleTextView.text = it.getString("title")
            authorTextView.text = it.getString("author")
            descriptionTextView.text = it.getString("description")
        }
    }
}
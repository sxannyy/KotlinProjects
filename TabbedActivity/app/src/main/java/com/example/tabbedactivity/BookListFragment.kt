package com.example.tabbedactivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.fragment.app.FragmentManager

class BookListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val books = mutableListOf<Book>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragmentbooklist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        books.add(Book("Опасные связи", "Шодерло де Лакло", "Нравоучительный роман в письмах из жизни куртуазного XVIII века. Порок плетет хитроумные интриги, заставляя восклицать: «О времена! О нравы!» Однако добродетель все-таки торжествует."))
        books.add(Book("1984", "Джордж Оруэлл", "Дистопийный роман о тоталитарном режиме."))
        books.add(Book("Убить пересмешника", "Харпер Ли", "Роман о расовых предрассудках и справедливости."))
        books.add(Book("Бледный огонь", "Владимир Набоков", "Самое необычное по форме произведение Владимира Набокова, состоящее из эпиграфа, одноименной поэмы в 999 строк, предисловия к ней, обширного комментария и указателя."))
        books.add(Book("Гаргантюа и Пантагрюэль", "Франсуа Рабле", "Феерия душевного здоровья, грубых и добрых шуток, пародия пародий, каталог всего. Сколько столетий прошло, а ничего не изменилось."))

        val adapter = BookAdapter(books) { book ->

            val detailFragment = BookDetailFragment().apply {
                arguments = Bundle().apply {
                    putString("title", book.title)
                    putString("author", book.author)
                    putString("description", book.description)
                }
            }
            parentFragmentManager.beginTransaction()
                .replace(R.id.view_pager, detailFragment)
                .addToBackStack(null)
                .commit()
        }

        recyclerView.adapter = adapter
    }
}
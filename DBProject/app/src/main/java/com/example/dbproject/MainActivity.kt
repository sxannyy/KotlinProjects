package com.example.dbproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val db by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "results.db"
        ).allowMainThreadQueries().build()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val context = this
        GlobalScope.launch {
            for (company in TestData.russianCompanies2020) {
                db.resultsDao().insert(company)
            }
        }

        val companies_list = findViewById<RecyclerView>(R.id.companies_list)
        val statistics = findViewById<Button>(R.id.statistics)

        companies_list.layoutManager = LinearLayoutManager(this)
        db.resultsDao().getAll("RESULT DESC").observe(this
        ) { results -> companies_list.adapter = ResultAdapter(results) }
        statistics.setOnClickListener {
            startActivity(Intent(this, StatActivity::class.java))
        }
        val delete_field=findViewById<EditText>(R.id.toDelete)
        val delete_button=findViewById<Button>(R.id.delete)
        delete_button.setOnClickListener{
            var text=delete_field.text
            db.resultsDao().getAll("RESULT DESC").observe(this) {results->
                Log.d("mytag",text.toString())
                for (i in 0 ..results.size-1){

                    if (results[i].name.toString().contains(text.toString(),ignoreCase = true) == true) {
                        db.resultsDao().delete(results[i])
                        Log.d("mytag",results[i].toString())
                    }
                }
            }
            companies_list.layoutManager = LinearLayoutManager(this)
            db.resultsDao().getAll("RESULT DESC").observe(this
            ) { results -> companies_list.adapter = ResultAdapter(results) }
            statistics.setOnClickListener {
                startActivity(Intent(this, StatActivity::class.java))
            }
        }

    }
}
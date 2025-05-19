package com.example.dbproject

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import java.util.regex.Pattern

class StatActivity : AppCompatActivity() {
    val db by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "results.db"
        ).build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_stat)
        var list:List<ResultEntity>
        db.resultsDao().getAll("RESULT DESC").observe(this){results->list=results;
            var maxresult=0
            var maxname=""
            var maxlengthname=""
            var kapital=0
            var anglkap=0
            var kaps=0
            for (i in 0..list.size-1) {
                if (list[i].result!! >maxresult){
                    maxresult= list[i].result!!
                    maxname=list[i].name.toString()
                }
                if (list[i].result!! >4000){
                    kaps+=1
                }
                if (list[i].name!!.toString().length>maxlengthname.length){
                    maxlengthname= list[i].name.toString()
                }
                kapital += list[i].result!!
                val PATTERN = Pattern.compile("""^[_A-z0-9]*((\s)*[_A-z0-9])*${'$'}""")
                if (PATTERN.matcher(list[i].name!!.toString()).matches()){
                    anglkap+=1
                }
            }
            findViewById<TextView>(R.id.money).text= kapital.toString()
            findViewById<TextView>(R.id.best).text= maxname
            findViewById<TextView>(R.id.good).text=kaps.toString()
            findViewById<TextView>(R.id.english).text= anglkap.toString()
            findViewById<TextView>(R.id.longest).text= maxlengthname
        }

    }
}
package com.example.alertdialog

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import java.util.Locale

class MainActivity : AppCompatActivity() {
    lateinit var fm: FragmentManager
    lateinit var ft: FragmentTransaction
    lateinit var fr1: Fragment
    lateinit var fr2: Fragment
    lateinit var toFinishTask: Button
    lateinit var toCurrentTask: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        fm = supportFragmentManager
        ft = fm.beginTransaction()
        fr2 = DetailedWeatherFragment()

        val fr = fm.findFragmentById(R.id.container_fragm)
        if (fr == null) {
            fr1 = WeatherFragment()
            fm.beginTransaction().add(R.id.container_fragm, fr1)
                .commit()
        } else
            fr1 = fr

        toCurrentTask = findViewById(R.id.currentTask)
        toFinishTask = findViewById(R.id.finishTask)

        toFinishTask.setOnClickListener {

            val ft = fm.beginTransaction()
            ft.replace(R.id.container_fragm, fr2)
            ft.commit() }

        toCurrentTask.setOnClickListener {
            val ft = fm.beginTransaction()
            ft.replace(R.id.container_fragm, fr1)
            ft.commit() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_english -> {
                switchLanguage("en")
                true
            }
            R.id.action_russian -> {
                switchLanguage("ru")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun switchLanguage(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
        recreate()
    }
}
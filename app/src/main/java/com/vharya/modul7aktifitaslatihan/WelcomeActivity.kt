package com.vharya.modul7aktifitaslatihan

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.content.edit

class WelcomeActivity : AppCompatActivity() {
    private lateinit var buttonLogout: Button
    private lateinit var buttonExit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        buttonLogout = findViewById(R.id.button_logout)
        buttonExit = findViewById(R.id.button_exit)

        buttonLogout.setOnClickListener { view ->
            val sharedPreferences = getSharedPreferences(MainActivity.PREF, MODE_PRIVATE)
            sharedPreferences.edit(commit = true) {
                clear()
            }

            moveTaskToBack(true)
            finish()
        }

        buttonExit.setOnClickListener { view ->
            moveTaskToBack(true)
            finish()
        }
    }
}
package com.vharya.modul7aktifitaslatihan

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {
    private lateinit var inputUsername: EditText
    private lateinit var inputPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inputUsername = findViewById(R.id.input_username)
        inputPassword = findViewById(R.id.input_password)
        buttonLogin = findViewById(R.id.button_login)

        buttonLogin.setOnClickListener { view ->
            sharedPreferences.edit(commit = true) {
                val username = inputUsername.text.toString()
                val password = inputPassword.text.toString()

                putString(MainActivity.PREF_USERNAME, username)
                putString(MainActivity.PREF_PASSWORD, password)
            }

            val intent = Intent(this@MainActivity, WelcomeActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        sharedPreferences = getSharedPreferences(MainActivity.PREF, MODE_PRIVATE)

        if (sharedPreferences.contains(MainActivity.PREF_USERNAME) == true ||
            sharedPreferences.contains(MainActivity.PREF_PASSWORD) == true) {
            val intent = Intent(this@MainActivity, WelcomeActivity::class.java)
            startActivity(intent)
        }

        super.onResume()
    }

    companion object {
        const val PREF = "main_prefs"
        const val PREF_USERNAME = "username"
        const val PREF_PASSWORD = "password"
    }
}
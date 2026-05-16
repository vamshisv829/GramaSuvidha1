package com.gramasuvidha.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gramasuvidha.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etPhone = findViewById<EditText>(R.id.etPhone)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val rgUserType = findViewById<RadioGroup>(R.id.rgUserType)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        btnLogin.setOnClickListener {
            val phone = etPhone.text.toString()
            val password = etPassword.text.toString()
            val userType = when (rgUserType.checkedRadioButtonId) {
                R.id.rbCitizen -> "citizen"
                R.id.rbAuthority -> "authority"
                else -> ""
            }

            if (phone.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter phone and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (phone.length != 10) {
                Toast.makeText(this, "Please enter valid 10-digit phone number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (userType.isEmpty()) {
                Toast.makeText(this, "Please select user type", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Mock authentication
            if (password.length >= 4) {
                // Save user type in SharedPreferences
                getSharedPreferences("GramaSuvidha", MODE_PRIVATE)
                    .edit()
                    .putString("userType", userType)
                    .putBoolean("isLoggedIn", true)
                    .apply()

                Toast.makeText(this, "Login Successful as $userType", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }

        btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}

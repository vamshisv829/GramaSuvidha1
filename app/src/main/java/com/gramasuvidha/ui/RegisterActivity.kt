package com.gramasuvidha.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gramasuvidha.R

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etName = findViewById<EditText>(R.id.etName)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val etVillage = findViewById<EditText>(R.id.etVillage)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)
        val rgUserType = findViewById<RadioGroup>(R.id.rgUserType)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val btnBackToLogin = findViewById<Button>(R.id.btnBackToLogin)

        btnRegister.setOnClickListener {
            val name = etName.text.toString()
            val phone = etPhone.text.toString()
            val village = etVillage.text.toString()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()
            val userType = when (rgUserType.checkedRadioButtonId) {
                R.id.rbCitizen -> "citizen"
                R.id.rbAuthority -> "authority"
                else -> ""
            }

            if (name.isEmpty() || phone.isEmpty() || village.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (phone.length != 10) {
                Toast.makeText(this, "Please enter valid 10-digit phone number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.length < 4) {
                Toast.makeText(this, "Password must be at least 4 characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (userType.isEmpty()) {
                Toast.makeText(this, "Please select user type", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Mock registration
            Toast.makeText(this, "Registration Successful as $userType! Please login.", Toast.LENGTH_SHORT).show()
            finish()
        }

        btnBackToLogin.setOnClickListener {
            finish()
        }
    }
}

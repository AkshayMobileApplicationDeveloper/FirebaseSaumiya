package com.example.firebasekotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {

    lateinit var database: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val signUpButton = findViewById<Button>(R.id.Button_sign_up)
        val inputName = findViewById<TextInputEditText>(R.id.input_name)
        val inputEmail = findViewById<TextInputEditText>(R.id.input_email)
        val inputPassword = findViewById<TextInputEditText>(R.id.input_password)
        val uniqueCardNumber = findViewById<TextInputEditText>(R.id.input_uniqueid)
        val signtochange = findViewById<TextView>(R.id.changetoSignin)
        /*
        SignUp Botton Click
         */
        signUpButton.setOnClickListener {
            val name = inputName.text.toString()
            val email = inputEmail.text.toString()
            val password = inputPassword.text.toString()
            val userId = uniqueCardNumber.text.toString()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || userId.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = User(name, email, password, userId)
            database = FirebaseDatabase.getInstance().getReference("users")

            try {
                database.child(userId).setValue(user).addOnSuccessListener {
                    Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener { exception ->
                    Toast.makeText(this, "Registration Failed: ${exception.message}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this, "An error occurred: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
        /*
        End for the Bottom Function
         */

        /*
        To change from Sign up to Sign in
         */
        signtochange.setOnClickListener {
            val SignInChange= Intent(this@SignUpActivity,SignInActivity::class.java)
            startActivity(SignInChange)
        }
    }
}



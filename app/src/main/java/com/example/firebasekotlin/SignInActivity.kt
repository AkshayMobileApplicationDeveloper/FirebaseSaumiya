package com.example.firebasekotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity() {
    lateinit var databaseReference: DatabaseReference

    companion object {
        const val USER_EMAIL = "package com.example.firebasekotlin.SignInActivity.USER_EMAIL"
        const val USER_NAME = "package com.example.firebasekotlin.SignInActivity.USER_NAME"
        const val USER_PASS = "package com.example.firebasekotlin.SignInActivity.USER_PASS"
        const val USER_ID = "package com.example.firebasekotlin.SignInActivity.USER_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val signInButton = findViewById<Button>(R.id.Button_sign_in)
        val userNameEditText = findViewById<TextInputEditText>(R.id.sign_in_uniqueid)

        signInButton.setOnClickListener {
            val uniqueId = userNameEditText.text.toString()
            if (uniqueId.isNotEmpty()) {
                readData(uniqueId)
            } else {
                Toast.makeText(this, "Please Enter Unique ID", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(uniqueId: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("users")
        databaseReference.child(uniqueId).get().addOnSuccessListener {
            if (it.exists()) {
                val userEmail = it.child("email").value.toString()
                val userName = it.child("name").value.toString()
                val userPass = it.child("password").value.toString()
                val userId = it.child("userid").value.toString()

                // Proceed to the next activity and pass data
                val intentWelcome = Intent(applicationContext,WelComeActivity::class.java)
                intentWelcome.putExtra(USER_EMAIL, userEmail)
                intentWelcome.putExtra(USER_NAME, userName)
                intentWelcome.putExtra(USER_PASS, userPass)
                intentWelcome.putExtra(USER_ID, userId)
                startActivity(intentWelcome)
                Toast.makeText(this,"There is a proble ",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "User does not exist, please first sign up !!", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed !!", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this@SignInActivity, "An error occurred: ${it.message}", Toast.LENGTH_SHORT).show()
        }
    }
}

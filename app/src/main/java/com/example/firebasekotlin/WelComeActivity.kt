package com.example.firebasekotlin

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class WelComeActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        try {
            val userEmail = intent.getStringExtra(SignInActivity.USER_EMAIL)
            val userName = intent.getStringExtra(SignInActivity.USER_NAME)
            val userPass = intent.getStringExtra(SignInActivity.USER_PASS)
            val userId = intent.getStringExtra(SignInActivity.USER_ID)

            val textwelcome=findViewById<TextView>(R.id.WelcomeText)
            val textdata =findViewById<TextView>(R.id.username_database_fetch)
            val MailText=findViewById<TextView>(R.id.BtnMail)
            val UniqueIdText = findViewById<TextView>(R.id.BtnUnique)


            /*
            SetText
             */

            textwelcome.text="Welome $userName"
            MailText.text="Mail : $userEmail"
            UniqueIdText.text="UserId : $userId"
        } catch (e: Exception) {
            Toast.makeText(this@WelComeActivity, "An error occurred: ${e.message}", Toast.LENGTH_SHORT).show()
        }




    }

}



package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    private lateinit var mAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()


        mAuth = FirebaseAuth.getInstance()
        if(mAuth.currentUser != null){
            var intent = Intent(this@Login, MainActivity::class.java)
            startActivity(intent)
        }
        btn_signup.setOnClickListener {
            var intent = Intent(this,Signup::class.java)
            startActivity(intent)
        }

        btn_login.setOnClickListener {
            var email = Email.text.toString()
            var pass = Password.text.toString()

            login(email,pass)
        }
    }

    private fun login(email: String, pass: String) {
        mAuth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    println("Logged IN successfully")
                    var intent = Intent(this@Login, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@Login,"Incorrect UserID or Password",Toast.LENGTH_LONG).show()
                }
            }

    }
}
package com
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.sj.saijewellers.MainActivity
import com.sj.saijewellers.R

class SignIn : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        mAuth = FirebaseAuth.getInstance()
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        val btnSignIn: Button = findViewById(R.id.btnSignIn)
        val tvSignUp: TextView = findViewById(R.id.tvSignUp)

        if (mAuth.currentUser != null) {
            // User is already signed in, open the main activity
            startActivity(Intent(this@SignIn, MainActivity::class.java))
            finish()
        }

        btnSignIn.setOnClickListener {
            signInUser()
        }

        tvSignUp.setOnClickListener {
            startActivity(Intent(this@SignIn, SignUp::class.java))
        }
    }

    private fun signInUser() {
        val email = editTextEmail.text.toString().trim()
        val password = editTextPassword.text.toString().trim()

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success
                    startActivity(Intent(this@SignIn, MainActivity::class.java))
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@SignIn, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}

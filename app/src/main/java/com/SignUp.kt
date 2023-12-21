package com
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.sj.saijewellers.R

class SignUp: AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var editTextUserName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mAuth = FirebaseAuth.getInstance()
        editTextUserName = findViewById(R.id.editTextUserName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        val btnSignUp: Button = findViewById(R.id.btnSignUp)
        val tvSignIn: TextView = findViewById(R.id.tvSignIn)

        btnSignUp.setOnClickListener {
            signUpUser()
        }

        tvSignIn.setOnClickListener {
            startActivity(Intent(this@SignUp, SignIn::class.java))
        }
    }

    private fun signUpUser() {
        val userName = editTextUserName.text.toString().trim()
        val email = editTextEmail.text.toString().trim()
        val password = editTextPassword.text.toString().trim()

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign up success
                    Toast.makeText(this@SignUp, "Sign up successful", Toast.LENGTH_SHORT).show()
                    // You can navigate to the main activity or perform other actions here
                } else {
                    // If sign up fails, display a message to the user.
                    Toast.makeText(this@SignUp, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}

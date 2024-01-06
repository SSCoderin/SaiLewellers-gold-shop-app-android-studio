package com
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.sj.saijewellers.R



import com.google.firebase.auth.FirebaseAuth
import com.sj.saijewellers.MainActivity

class SplashScreen : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 1000 // 3 seconds
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        mAuth = FirebaseAuth.getInstance()

        Handler().postDelayed({
            if (mAuth.currentUser != null) {
                // User is already signed in, open the main activity
                startActivity(Intent(this@SplashScreen, MainActivity::class.java))
            } else {
                // User is not signed in, open the sign-up activity
                startActivity(Intent(this@SplashScreen, SignUp::class.java))
            }

            // Close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }
}

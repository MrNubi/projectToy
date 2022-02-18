package com.beyond.projecttoy

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.beyond.projecttoy.Login.IntroActivity
import com.beyond.projecttoy.databinding.ActivitySplashBinding
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import splitties.activities.start

class splash : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding : ActivitySplashBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        auth = Firebase.auth
        val molu = findViewById<ImageView>(R.id.molu)

        Glide.with(this).load(R.raw.toy_likelikeeffect).into(molu)



        if(auth.currentUser?.uid == null) {
            Log.d("SplashActivity", "null")

            Handler().postDelayed({
                startActivity(Intent(this, IntroActivity::class.java))
                finish()
            }, 3000)

        } else {
            Log.d("SplashActivity", "not null")

            Handler().postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, 3000)
        }


    }
}
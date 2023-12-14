package com.example.foodapp.Activity


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.foodapp.R


 class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val constraintLayout = findViewById<ConstraintLayout>(R.id.startbutton)
        constraintLayout.setOnClickListener {
        var intent = Intent(this, LogInActivity::class.java)
        startActivity(intent)
    }
    }
}
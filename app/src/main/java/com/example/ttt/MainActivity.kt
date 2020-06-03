package com.example.ttt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        easy.setOnClickListener{
            val intent = Intent(this, EasyActivity::class.java)
            startActivity(intent)
        }

        hard.setOnClickListener{
            val intent = Intent(this, HardActivity::class.java)
            startActivity(intent)
        }
    }
}

package com.example.lab7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun atiendeBoton1(view: View) {
        val i = Intent(this, MainActivity2::class.java)
        startActivity(i)
    }
    fun atiendeBoton2(view: View) {
        val i = Intent(this, MainActivity3::class.java)
        startActivity(i)
    }


    fun atiendeBoton3(view: View) {
        val i = Intent(this, MainActivity4::class.java)
        startActivity(i)
    }

    fun atiendeBoton4(view: View) {
        val i = Intent(this, MainActivity5::class.java)
        startActivity(i)
    }
}
package com.example.dailyglucose

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class EkranGlowny : AppCompatActivity() {

    private var btnWyloguj: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ekran_glowny)

        btnWyloguj = findViewById(R.id.btnWyloguj)

        btnWyloguj?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                wyloguj()
            }
        })

    }

    private fun wyloguj(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
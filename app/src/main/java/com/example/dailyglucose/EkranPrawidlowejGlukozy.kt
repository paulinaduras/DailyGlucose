package com.example.dailyglucose

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class EkranPrawidlowejGlukozy : AppCompatActivity() {

    private var btnOK : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ekran_prawidlowej_glukozy)

        btnOK = findViewById(R.id.btnGoodGlukoza1)

        btnOK?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                openEkranGlowny()
            }
        })

    }

    private fun openEkranGlowny(){
        val intent = Intent(this, EkranGlowny::class.java)
        startActivity(intent)
    }
}
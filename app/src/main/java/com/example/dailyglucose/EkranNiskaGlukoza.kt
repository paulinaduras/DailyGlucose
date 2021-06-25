package com.example.dailyglucose

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class EkranNiskaGlukoza : AppCompatActivity() {

    private var btnOK : Button? = null
    private var tvKostki : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ekran_niska_glukoza)

        btnOK = findViewById(R.id.btnBadGlukoza)
        tvKostki = findViewById(R.id.textBadGlukoza5)

        val glukoza = intent
        val poziomG = glukoza.getStringExtra("glucose")

        if (poziomG != null) {
            przelicznikCzekolady(poziomG.toInt())
        }

        btnOK?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                openEkranGlowny()
            }
        })
    }

    private fun przelicznikCzekolady(glu: Int){
        var różnica = 80 - glu
        var liczbaKostek = 0

        if (różnica < 11) liczbaKostek = 1
        else if (różnica < 21) liczbaKostek = 2
        else if (różnica < 31) liczbaKostek = 3
        else if (różnica < 41) liczbaKostek = 4
        else if (różnica < 51) liczbaKostek = 5
        else if (różnica < 61) liczbaKostek = 6
        else if (różnica < 71) liczbaKostek = 7
        else liczbaKostek = 8

        if (liczbaKostek == 1){
            tvKostki?.setText("$liczbaKostek kostkę czekolady.")
        }
        else if (liczbaKostek < 5){
            tvKostki?.setText("$liczbaKostek kostki czekolady.")
        }
        else{
            tvKostki?.setText("$liczbaKostek kostek czekolady.")
        }
    }

    private fun openEkranGlowny(){
        val intent = Intent(this, EkranGlowny::class.java)
        startActivity(intent)
    }

}
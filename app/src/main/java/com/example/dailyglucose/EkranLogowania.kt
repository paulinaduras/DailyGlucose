package com.example.dailyglucose

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class EkranLogowania : AppCompatActivity() {

    private var logowanieNazwaUzytkownika: EditText? = null
    private var logowanieHaslo: EditText? = null
    private var btnLogowania: Button? = null
    private var btnNoweKonto: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ekran_logowania)

        logowanieNazwaUzytkownika = findViewById(R.id.editTextLogowanie1)
        logowanieHaslo = findViewById(R.id.editTextLogowanie2)
        btnLogowania = findViewById(R.id.btnLogowania)
        btnNoweKonto = findViewById(R.id.btnNoweKonto)

        btnLogowania?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                rejestracja()
            }
        })

        btnNoweKonto?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                openEkranRejestracji()
            }
        })
    }

    private fun rejestracja(){
        val intent = Intent(this, EkranGlowny::class.java)
        startActivity(intent)
    }

    // ta metoda pozwala na przechodzenie z obecnego widoku na widok MainActivity
    private fun openEkranRejestracji(){
        val intent = Intent(this, EkranRejestracji::class.java)
        startActivity(intent)
    }
}
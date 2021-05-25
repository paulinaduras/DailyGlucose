package com.example.dailyglucose

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class EkranRejestracji : AppCompatActivity() {

    private var rejestracjaNazwaUzytkownika: EditText? = null
    private var rejestracjaHaslo: EditText? = null
    private var rejestracjaPowtorzHaslo: EditText? = null
    private var rejestracjaEmail: EditText? = null
    private var btnRejestracji: Button? = null
    private var btnMamKonto: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ekran_rejestracji)

        rejestracjaNazwaUzytkownika = findViewById(R.id.editTextRejestracji1)
        rejestracjaHaslo = findViewById(R.id.editTextRejestracji2)
        rejestracjaPowtorzHaslo = findViewById(R.id.editTextRejestracji3)
        rejestracjaEmail = findViewById(R.id.editTextEmailAddress)
        btnRejestracji = findViewById(R.id.btnRejestracji)
        btnMamKonto = findViewById(R.id.btnMamKonto)

        btnRejestracji?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                logowanie()
            }
        })

        btnMamKonto?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                openEkranLogowania()
            }
        })
    }

    private fun logowanie(){
        val intent = Intent(this, EkranGlowny::class.java)
        startActivity(intent)
    }

    private fun openEkranLogowania(){
        val intent = Intent(this, EkranLogowania::class.java)
        startActivity(intent)
    }
}
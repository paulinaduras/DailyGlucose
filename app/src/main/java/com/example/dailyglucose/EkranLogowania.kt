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
    private var btnPowrotu: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ekran_logowania)

        logowanieNazwaUzytkownika = findViewById(R.id.textLogowanie3)
        logowanieHaslo = findViewById(R.id.textLogowanie5)
        btnLogowania = findViewById(R.id.btnLogowania)
        btnPowrotu = findViewById(R.id.btnPowrotu)

        btnPowrotu?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                openMainActivity()
            }
        })
    }

    // ta metoda pozwala na przechodzenie z obecnego widoku na widok MainActivity
    private fun openMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
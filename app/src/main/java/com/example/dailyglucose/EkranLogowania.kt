package com.example.dailyglucose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class EkranLogowania : AppCompatActivity() {

    private var logowanieNazwaUzytkownika: EditText? = null
    private var logowanieHaslo: EditText? = null
    private var btnLogowanie1: Button? = null
    private var btnLogowanie2: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ekran_logowania)

        logowanieNazwaUzytkownika = findViewById(R.id.textLogowanie3)
        logowanieHaslo = findViewById(R.id.textLogowanie5)
        btnLogowanie1 = findViewById(R.id.btnLogowanie1)
        btnLogowanie2 = findViewById(R.id.btnLogowanie2)

    }
}
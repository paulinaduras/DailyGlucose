package com.example.dailyglucose

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class EkranNowyInsulina : AppCompatActivity() {

    private var editTextNowyInsulina: EditText? = null
    private var btnZatwierdz: Button? = null
    private var btnPowrot: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ekran_nowy_insulina)

        editTextNowyInsulina = findViewById(R.id.editTextNowyInsulina)
        btnZatwierdz = findViewById(R.id.btnNowyInsulina)
        btnPowrot = findViewById(R.id.btnNowyInsulina2)

        btnPowrot?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                openEkranGlowny()
            }
        })

    }

    private  fun openEkranGlowny(){
        val intent = Intent(this, EkranGlowny::class.java)
        startActivity(intent)
    }
}
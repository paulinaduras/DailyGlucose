package com.example.dailyglucose

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class EkranNowyGlukoza : AppCompatActivity() {

    private var editTextNowyGlukoza: EditText? = null
    private var btnZatwierdz: Button? = null
    private var btnPowrot: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ekran_nowy_glukoza)

        editTextNowyGlukoza = findViewById(R.id.editTextNowyGlukoza)
        btnZatwierdz = findViewById(R.id.btnNowyGlukoza1)
        btnPowrot = findViewById(R.id.btnNowyGlukoza2)

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
package com.example.dailyglucose

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private var btnZaloguj: Button?= null
    private var btnZarejestruj: Button ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnZaloguj = findViewById(R.id.btnZaloguj)
        btnZarejestruj = findViewById(R.id.btnZarejestruj)

        btnZaloguj?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                openEkranLogowania()
                }
        })

        btnZarejestruj?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                openEkranRejestracji()
            }
        })

    }
// ta metoda pozwala na przechodzenie z obecnego widoku na widok ekranu Logowania
    private fun openEkranLogowania(){
        val intent = Intent(this, EkranLogowania::class.java)
        startActivity(intent)
    }

    private fun openEkranRejestracji(){
        val intent = Intent(this, EkranRejestracji::class.java)
        startActivity(intent)
    }

}
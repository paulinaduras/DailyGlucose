package com.example.dailyglucose

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class EkranGlowny : AppCompatActivity() {

    private var btnWyloguj: Button? = null
    private var btnNowyGlukoza: Button? = null
    private var btnNowyInsulina: Button? = null
    private var btnHistoriaInsuliny: Button? = null
    private var btnHistoriaGlukozy: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ekran_glowny)

        btnWyloguj = findViewById(R.id.btnWyloguj)
        btnNowyGlukoza = findViewById(R.id.btnGlownyNowy1)
        btnNowyInsulina = findViewById(R.id.btnGlownyNowy2)
        btnHistoriaGlukozy = findViewById(R.id.btnGlownyHistoria1)
        btnHistoriaInsuliny = findViewById(R.id.btnGlownyHistoria2)

        btnWyloguj?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                wyloguj()
            }
        })

        btnNowyGlukoza?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                oknoDodawaniaGlukozy()
            }
        })

        btnNowyInsulina?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                oknoDodawaniaInsuliny()
            }
        })

        btnHistoriaGlukozy?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                oknoHistoriaGlukozy()
            }
        })

        btnHistoriaInsuliny?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                oknoHistoriaInsuliny()
            }
        })

    }

    private fun wyloguj(){
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this, EkranStartu::class.java)
        startActivity(intent)
        finish()
    }

    private fun oknoDodawaniaGlukozy(){
        val intent = Intent(this, EkranNowyGlukoza::class.java)
        startActivity(intent)
        finish()
    }

    private fun oknoDodawaniaInsuliny(){
        val intent = Intent(this, EkranNowyInsulina::class.java)
        startActivity(intent)
        finish()
    }

    private fun oknoHistoriaGlukozy(){
        val intent = Intent(this, EkranHistoriaGlukozy::class.java)
        startActivity(intent)
        finish()
    }

    private fun oknoHistoriaInsuliny(){
        val intent = Intent(this, EkranHistoriaInsuliny::class.java)
        startActivity(intent)
        finish()
    }
}
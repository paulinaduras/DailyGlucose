package com.example.dailyglucose

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EkranHistoriaGlukozy : AppCompatActivity() {

    private var rclHistoriaGlukozy: RecyclerView? = null
    private var btnNowyGLukoza: Button? = null
    private var btnPowrot: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ekran_historia_glukozy)

        btnNowyGLukoza = findViewById(R.id.btnHistoriaGlukozy1)
        btnPowrot = findViewById(R.id.btnHistoriaGlukoza2)
        rclHistoriaGlukozy = findViewById(R.id.rclHistoriaGlukozy)

        FireStoreClass().getUserDetails(this)

        btnNowyGLukoza?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                oknoDodawaniaGlukozy()
            }
        })

        btnPowrot?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                openEkranGlowny()
            }
        })
    }

    private fun oknoDodawaniaGlukozy(){
        val intent = Intent(this, EkranNowyGlukoza::class.java)
        startActivity(intent)
    }

    private  fun openEkranGlowny(){
        val intent = Intent(this, EkranGlowny::class.java)
        startActivity(intent)
    }

    fun uzupełnienieHistoriiGlukozy(lista: MutableList<String>){
        val pomiaryAdapter = InsulinaAdapter(lista)
        rclHistoriaGlukozy?.adapter = pomiaryAdapter
        rclHistoriaGlukozy?.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
    }

}
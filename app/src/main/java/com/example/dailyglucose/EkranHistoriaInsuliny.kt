package com.example.dailyglucose

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class EkranHistoriaInsuliny : AppCompatActivity() {

    private var rclHistoriaInsuliny: RecyclerView? = null
    private val mFireStore = FirebaseFirestore.getInstance()
    private var btnNowyInsulina: Button? = null
    private var btnPowrot: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ekran_historia_insuliny)

        btnNowyInsulina = findViewById(R.id.btnHistoriaInsuliny1)
        btnPowrot = findViewById(R.id.btnHistoriaInsuliny2)
        rclHistoriaInsuliny = findViewById(R.id.rclHistoriaInsuliny)

        FireStoreClass().getUserDetails(this)

        btnNowyInsulina?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                openOknoDodawaniaInsuliny()
            }
        })

        btnPowrot?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                openEkranGlowny()
            }
        })
    }

    private fun openOknoDodawaniaInsuliny(){
        val intent = Intent(this, EkranNowyInsulina::class.java)
        startActivity(intent)
    }

    private  fun openEkranGlowny(){
        val intent = Intent(this, EkranGlowny::class.java)
        startActivity(intent)
    }

    fun uzupełnienieHistoriiInsuliny(lista: MutableList<String>){
        val dawkiAdapter = InsulinaAdapter(lista)
        rclHistoriaInsuliny?.adapter = dawkiAdapter
        rclHistoriaInsuliny?.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
    }
}
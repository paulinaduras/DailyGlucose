package com.example.dailyglucose

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class EkranNowyGlukoza : AppCompatActivity() {

    private var editTextNowyGlukoza: EditText? = null
    private var editTextGlukozaData: EditText? = null
    private var editTextGlukozaGodzina: EditText? = null
    private var btnZatwierdz: Button? = null
    private var btnPowrot: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ekran_nowy_glukoza)

        editTextNowyGlukoza = findViewById(R.id.editTextNowyGlukoza)
        editTextGlukozaData = findViewById(R.id.editTextGlukozaDate)
        editTextGlukozaGodzina = findViewById(R.id.editTextGlukozaTime)
        btnZatwierdz = findViewById(R.id.btnNowyGlukoza1)
        btnPowrot = findViewById(R.id.btnNowyGlukoza2)

//        val glukoza = editTextNowyGlukoza?.text.toString().toFloat()
//        print(glukoza)

        btnPowrot?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                openEkranGlowny()
            }
        })

//        btnZatwierdz?.setOnClickListener(object : View.OnClickListener{
//            override fun onClick(v: View?) {
//                if (glukoza < 80){
//                    openEkranNiskaGlukoza()
//                }
//                else if(glukoza > 180){
//                    openEkranWysokaGlukoza()
//                }
//                else{
//                    openEkranPrawidlowaGlukoza()
//                }
//            }
//        })

    }

    private  fun openEkranGlowny(){
        val intent = Intent(this, EkranGlowny::class.java)
        startActivity(intent)
    }

//    private fun openEkranNiskaGlukoza(){
//        val intent = Intent(this, EkranNiskaGlukoza::class.java)
//        startActivity(intent)
//    }
//
//    private fun openEkranWysokaGlukoza(){
//        val intent = Intent(this, EkranWysokaGlukoza::class.java)
//        startActivity(intent)
//    }
//
//    private fun openEkranPrawidlowaGlukoza(){
//        val intent = Intent(this, EkranPrawidlowejGlukozy::class.java)
//        startActivity(intent)
//    }

}
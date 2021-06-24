package com.example.dailyglucose

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.text.SimpleDateFormat
import java.util.*

class EkranNowyGlukoza : AppCompatActivity() {

    private var editTextNowyGlukoza: EditText? = null
    private var tvGlukozaData: TextView? = null
    private var tvGlukozaGodzina: TextView? = null
    private var btnDateGlukoza: ImageView? = null
    private var btnTimeGlukoza: ImageView? = null
    private var btnZatwierdz: Button? = null
    private var btnPowrot: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ekran_nowy_glukoza)

        editTextNowyGlukoza = findViewById(R.id.editTextNowyGlukoza)
        tvGlukozaData = findViewById(R.id.tvNowyGlukozaData)
        tvGlukozaGodzina = findViewById(R.id.tvNowyGlukozaGodzina)
        btnDateGlukoza = findViewById(R.id.btnDateGlukoza)
        btnTimeGlukoza = findViewById(R.id.btnTimeGlukoza)
        btnZatwierdz = findViewById(R.id.btnNowyGlukoza1)
        btnPowrot = findViewById(R.id.btnNowyGlukoza2)

        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        btnPowrot?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                openEkranGlowny()
            }
        })

        btnDateGlukoza?.setOnClickListener{
            val dateSetListener = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, yy, mm, dd ->
                // set date to TextView
                if (mm < 10){
                    if (dd < 10){
                        tvGlukozaData?.setText("0" + dd + ".0" + (mm + 1) + "." + yy)
                    }
                    else{
                        tvGlukozaData?.setText("" + dd + ".0" + (mm + 1) + "." + yy)
                    }
                }
                else {
                    tvGlukozaData?.setText("" + dd + "." + (mm + 1) + "." + yy)
                }
            }, year, month, day)
            dateSetListener.show()
        }

        btnTimeGlukoza?.setOnClickListener{
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                // set time to TextView
                tvGlukozaGodzina?.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

        btnZatwierdz?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                sprawdzPoprawność()
            }
        })

    }

    private  fun openEkranGlowny(){
        val intent = Intent(this, EkranGlowny::class.java)
        startActivity(intent)
    }

    private fun openEkranNiskaGlukoza(){
        val intent = Intent(this, EkranNiskaGlukoza::class.java)
        startActivity(intent)
    }

    private fun openEkranWysokaGlukoza(){
        val intent = Intent(this, EkranWysokaGlukoza::class.java)
        startActivity(intent)
    }

    private fun openEkranPrawidlowaGlukoza(){
        val intent = Intent(this, EkranPrawidlowejGlukozy::class.java)
        startActivity(intent)
    }

    private fun sprawdzPoprawność(){
        var glukoza = editTextNowyGlukoza?.text.toString().toInt()

        if (glukoza < 80){
            openEkranNiskaGlukoza()
        }
        else if(glukoza > 180){
            openEkranWysokaGlukoza()
        }
        else{
            openEkranPrawidlowaGlukoza()
        }
    }

}
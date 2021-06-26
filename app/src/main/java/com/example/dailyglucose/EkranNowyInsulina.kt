package com.example.dailyglucose

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class EkranNowyInsulina : BaseActivity() {

    private var editTextNowyInsulina: EditText? = null
    private lateinit var tvInsulinaData: TextView
    private lateinit var tvInsulinaGodzina: TextView
    private var btnDateInsulina: ImageView? = null
    private var btnTimeInsulina: ImageView? = null
    private var btnZatwierdz: Button? = null
    private var btnPowrot: Button? = null
    private var lista: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ekran_nowy_insulina)

        editTextNowyInsulina = findViewById(R.id.editTextNowyInsulina)
        tvInsulinaData = findViewById(R.id.tvNowyInsulinaData)
        tvInsulinaGodzina = findViewById(R.id.tvNowyInsulinaGodzina)
        btnDateInsulina = findViewById(R.id.btnDateInsulina)
        btnTimeInsulina = findViewById(R.id.btnTimeInsulina)
        btnZatwierdz = findViewById(R.id.btnNowyInsulina1)
        btnPowrot = findViewById(R.id.btnNowyInsulina2)

        tvInsulinaData.setPaintFlags(tvInsulinaData.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
        tvInsulinaGodzina.setPaintFlags(tvInsulinaGodzina.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)

        val cal = Calendar.getInstance()

        FireStoreClass().getUserDetails(this)

        btnDateInsulina?.setOnClickListener{
            val dateSetListener = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, yy, mm, dd ->
//                 set date to TextView
                if (mm < 10){
                    if (dd < 10){
                        tvInsulinaData.setText("0" + dd + ".0" + (mm + 1) + "." + yy)
                    }
                    else{
                        tvInsulinaData.setText("" + dd + ".0" + (mm + 1) + "." + yy)
                    }
                }
                else {
                    tvInsulinaData.setText("" + dd + "." + (mm + 1) + "." + yy)
                }
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
            dateSetListener.show()
        }

        btnTimeInsulina?.setOnClickListener{
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                // set time to TextView
                tvInsulinaGodzina.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

        btnPowrot?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                openEkranGlowny()
            }
        })

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

    private fun validateInsulineDetails(): Boolean {

        return when{
            TextUtils.isEmpty(editTextNowyInsulina?.text.toString().trim{ it <= ' '}) -> {
                showErrorSnackBar("Podaj dawkę insuliny.", true)
                false
            }

            TextUtils.isEmpty(tvInsulinaData.text.toString().trim{ it <= ' '}) -> {
                showErrorSnackBar("Wybierz datę dawkowania.",true)
                false
            }

            TextUtils.isEmpty(tvInsulinaGodzina.text.toString().trim{ it <= ' '}) -> {
                showErrorSnackBar("Wybierz godzinę dawkowania.",true)
                false
            }

            else -> {
                true
            }
        }
    }

    fun pobierzHistorieInsuliny(tab: MutableList<String>){
        lista = tab
    }

    private fun sprawdzPoprawność(){
        var dawkiInsuliny: MutableList<String> = mutableListOf()

        if (validateInsulineDetails()) {

            var insulina = editTextNowyInsulina?.text.toString().toInt()
            var data = tvInsulinaData.text.toString()
            var godzina = tvInsulinaGodzina.text.toString()

            var dane = ""
            if (insulina < 100){
                dane = " $data     $godzina                    $insulina"
            }
            else if (insulina <10){
                dane = " $data     $godzina                     $insulina"

            }
            else{
                dane = " $data     $godzina                   $insulina"
            }

            dawkiInsuliny.add(dane)
            for (i in lista){
                dawkiInsuliny.add(i)
            }

            FireStoreClass().aktualizacjaHistoriInsuliny(dawkiInsuliny)
            showErrorSnackBar("Nowy dawka insuliny została pomyślnie dodana.", false)

            Handler().postDelayed(
                {
                    openEkranGlowny()
                },
                3000 // value in milliseconds
            )


        }
    }


}
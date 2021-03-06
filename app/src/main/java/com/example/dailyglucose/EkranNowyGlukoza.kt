package com.example.dailyglucose

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.View
import android.widget.*
import java.text.SimpleDateFormat
import java.util.*


class EkranNowyGlukoza : BaseActivity() {

    private var editTextNowyGlukoza: EditText? = null
    private lateinit var tvGlukozaData: TextView
    private lateinit var tvGlukozaGodzina: TextView
    private var btnDateGlukoza: ImageView? = null
    private var btnTimeGlukoza: ImageView? = null
    private var btnZatwierdz: Button? = null
    private var btnPowrot: Button? = null
    private var lista: MutableList<String> = mutableListOf()

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

        tvGlukozaData.setPaintFlags(tvGlukozaData.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
        tvGlukozaGodzina.setPaintFlags(tvGlukozaGodzina.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)

        val cal = Calendar.getInstance()

        FireStoreClass().getUserDetails(this)

        btnPowrot?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                openEkranGlowny()
            }
        })

        btnDateGlukoza?.setOnClickListener{
            val dateSetListener = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, yy, mm, dd ->
//                 set date to TextView
                if (mm < 10){
                    if (dd < 10){
                        tvGlukozaData.setText("0" + dd + ".0" + (mm + 1) + "." + yy)
                    }
                    else{
                        tvGlukozaData.setText("" + dd + ".0" + (mm + 1) + "." + yy)
                    }
                }
                else {
                    tvGlukozaData.setText("" + dd + "." + (mm + 1) + "." + yy)
                }
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
            dateSetListener.show()
        }

        btnTimeGlukoza?.setOnClickListener{
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                // set time to TextView
                tvGlukozaGodzina.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

        btnZatwierdz?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                sprawdzPoprawno????()
            }
        })

    }

    private  fun openEkranGlowny(){
        val intent = Intent(this, EkranGlowny::class.java)
        startActivity(intent)
    }

    private fun openEkranNiskaGlukoza(glukoza: String){
        val intent = Intent(this, EkranNiskaGlukoza::class.java)
        intent.putExtra("glucose",glukoza)
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

    private fun validateGlucoseDetails(): Boolean {

        return when{
            TextUtils.isEmpty(editTextNowyGlukoza?.text.toString().trim{ it <= ' '}) -> {
                showErrorSnackBar("Podaj warto???? poziomu glukozy.", true)
                false
            }

            TextUtils.isEmpty(tvGlukozaData.text.toString().trim{ it <= ' '}) -> {
                showErrorSnackBar("Wybierz dat?? pomiaru.",true)
                false
            }

            TextUtils.isEmpty(tvGlukozaGodzina.text.toString().trim{ it <= ' '}) -> {
                showErrorSnackBar("Wybierz godzin?? pomiaru.",true)
                false
            }

            else -> {
                true
            }
        }
    }

    fun pobierzHistorieGlukozy(tab: MutableList<String>){
        lista = tab
    }

    private fun sprawdzPoprawno????(){
        var pomiaryGlukozy: MutableList<String> = mutableListOf()

        if (validateGlucoseDetails()) {

            var glukoza = editTextNowyGlukoza?.text.toString().toInt()
            var data = tvGlukozaData.text.toString()
            var godzina = tvGlukozaGodzina.text.toString()

            var dane = ""
            if (glukoza < 100){
                dane = " $data      $godzina                   $glukoza"
            }
            else if (glukoza <10){
                dane = " $data      $godzina                    $glukoza"

            }
            else{
                dane = " $data      $godzina                  $glukoza"
            }

            pomiaryGlukozy.add(dane)
            for (i in lista){
                pomiaryGlukozy.add(i)
            }

            FireStoreClass().aktualizacjaHistoriGlukozy(pomiaryGlukozy)
            showErrorSnackBar("Nowy pomiar glukozy zosta?? pomy??lnie dodany.", false)

            Handler().postDelayed(
                {
                    if (glukoza < 80) {
                        openEkranNiskaGlukoza(glukoza.toString())
                    } else if (glukoza > 160) {
                        openEkranWysokaGlukoza()
                    } else {
                        openEkranPrawidlowaGlukoza()
                    }
                },
                3000 // value in milliseconds
            )


        }
    }



}
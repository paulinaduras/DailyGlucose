package com.example.dailyglucose

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth

class EkranLogowania : BaseActivity() {

    private var logowanieEmail: EditText? = null
    private var logowanieHaslo: EditText? = null
    private var btnLogowania: Button? = null
    private var btnNoweKonto: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ekran_logowania)

        logowanieEmail = findViewById(R.id.editTextLogowanie1)
        logowanieHaslo = findViewById(R.id.editTextLogowanie2)
        btnLogowania = findViewById(R.id.btnLogowania)
        btnNoweKonto = findViewById(R.id.btnNoweKonto)

        btnLogowania?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                logInRegisteredUser()
            }
        })

        btnNoweKonto?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                openEkranRejestracji()
            }
        })
    }

    private fun validateLoginDetails(): Boolean {

        return when{
            TextUtils.isEmpty(logowanieEmail?.text.toString().trim{ it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }

            TextUtils.isEmpty(logowanieHaslo?.text.toString().trim{ it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password),true)
                false
            }

            else -> {
                true
            }
        }


    }

    private fun logInRegisteredUser(){


        if(validateLoginDetails()){
            val email = logowanieEmail?.text.toString().trim(){ it<= ' '}
            val password = logowanieHaslo?.text.toString().trim(){ it<= ' '}

            //Log-in using FirebaseAuth

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                .addOnCompleteListener{task ->

                    if(task.isSuccessful){
                        FireStoreClass().getUserDetails(this)
                        showErrorSnackBar("Logowanie zakończone sukcesem.", false)

                    } else{
                        showErrorSnackBar("Hasło jest nieprawidłowe lub użytkownik nie posiada hasła.",true)
                    }
                }
        }
    }

    private fun openEkranGlowny(){
        val intent = Intent(this, EkranGlowny::class.java)
        startActivity(intent)
    }

    // ta metoda pozwala na przechodzenie z obecnego widoku na widok MainActivity
    private fun openEkranRejestracji(){
        val intent = Intent(this, EkranRejestracji::class.java)
        startActivity(intent)
    }


    fun userLoggedInSuccess(user: User){

        Log.i("Email: ", user.email)
        Log.i("name: ", user.name)

        openEkranGlowny()
        finish()
    }

}
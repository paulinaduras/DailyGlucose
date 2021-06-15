package com.example.dailyglucose

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class EkranRejestracji : BaseActivity() {

    private var rejestracjaNazwaUzytkownika: EditText? = null
    private var rejestracjaHaslo: EditText? = null
    private var rejestracjaPowtorzHaslo: EditText? = null
    private var rejestracjaEmail: EditText? = null
    private var btnRejestracji: Button? = null
    private var btnMamKonto: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ekran_rejestracji)

        rejestracjaNazwaUzytkownika = findViewById(R.id.editTextRejestracji1)
        rejestracjaHaslo = findViewById(R.id.editTextRejestracji2)
        rejestracjaPowtorzHaslo = findViewById(R.id.editTextRejestracji3)
        rejestracjaEmail = findViewById(R.id.editTextEmailAddress)
        btnRejestracji = findViewById(R.id.btnRejestracji)
        btnMamKonto = findViewById(R.id.btnMamKonto)

        btnRejestracji?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                registerUser()
//                openEkranLogowania()
            }
        })

        btnMamKonto?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                openEkranLogowania()
            }
        })
    }

    private fun validateRegisterDetails(): Boolean {

        return when{
            TextUtils.isEmpty(rejestracjaEmail?.text.toString().trim{ it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email),true)
                false
            }
            TextUtils.isEmpty(rejestracjaNazwaUzytkownika?.text.toString().trim{ it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_name),true)
                false
            }

            TextUtils.isEmpty(rejestracjaHaslo?.text.toString().trim{ it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password),true)
                false
            }

            TextUtils.isEmpty(rejestracjaPowtorzHaslo?.text.toString().trim{ it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_reppassword),true)
                false
            }

            rejestracjaHaslo?.text.toString().trim {it <= ' '} != rejestracjaPowtorzHaslo?.text.toString().trim{it <= ' '} -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_password_mismatch),true)
                false
            }


            else -> {
                //showErrorSnackBar("Your details are valid",false)
                true
            }
        }
    }


    private fun registerUser(){
        if (validateRegisterDetails()){
            val login: String = rejestracjaEmail?.text.toString().trim() {it <= ' '}
            val password: String = rejestracjaHaslo?.text.toString().trim() {it <= ' '}

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(login,password).addOnCompleteListener(
                OnCompleteListener <AuthResult>{ task ->
                    if(task.isSuccessful){
                        val firebaseUser: FirebaseUser = task.result!!.user!!

                        val user = User(
                            firebaseUser.uid,
                            rejestracjaNazwaUzytkownika?.text.toString().trim() {it <= ' '},
                            rejestracjaEmail?.text.toString().trim() {it <= ' '},
                        )


                        FireStoreClass().registerUser(this,user)

                        showErrorSnackBar("Twoja rejestracja przebiegła pomyślnie.",false)
                        FirebaseAuth.getInstance().signOut()
                        finish()


                    } else{
                        showErrorSnackBar(task.exception!!.message.toString(),true)
                    }
                }
            )
        }
    }

    private fun openEkranLogowania(){
        val intent = Intent(this, EkranLogowania::class.java)
        startActivity(intent)
    }

    fun userRegistrationSuccess(){

        Toast.makeText(
            this@EkranRejestracji,
            resources.getString(R.string.register_success),
            Toast.LENGTH_SHORT
        ).show()

    }
}
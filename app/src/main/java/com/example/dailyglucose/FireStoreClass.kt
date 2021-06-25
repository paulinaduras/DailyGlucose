package com.example.dailyglucose

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions


class FireStoreClass {

    private val mFireStore = FirebaseFirestore.getInstance()

    // tworzenie bazy danych
    fun registerUser(activity: EkranRejestracji, userInfo: User){
        mFireStore.collection("Users")
            .document(userInfo.id)
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {

                activity.userRegistrationSuccess()
            }
            .addOnFailureListener { e ->
                Log.e(
                    activity.javaClass.simpleName,
                    "Błąd podczas rejestracji urzytkownika.",
                    e
                )
            }

    }

    fun getCurrentUserID(): String {

        val currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserID = ""
        if(currentUser != null){
            currentUserID = currentUser.uid
        }
        return currentUserID
    }
    //pobieranie danych urzytkownika z firebase
    fun getUserDetails(activity: Activity){
        mFireStore.collection("Users")
            .document(getCurrentUserID())
            .get()
            .addOnSuccessListener { document ->

                Log.i(javaClass.simpleName,document.toString())
                val user = document.toObject(User::class.java)!!

                when(activity){
                    is EkranLogowania -> {
                        activity.userLoggedInSuccess(user)
                    }
                    is EkranNowyGlukoza -> {
                        activity.kk(user.historiaGlukozy)
                    }
//                    is EkranNowyInsulina -> {
//                        activity.uzupełnienieHistoriiInsuliny(user.historiaInsuliny)
//                    }
                    is EkranHistoriaGlukozy -> {
                        activity.uzupełnienieHistoriiGlukozy(user.historiaGlukozy)
                    }
                    is EkranHistoriaInsuliny -> {
                        activity.uzupełnienieHistoriiInsuliny(user.historiaInsuliny)
                    }
                }
            }
    }

    fun  aktualizacjaHistoriGlukozy(historiaGlukozy: MutableList<String>){

        mFireStore.collection("Users")
            .document(getCurrentUserID())
            .update("historiaGlukozy",historiaGlukozy)
    }

    fun  aktualizacjaHistoriInsuliny(historiaInsuliny: MutableList<String>){

        mFireStore.collection("Users")
            .document(getCurrentUserID())
            .update("historiaInsuliny",historiaInsuliny)
    }
}
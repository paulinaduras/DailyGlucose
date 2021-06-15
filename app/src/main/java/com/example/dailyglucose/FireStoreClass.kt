package com.example.dailyglucose

import android.app.Activity
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
                    "Error while registering the user",
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
                    is EkranHistoriaInsuliny -> {
                        println("go to kkk")
//                        activity.kkk(user)
                    }
                }
            }

    }

    fun  aktualizacjaHistoriGlukozy(activity: Activity, historiaGlukozy: Array<String>){

        mFireStore.collection("Users")
            .document(getCurrentUserID())
            .update("historiaGlukozy",historiaGlukozy)
    }

    fun  aktualizacjaHistoriInsuliny(activity: Activity, historiaInsuliny: Array<String>){

        mFireStore.collection("Users")
            .document(getCurrentUserID())
            .update("historiaInsuliny",historiaInsuliny)
    }

    fun pobierzHistorieInsuliny(){
        mFireStore.collection("Users")
            .document(getCurrentUserID())
            .get()
    }

}
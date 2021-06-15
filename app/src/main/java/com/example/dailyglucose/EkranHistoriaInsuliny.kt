package com.example.dailyglucose

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class EkranHistoriaInsuliny : AppCompatActivity() {

    private val mFireStore = FirebaseFirestore.getInstance()
    private var btnNowyInsulina: Button? = null
    private var btnPowrot: Button? = null
    private var lista2 = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ekran_historia_insuliny)

        btnNowyInsulina = findViewById(R.id.btnHistoriaInsuliny1)
        btnPowrot = findViewById(R.id.btnHistoriaInsuliny2)
        val rclHistoriaInsuliny = findViewById<RecyclerView>(R.id.rclHistoriaInsuliny)
        var listaDawek = getUserDetails(this)
//        println(lista2[0])
//        FireStoreClass().getUserDetails(this)

        println(lista2[0])


        val dawkiAdapter = InsulinaAdapter(lista2)
        rclHistoriaInsuliny.adapter=dawkiAdapter
        rclHistoriaInsuliny.layoutManager= LinearLayoutManager(this,RecyclerView.VERTICAL,false)


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

    fun getCurrentUserID(): String {

        val currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserID = ""
        if(currentUser != null){
            currentUserID = currentUser.uid
        }
        return currentUserID
    }

    fun getUserDetails(activity: Activity): List<String> {
        var listaReturn:List<String> = mutableListOf()
        mFireStore.collection("Users")
            .document(getCurrentUserID())
            .get()
            .addOnSuccessListener { document ->

                Log.i(javaClass.simpleName, document.toString())
                val user = document.toObject(User::class.java)!!

                println("Przez nadpisaniem")

                println("Email: ${user.email}")
                listaReturn = user.historiaInsuliny
                println("Po zcztaniu: ${listaReturn[0]}")
                setLista2(listaReturn)
            }
//        println(listaReturn[0])
        return listaReturn
    }
    fun setLista2(lista: List<String>){
        println(lista[0])
        for (i in lista){
            lista2.add(i)
            println(i)
        }

//        println(lista2[0])

    }

//    fun kkk(user: User){
//        print("JEtsem w kkaaa")
//        listaDawek =  user.historiaInsuliny
//        print(listaDawek.toString())
//        print("po lista dawek")
//    }
}
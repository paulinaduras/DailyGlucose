package com.example.dailyglucose

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class InsulinaAdapter(private val dawki: List<String>) :
    RecyclerView.Adapter<InsulinaAdapter.InsulinaViewHolder>() {

    class InsulinaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val txtDawka = itemView.findViewById(R.id.txHistoriaInsuliny) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InsulinaViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.historiainsuliny,parent,false)
        return InsulinaViewHolder(view)
    }

    override fun onBindViewHolder(holder: InsulinaViewHolder, position: Int) {
        val element=dawki[position]
        holder.txtDawka.text=element
    }

    override fun getItemCount(): Int {
        return dawki.size
    }
}
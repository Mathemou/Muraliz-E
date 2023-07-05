package com.example.muralize.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.muralize.Classes.Disciplina
import com.example.muralize.R

class DisciplinasAdapter (val listaDisciplinas : MutableList<Disciplina>) : RecyclerView.Adapter<DisciplinasAdapter.DisciplinasViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisciplinasViewHolder {
        Log.d("Chegou", "Chegou")

        val view = LayoutInflater.from(parent.context).inflate(R.layout.disciplina_item, parent, false)
        return DisciplinasViewHolder(view)
    }

    override fun getItemCount(): Int = listaDisciplinas.size

    override fun onBindViewHolder(holder: DisciplinasViewHolder, position: Int) {
        holder.bind(listaDisciplinas[position])
    }

    inner class DisciplinasViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView){
        fun bind(disciplina: Disciplina) {
            with(disciplina){
                itemView.findViewById<TextView>(R.id.codigoDisciplina).text = codigo
                itemView.findViewById<TextView>(R.id.nomeDisciplina).text = nome
            }
        }

    }
}
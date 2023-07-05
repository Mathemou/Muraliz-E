package com.example.muralize.Adapters

import android.app.Application
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.muralize.Classes.Disciplina
import com.example.muralize.R
import com.example.muralize.Utils.PopUpMethods
import com.example.muralize.ViewModels.AdicionarDisciplinasViewModel

class DisciplinasAdapter (val listaDisciplinas : MutableList<Disciplina>, val mAdicionarDisciplinasViewModel: AdicionarDisciplinasViewModel) : RecyclerView.Adapter<DisciplinasAdapter.DisciplinasViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisciplinasViewHolder {
        Log.d("Chegou", "Chegou")

        val view = LayoutInflater.from(parent.context).inflate(R.layout.disciplina_item, parent, false)
        return DisciplinasViewHolder(view)
    }

    override fun getItemCount(): Int = listaDisciplinas.size

    override fun onBindViewHolder(holder: DisciplinasViewHolder, position: Int) {
        holder.bind(listaDisciplinas[position])
    }

    fun removerDisciplina(position: Int) {
        listaDisciplinas.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class DisciplinasViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView){
        fun bind(disciplina: Disciplina) {
            with(disciplina){
                itemView.findViewById<TextView>(R.id.codigoDisciplina).text = codigo
                itemView.findViewById<TextView>(R.id.nomeDisciplina).text = nome
                itemView.findViewById<Button>(R.id.btnAdicionarCardDisciplina).setOnClickListener{
                    val builder = AlertDialog.Builder(itemView.context)
                    builder.setTitle("Alerta")
                    builder.setMessage("Você deseja adicionar essa disciplina?")
                    builder.setPositiveButton("Sim") { dialog, _ ->
                        mAdicionarDisciplinasViewModel.cadastrar(this)
                        dialog.dismiss()
                    }
                    builder.setNegativeButton("Não") { dialog, _ ->
                        dialog.dismiss()
                    }
                    val dialog = builder.create()
                    dialog.show()
                }
            }
        }

    }
}
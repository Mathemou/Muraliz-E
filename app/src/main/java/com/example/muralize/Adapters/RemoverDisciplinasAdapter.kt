package com.example.muralize.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.muralize.Classes.Disciplina
import com.example.muralize.R
import com.example.muralize.ViewModels.AdicionarDisciplinasViewModel
import com.example.muralize.ViewModels.RemoverDisciplinasViewModel

class RemoverDisciplinasAdapter (val listaDisciplinas : MutableList<Disciplina>, val mRemoverDisciplinasViewModel: RemoverDisciplinasViewModel) : RecyclerView.Adapter<RemoverDisciplinasAdapter.RemoverDisciplinasViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemoverDisciplinasViewHolder {
        Log.d("Chegou", "Chegou")

        val view = LayoutInflater.from(parent.context).inflate(R.layout.disciplina_item, parent, false)
        return RemoverDisciplinasViewHolder(view)
    }

    override fun getItemCount(): Int = listaDisciplinas.size

    override fun onBindViewHolder(holder: RemoverDisciplinasViewHolder, position: Int) {
        holder.bind(listaDisciplinas[position])
    }

    fun removerDisciplina(position: Int) {
        listaDisciplinas.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class RemoverDisciplinasViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView){
        fun bind(disciplina: Disciplina) {
            with(disciplina){
                itemView.findViewById<TextView>(R.id.codigoDisciplina).text = codigo
                itemView.findViewById<TextView>(R.id.nomeDisciplina).text = nome
                itemView.findViewById<Button>(R.id.btnAdicionarCardDisciplina).text = "-"
                itemView.findViewById<Button>(R.id.btnAdicionarCardDisciplina).setOnClickListener{
                    val builder = AlertDialog.Builder(itemView.context)
                    builder.setTitle("Alerta")
                    builder.setMessage("Você deseja remover essa disciplina?")
                    builder.setPositiveButton("Sim") { dialog, _ ->
                        mRemoverDisciplinasViewModel.remover(this)
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
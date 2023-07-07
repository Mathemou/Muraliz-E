package com.example.muralize.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.muralize.Classes.Disciplina
import com.example.muralize.Classes.Solicitacao
import com.example.muralize.Classes.Usuario
import com.example.muralize.R
import com.example.muralize.Utils.PopUpMethods
import com.example.muralize.ViewModels.AdicionarDisciplinasViewModel
import com.example.muralize.Views.SolicitacaoExpandida

class SolicitacoesAdapter(val listaSolicitacoes: MutableList<Solicitacao>) : RecyclerView.Adapter<SolicitacoesAdapter.SolicitacoesViewHolder>() {

    override fun getItemCount(): Int {
        return listaSolicitacoes.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SolicitacoesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.solicitacao_item, parent, false)
        return SolicitacoesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SolicitacoesViewHolder, position: Int) {
        val solicitacao = listaSolicitacoes[position]
        holder.bind(solicitacao)
    }

    inner class SolicitacoesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(solicitacao: Solicitacao) {
            solicitacao.disciplina!!.get().addOnSuccessListener {
                val disciplina = it.toObject(Disciplina::class.java)
                itemView.findViewById<TextView>(R.id.nome_disciplina_solicitacao_item).text = disciplina!!.nome
                itemView.findViewById<CardView>(R.id.card_view_solicitacao).setOnClickListener{
                    solicitacao.aluno!!.get().addOnSuccessListener { aluno ->
                        val intent = Intent(itemView.context, SolicitacaoExpandida::class.java)
                        intent.putExtra("DescricaoSolicitacao", solicitacao.descricao)
                        intent.putExtra("NomeDisciplina", disciplina.nome)
                        intent.putExtra("NomeAluno", aluno.toObject(Usuario::class.java)!!.nome)
                        intent.putExtra("TelefoneAluno", aluno.toObject(Usuario::class.java)!!.telefone)
                        itemView.context.startActivity(intent)
                    }
                }
            }
        }
    }
}

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
import com.example.muralize.ViewModels.ModificarSolicitacaoViewModel
import com.example.muralize.Views.SolicitacaoExpandida

class SolicitacoesGrandeAdapter(val listaSolicitacoes: MutableList<Solicitacao>, val mModificarSolicitacaoViewModel : ModificarSolicitacaoViewModel) : RecyclerView.Adapter<SolicitacoesGrandeAdapter.SolicitacoesGrandeViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SolicitacoesGrandeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.solicitacao_item_grande, parent, false)
        return SolicitacoesGrandeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SolicitacoesGrandeViewHolder, position: Int) {
        val solicitacao = listaSolicitacoes[position]
        holder.bind(solicitacao)
    }

    override fun getItemCount(): Int {
        return listaSolicitacoes.size
    }

    fun removerSolicitacao(position: Int) {
        listaSolicitacoes.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class SolicitacoesGrandeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(solicitacao: Solicitacao) {
            solicitacao.disciplina!!.get().addOnSuccessListener {
                val disciplina = it.toObject(Disciplina::class.java)
                itemView.findViewById<TextView>(R.id.nome_disciplina_solicitacao_item_grande).text = disciplina!!.nome
                itemView.findViewById<TextView>(R.id.codigo_disciplina_solicitacao_item_grande).text = disciplina.codigo
                itemView.findViewById<TextView>(R.id.descricao_solicitacao_item_grande).text = solicitacao.descricao
                itemView.findViewById<TextView>(R.id.data_solicitacao_item_grande).text = solicitacao.data!!.toDate().toString()
                itemView.findViewById<Button>(R.id.button_resolvido_solicitacao_item_grande).setOnClickListener{
                    val builder = AlertDialog.Builder(itemView.context)
                    builder.setTitle("Alerta")
                    builder.setMessage("Você deseja marcar essa solicitacão como resolvida?")
                    builder.setPositiveButton("Sim") { dialog, _ ->
                        mModificarSolicitacaoViewModel.marcarComoResolvida(solicitacao)
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
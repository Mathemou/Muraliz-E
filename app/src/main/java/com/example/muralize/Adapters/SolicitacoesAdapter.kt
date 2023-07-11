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

    /**
     * Cria uma nova instância de [SolicitacoesViewHolder] com base no tipo de visualização e no [ViewGroup] pai.
     *
     * @param parent O [ViewGroup] pai em que a nova visualização será inflada.
     * @param viewType O tipo de visualização da nova instância de [SolicitacoesViewHolder].
     * @return Uma nova instância de [SolicitacoesViewHolder] que representa um item da lista de solicitações.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SolicitacoesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.solicitacao_item, parent, false)
        return SolicitacoesViewHolder(itemView)
    }

    /**
     * Retorna o número total de itens na lista de solicitações.
     *
     * @return O número de itens na lista de solicitações.
     */
    override fun getItemCount(): Int {
        return listaSolicitacoes.size
    }

    /**
     * Associa os dados de uma solicitação específica ao [SolicitacoesViewHolder] fornecido.
     *
     * @param holder O [SolicitacoesViewHolder] que será associado aos dados da solicitação.
     * @param position A posição da solicitação na lista de solicitações.
     */
    override fun onBindViewHolder(holder: SolicitacoesViewHolder, position: Int) {
        val solicitacao = listaSolicitacoes[position]
        holder.bind(solicitacao)
    }

    /**
     * Classe interna que representa um ViewHolder para exibir os itens da lista de solicitações.
     *
     * @param itemView A view raiz do item da solicitação.
     */
    inner class SolicitacoesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /**
         * Associa os dados de uma solicitação ao layout do item da solicitação.
         *
         * @param solicitacao A solicitação a ser exibida.
         */
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

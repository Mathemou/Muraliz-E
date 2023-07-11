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
import com.example.muralize.Utils.UtilMethods
import com.example.muralize.ViewModels.ModificarSolicitacaoViewModel
import com.example.muralize.Views.SolicitacaoExpandida

class SolicitacoesGrandeAdapter(val listaSolicitacoes: MutableList<Solicitacao>, val mModificarSolicitacaoViewModel : ModificarSolicitacaoViewModel) : RecyclerView.Adapter<SolicitacoesGrandeAdapter.SolicitacoesGrandeViewHolder>() {

    /**
     * Cria uma nova instância de [SolicitacoesGrandeViewHolder] com base no tipo de visualização e no [ViewGroup] pai.
     *
     * @param parent O [ViewGroup] pai em que a nova visualização será inflada.
     * @param viewType O tipo de visualização da nova instância de [SolicitacoesGrandeViewHolder].
     * @return Uma nova instância de [SolicitacoesGrandeViewHolder] que representa um item da lista de solicitações.
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SolicitacoesGrandeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.solicitacao_item_grande, parent, false)
        return SolicitacoesGrandeViewHolder(itemView)
    }

    /**
     * Associa os dados de uma solicitação específica ao [SolicitacoesGrandeViewHolder] fornecido.
     *
     * @param holder O [SolicitacoesGrandeViewHolder] que será associado aos dados da solicitação.
     * @param position A posição da solicitação na lista de solicitações.
     */
    override fun onBindViewHolder(holder: SolicitacoesGrandeViewHolder, position: Int) {
        val solicitacao = listaSolicitacoes[position]
        holder.bind(solicitacao)
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
     * Remove uma solicitação da lista de solicitações na posição especificada.
     *
     * @param position A posição da solicitação a ser removida.
     */
    fun removerSolicitacao(position: Int) {
        listaSolicitacoes.removeAt(position)
        notifyItemRemoved(position)
    }

    /**
     * Classe interna que representa um ViewHolder para exibir os itens da lista de solicitações em um layout maior.
     *
     * @param itemView A view raiz do item da solicitação.
     */
    inner class SolicitacoesGrandeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /**
         * Associa os dados de uma solicitação ao layout maior do item da solicitação.
         *
         * @param solicitacao A solicitação a ser exibida.
         */
        fun bind(solicitacao: Solicitacao) {
            solicitacao.disciplina!!.get().addOnSuccessListener {
                val disciplina = it.toObject(Disciplina::class.java)
                itemView.findViewById<TextView>(R.id.nome_disciplina_solicitacao_item_grande).text = disciplina!!.nome
                itemView.findViewById<TextView>(R.id.codigo_disciplina_solicitacao_item_grande).text = disciplina.codigo
                itemView.findViewById<TextView>(R.id.descricao_solicitacao_item_grande).text = solicitacao.descricao
                itemView.findViewById<TextView>(R.id.data_solicitacao_item_grande).text = UtilMethods.converterDataParaPortugues(solicitacao.data!!.toDate().toString())
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
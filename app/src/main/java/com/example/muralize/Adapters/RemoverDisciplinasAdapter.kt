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

    /**
     * Cria uma nova instância de [RemoverDisciplinasViewHolder] com base no tipo de visualização e no [ViewGroup] pai.
     *
     * @param parent O [ViewGroup] pai em que a nova visualização será inflada.
     * @param viewType O tipo de visualização da nova instância de [RemoverDisciplinasViewHolder].
     * @return Uma nova instância de [RemoverDisciplinasViewHolder] que representa um item da lista de disciplinas.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemoverDisciplinasViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.disciplina_item, parent, false)
        return RemoverDisciplinasViewHolder(view)
    }

    /**
    * Retorna o número de itens na lista de disciplinas.
    *
    * @return O número de itens na lista de disciplinas.
    */
    override fun getItemCount(): Int = listaDisciplinas.size

    /**
     * Associa os dados de uma disciplina específica ao [RemoverDisciplinasViewHolder] fornecido.
     *
     * @param holder O [RemoverDisciplinasViewHolder] que será associado aos dados da disciplina.
     * @param position A posição da disciplina na lista de disciplinas.
     */
    override fun onBindViewHolder(holder: RemoverDisciplinasViewHolder, position: Int) {
        holder.bind(listaDisciplinas[position])
    }

    /**
     * Remove uma disciplina da lista de disciplinas na posição especificada.
     *
     * @param position A posição da disciplina a ser removida.
     */
    fun removerDisciplina(position: Int) {
        listaDisciplinas.removeAt(position)
        notifyItemRemoved(position)
    }

    /**
     * Classe interna que representa um ViewHolder para exibir os itens da lista de disciplinas com a opção de remoção.
     *
     * @param itemView A view raiz do item da disciplina.
     */
    inner class RemoverDisciplinasViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView){
        /**
         * Associa os dados de uma disciplina ao layout do item da disciplina com a opção de remoção.
         *
         * @param disciplina A disciplina a ser exibida e removida.
         */
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
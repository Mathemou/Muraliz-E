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
    /**
     * Cria uma nova instância de [DisciplinasViewHolder] com base no tipo de visualização e no [ViewGroup] pai.
     *
     * @param parent O [ViewGroup] pai em que a nova visualização será inflada.
     * @param viewType O tipo de visualização da nova instância do [DisciplinasViewHolder].
     * @return Uma nova instância do [DisciplinasViewHolder] que representa um item da lista de disciplinas.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisciplinasViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.disciplina_item, parent, false)
        return DisciplinasViewHolder(view)
    }

    /**
     * Retorna o número total de itens na lista de disciplinas.
     *
     * @return O número de itens na lista de disciplinas.
     */
    override fun getItemCount(): Int = listaDisciplinas.size

    /**
     * Associa os dados de uma disciplina específica ao [DisciplinasViewHolder] fornecido.
     *
     * @param holder O [DisciplinasViewHolder] que será associado aos dados da disciplina.
     * @param position A posição da disciplina na lista de disciplinas.
     */
    override fun onBindViewHolder(holder: DisciplinasViewHolder, position: Int) {
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
     * Classe interna que representa um ViewHolder para exibir os itens da lista de disciplinas.
     *
     * @param itemView A view raiz do item da disciplina.
     */
    inner class DisciplinasViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView){
        /**
         * Associa os dados de uma disciplina ao layout do item da disciplina.
         *
         * @param disciplina A disciplina a ser exibida.
         */
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
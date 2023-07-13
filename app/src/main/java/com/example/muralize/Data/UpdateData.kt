package com.example.muralize.Data

import android.annotation.SuppressLint
import com.example.muralize.Classes.Solicitacao
import com.example.muralize.Constants.ConstantesFB
import com.example.muralize.Utils.UtilMethods
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class UpdateData {
    companion object {
        @SuppressLint("StaticFieldLeak")
        private val db = FirebaseFirestore.getInstance()
        private val auth = Firebase.auth

        /**
         * Resolve uma solicitação, marcando-a como resolvida no banco de dados.
         *
         * @param solicitacao A solicitação a ser resolvida.
         * @param callback O objeto ResolverSolicitacaoCallback para receber os resultados da operação.
         */
        fun resolverSolicitacao(solicitacao : Solicitacao, callback: ResolverSolicitacaoCallback){
            solicitacao
                .documento!!
                .update(ConstantesFB.RESOLVIDA, true)
                .addOnSuccessListener {
                    callback.onSuccess(solicitacao)
                }
                .addOnFailureListener { error ->
                    callback.onFailure(error.message.toString())
                }
        }

        /**
         * Remove uma disciplina associada a um aluno no banco de dados.
         *
         * @param disciplina A referência da disciplina a ser removida.
         * @param callback O objeto RemoverDisciplinaCallback para receber os resultados da operação.
         */
        fun removerDisciplina(disciplina : DocumentReference, callback: RemoverDisciplinaCallback){
            db.collection(ConstantesFB.ALUNOS)
                .document(UtilMethods.getUidUser())
                .update(ConstantesFB.DISCIPLINAS, FieldValue.arrayRemove(disciplina))
                .addOnSuccessListener {
                    callback.onSuccess(true)
                }.addOnFailureListener { error ->
                    callback.onFailure(error.message.toString())
                }
        }
    }
}
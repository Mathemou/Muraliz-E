package com.example.muralize.Data

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.example.muralize.Classes.Disciplina
import com.example.muralize.Classes.Solicitacao
import com.example.muralize.Classes.Usuario
import com.example.muralize.Constants.ConstantesFB
import com.example.muralize.R
import com.example.muralize.Utils.UtilMethods
import com.example.muralize.Views.RegistrarSolicitacao
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class SaveData {
    companion object {
        @SuppressLint("StaticFieldLeak")
        private val db = FirebaseFirestore.getInstance()
        private val auth = Firebase.auth
        fun registrarEmail(email: String, password: String, callback: CadastroAlunoCallback) {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback.onSuccess(true)
                } else {
                    callback.onFailure(task.exception.toString())
                }
            }
        }

        fun registrarAluno(context: Context, data: HashMap<String, Any>, uid: String, callback: CadastroAlunoCallback) {
            // Salva os dados do usuário no banco de dados
            db
                .collection(ConstantesFB.ALUNOS)
                .document(uid)
                .set(data)
                .addOnSuccessListener {
                    callback.onSuccess(true)
                }.addOnFailureListener { error ->
                    callback.onFailure(context.getString(R.string.falha_ao_cadastrar_aluno))
                }
        }

        fun registrarDisciplina(disciplina : DocumentReference, callback : CadastroDisciplinaCallback){
            db.collection(ConstantesFB.ALUNOS)
                .document(UtilMethods.getUidUser())
                .update(ConstantesFB.DISCIPLINAS, FieldValue.arrayUnion(disciplina))
                .addOnSuccessListener {
                    callback.onSuccess(true)
                }.addOnFailureListener { error ->
                    callback.onFailure(error.message.toString())
                }
        }

        fun registrarSolicitacao(aluno : Usuario, solicitacao: Solicitacao, callback : RegistrarSolicitacaoCallback){
            val dadosDaSolicitacao : HashMap<String, Any> = HashMap<String, Any>()
            dadosDaSolicitacao["disciplina"] = solicitacao.disciplina!!
            dadosDaSolicitacao["aluno"] = solicitacao.aluno!!
            dadosDaSolicitacao["data"] = solicitacao.data!!
            dadosDaSolicitacao["descricao"] = solicitacao.descricao
            dadosDaSolicitacao["resolvida"] = false
            aluno.universidade!!
                .collection(ConstantesFB.SOLICITACOES)
                .add(dadosDaSolicitacao)
                .addOnSuccessListener {
                    callback.onSuccess(true)
                }.addOnFailureListener { error ->
                    callback.onFailure(error.message.toString())
                }

        }
    }
}
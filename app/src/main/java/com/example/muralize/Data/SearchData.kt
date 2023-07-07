package com.example.muralize.Data

import android.annotation.SuppressLint
import com.example.muralize.Classes.*
import com.example.muralize.Constants.ConstantesFB
import com.example.muralize.Utils.UtilMethods
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class SearchData {
    companion object{
        @SuppressLint("StaticFieldLeak")
        private val db = FirebaseFirestore.getInstance()

        fun obterUniversidades(callback: UniversidadeCallback) {
            db.collection(ConstantesFB.UNIVERSIDADES)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    val universidades = mutableListOf<Universidade>()
                    for (document in querySnapshot.documents) {
                        // Aqui você pode criar objetos Universidade com os dados do documento
                        val universidade = document.toObject(Universidade::class.java)
                        if(universidade != null){
                            universidade.documento = document.reference
                        }
                        universidades.add(universidade!!)
                    }
                    callback.onSuccess(universidades.toList())
                }
                .addOnFailureListener { error ->
                    callback.onFailure(error.message.toString())
                }
        }


        fun obterCursos(nomeUniversidade: String, callback: CursosCallback) {
            db.collection(ConstantesFB.UNIVERSIDADES)
                .whereEqualTo("nome", nomeUniversidade)
                .limit(1)
                .get()
                .addOnSuccessListener { documentoUniversidade ->
                    for (document in documentoUniversidade.documents) {
                        val cursosCollection = document.reference.collection("cursos")
                        cursosCollection.get()
                            .addOnSuccessListener { cursosSnapshot ->
                                val cursos = mutableListOf<Curso>()
                                for (cursoDocument in cursosSnapshot.documents) {
                                    // Aqui você pode criar objetos Curso com os dados do documento
                                    val curso = cursoDocument.toObject(Curso::class.java)
                                    if (curso != null) {
                                        curso.documento = cursoDocument.reference
                                    }
                                    cursos.add(curso!!)
                                }
                                callback.onSuccess(cursos.toList())
                            }
                            .addOnFailureListener { error ->
                                callback.onFailure(error.message.toString())
                            }
                    }
                }
                .addOnFailureListener { error ->
                    callback.onFailure(error.message.toString())
                }
        }

        fun obterUsuarioLogado(callback: UsuarioCallback){
            db
                .collection(ConstantesFB.ALUNOS)
                .document(UtilMethods.getUidUser())
                .get()
                .addOnSuccessListener { result ->
                    if (result.exists()) {
                        val user = result.toObject(Usuario::class.java)!!
                        user.documento = result.reference
                        callback.onSuccess(user)
                    } else {
                        callback.onFailure("FailureGetUser")
                    }
                }.addOnFailureListener { error ->
                    callback.onFailure(error.message.toString())
                }
        }

        fun obterDisciplinas(universidade: DocumentReference, callback : DisciplinasCallback) {
            universidade
                .collection(ConstantesFB.DISCIPLINAS)
                .orderBy(ConstantesFB.NOME)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    val disciplinas = mutableListOf<Disciplina>()
                    for (document in querySnapshot.documents) {
                        // Aqui você pode criar objetos Universidade com os dados do documento
                        val disciplina = document.toObject(Disciplina::class.java)
                        if(disciplina != null){
                            disciplina.documento = document.reference
                        }
                        disciplinas.add(disciplina!!)
                    }
                    callback.onSuccess(disciplinas.toList())
                }
                .addOnFailureListener { error ->
                    callback.onFailure(error.message.toString())
                }
        }

        fun obterDisciplinasCursadas(aluno : Usuario, callback : DisciplinasCallback) {
            aluno.universidade!!
                .collection(ConstantesFB.DISCIPLINAS)
                .orderBy(ConstantesFB.NOME)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    val disciplinas = mutableListOf<Disciplina>()
                    for (document in querySnapshot.documents) {
                        // Aqui você pode criar objetos Universidade com os dados do documento
                        val disciplina = document.toObject(Disciplina::class.java)
                        if(disciplina != null){
                            disciplina.documento = document.reference

                        }
                        if(aluno.disciplinas!=null) {
                            if ((aluno.disciplinas.contains(disciplina!!.documento))) {
                                disciplinas.add(disciplina)
                            }
                        } else {
                            disciplinas.add(disciplina!!)
                        }
                    }
                    callback.onSuccess(disciplinas.toList())
                }
                .addOnFailureListener { error ->
                    callback.onFailure(error.message.toString())
                }
        }

        fun obterDisciplinasNaoCursadas(aluno : Usuario, callback : DisciplinasCallback) {
            aluno.universidade!!
                .collection(ConstantesFB.DISCIPLINAS)
                .orderBy(ConstantesFB.NOME)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    val disciplinas = mutableListOf<Disciplina>()
                    for (document in querySnapshot.documents) {
                        // Aqui você pode criar objetos Universidade com os dados do documento
                        val disciplina = document.toObject(Disciplina::class.java)
                        if(disciplina != null){
                                disciplina.documento = document.reference

                        }
                        if (!(aluno.disciplinas.contains(disciplina!!.documento))) {
                            disciplinas.add(disciplina)
                        }
                    }
                    callback.onSuccess(disciplinas.toList())
                }
                .addOnFailureListener { error ->
                    callback.onFailure(error.message.toString())
                }
        }

        fun obterSolicitacoes(universidade: DocumentReference, callback: SolicitacaoCallback){
            universidade
                .collection(ConstantesFB.SOLICITACOES)
                .orderBy("data")
                .get()
                .addOnSuccessListener {
                    val solicitacoes = mutableListOf<Solicitacao>()
                    for(document in it.documents){
                        val solicitacao = document.toObject(Solicitacao::class.java)
                        if(solicitacao != null){
                            solicitacao.documento = document.reference
                            solicitacoes.add(solicitacao)

                        }

                    }
                    callback.onSuccess(solicitacoes)
                }.addOnFailureListener { error ->
                    callback.onFailure(error.message.toString())
                }
        }

    }
}
package com.example.muralize.Data

import android.annotation.SuppressLint
import android.util.Log
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

        /**
         * Obtém a lista de universidades disponíveis.
         *
         * @param callback O objeto UniversidadeCallback para receber os resultados da operação.
         */
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

        /**
         * Obtém a lista de cursos de uma universidade específica.
         *
         * @param nomeUniversidade O nome da universidade.
         * @param callback O objeto CursosCallback para receber os resultados da operação.
         */
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

        /**
         * Obtém os dados do usuário logado.
         *
         * @param callback O objeto UsuarioCallback para receber os resultados da operação.
         */
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

        /**
         * Obtém a lista de disciplinas de uma universidade específica.
         *
         * @param universidade A referência da universidade.
         * @param callback O objeto DisciplinasCallback para receber os resultados da operação.
         */
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

        /**
         * Obtém a lista de disciplinas cursadas pelo aluno.
         *
         * @param aluno O objeto Usuario que representa o aluno.
         * @param callback O objeto DisciplinasCallback para receber os resultados da operação.
         */
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
                        if ((aluno.disciplinas.contains(disciplina!!.documento))) {
                            disciplinas.add(disciplina)
                        }
                    }
                    callback.onSuccess(disciplinas.toList())
                }
                .addOnFailureListener { error ->
                    callback.onFailure(error.message.toString())
                }
        }

        /**
         * Obtém a lista de disciplinas não cursadas pelo aluno.
         *
         * @param aluno O objeto Usuario que representa o aluno.
         * @param callback O objeto DisciplinasCallback para receber os resultados da operação.
         */
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

        /**
         * Obtém a lista de solicitações de uma universidade específica.
         *
         * @param universidade A referência da universidade.
         * @param callback O objeto SolicitacaoCallback para receber os resultados da operação.
         */
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
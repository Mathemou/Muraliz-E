package com.example.muralize.Data

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.muralize.Classes.Disciplina
import com.example.muralize.Classes.Solicitacao
import com.example.muralize.Classes.Usuario
import com.example.muralize.Constants.ConstantesFB
import com.example.muralize.R
import com.example.muralize.Utils.UtilMethods
import com.example.muralize.Views.RegistrarSolicitacao
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks.whenAll
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask

class SaveData {
    companion object {
        @SuppressLint("StaticFieldLeak")
        private val db = FirebaseFirestore.getInstance()
        private val storage : FirebaseStorage = FirebaseStorage.getInstance();
        private val auth = Firebase.auth
        /**
         * Registra um novo usuário com o email e senha fornecidos.
         *
         * @param email O email do usuário.
         * @param password A senha do usuário.
         * @param callback O objeto CadastroAlunoCallback para receber os resultados da operação.
         */
        fun registrarEmail(email: String, password: String, callback: CadastroAlunoCallback) {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback.onSuccess(true)
                } else {
                    callback.onFailure(task.exception.toString())
                }
            }
        }

        /**
         * Registra os dados de um aluno no banco de dados.
         *
         * @param context O contexto da aplicação.
         * @param data Os dados do aluno a serem salvos.
         * @param uid O ID do usuário.
         * @param callback O objeto CadastroAlunoCallback para receber os resultados da operação.
         */
        fun registrarAluno(context: Context, data: HashMap<String, Any>, uid: String, callback: CadastroAlunoCallback) {
            // Salva os dados do usuário no banco de dados
            db
                .collection(ConstantesFB.ALUNOS)
                .document(uid)
                .set(data)
                .addOnSuccessListener {
                    callback.onSuccess(true)
                }.addOnFailureListener {
                    callback.onFailure(context.getString(R.string.falha_ao_cadastrar_aluno))
                }
        }

        /**
         * Registra uma disciplina para um aluno no banco de dados.
         *
         * @param disciplina A referência da disciplina a ser registrada.
         * @param callback O objeto CadastroDisciplinaCallback para receber os resultados da operação.
         */
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

        /**
         * Registra uma solicitação de um aluno no banco de dados.
         *
         * @param aluno O objeto Usuario que representa o aluno.
         * @param solicitacao A solicitação a ser registrada.
         * @param listaImagens A lista contendo as imagens da solicitação
         * @param callback O objeto RegistrarSolicitacaoCallback para receber os resultados da operação.
         */
        fun registrarSolicitacao(aluno : Usuario, solicitacao: Solicitacao, listaImagens : MutableList<Uri>, callback : RegistrarSolicitacaoCallback){
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
                    val solicitacaoId = it.id
                    val uploadTasks: MutableList<UploadTask> = mutableListOf()
                    val imagensUrls: MutableList<String> = mutableListOf() // Lista para armazenar as URLs das imagens

                    for ((index, imagemUri) in listaImagens.withIndex()) {
                        val imagemRef = storage.getReference("imagens").child("imagens/$solicitacaoId/imagem_$index.png")
                        val uploadTask = imagemRef.putFile(imagemUri)
                        uploadTasks.add(uploadTask)

                        // Obtém a URL da imagem após o upload
                        uploadTask.addOnSuccessListener { taskSnapshot ->

                            taskSnapshot.storage.downloadUrl.addOnSuccessListener { uri ->
                                imagensUrls.add(uri.toString())

                                // Verifica se todas as URLs das imagens foram obtidas
                                if (imagensUrls.size == listaImagens.size) {
                                    // Atualiza o documento da solicitação com as URLs das imagens
                                        it
                                        .update("imagens", imagensUrls)
                                        .addOnSuccessListener {
                                            callback.onSuccess(true)
                                        }
                                        .addOnFailureListener { error ->
                                            Log.d("Image", error.toString())
                                            callback.onFailure(error.message.toString())
                                        }
                                }
                            }
                        }
                            .addOnFailureListener{
                                Log.d("Image", it.toString())
                            }
                    }

                }.addOnFailureListener { error ->
                    callback.onFailure(error.message.toString())
                }
        }
    }
}
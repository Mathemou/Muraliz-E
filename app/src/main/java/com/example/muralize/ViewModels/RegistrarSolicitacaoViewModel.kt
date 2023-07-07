package com.example.muralize.ViewModels

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.muralize.Classes.Solicitacao
import com.example.muralize.Classes.Usuario
import com.example.muralize.Data.RegistrarSolicitacaoCallback
import com.example.muralize.Data.SaveData
import com.example.muralize.Utils.UtilMethods
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class RegistrarSolicitacaoViewModel(application : Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private val context = application.applicationContext
    private val db = FirebaseFirestore.getInstance()

    // Variáveis de observação da viewModel
    private var _registroSolicitacao = MutableLiveData<Boolean>()
    val registroSolicitacao: MutableLiveData<Boolean>
        get() = _registroSolicitacao

    private var _failureregistroSolicitacao = MutableLiveData<String>()
    val failureregistroSolicitacao: MutableLiveData<String>
        get() = _failureregistroSolicitacao

    fun solicitar(aluno : Usuario, disciplina : DocumentReference, descricao : String){
        val solicitacao = Solicitacao(disciplina, aluno.documento, UtilMethods.getCurrentTimestamp(), descricao)
        SaveData.registrarSolicitacao(aluno, solicitacao, object : RegistrarSolicitacaoCallback{
            override fun onSuccess(result: Boolean) {
                if(result) {
                    _registroSolicitacao.value = true
                } else {
                    _failureregistroSolicitacao.value = "Falha na escrita de solicitação"
                }
            }

            override fun onFailure(message: String) {
                _failureregistroSolicitacao.value = message
            }

        })
    }
}
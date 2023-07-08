package com.example.muralize.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.muralize.Classes.Disciplina
import com.example.muralize.Classes.Solicitacao
import com.example.muralize.Classes.Usuario
import com.example.muralize.Data.SearchData
import com.example.muralize.Data.SolicitacaoCallback

class ObterSolicitacoesViewModel(application : Application) : AndroidViewModel(application) {
    private var _solicitacoes = MutableLiveData<List<Solicitacao>>()
    val solicitacoes : MutableLiveData<List<Solicitacao>>
        get() = _solicitacoes
    private var _failureList = MutableLiveData<String>()
    val failureList: MutableLiveData<String>
        get() = _failureList

    fun obterSolicitacoesCompativeis(aluno : Usuario){
        if(_solicitacoes.value != null){
            (_solicitacoes.value as MutableList<*>).clear()
        }
        SearchData.obterSolicitacoes(aluno.universidade!!, object : SolicitacaoCallback{
            override fun onSuccess(solicitacoes: List<Solicitacao>) {
                val solicitacoesCompativeis = mutableListOf<Solicitacao>()
                for(solicitacao in solicitacoes){
                    if(aluno.disciplinas.contains(solicitacao.disciplina) && solicitacao.aluno!=aluno.documento && !solicitacao.resolvida){
                        solicitacoesCompativeis.add(solicitacao)
                    }
                }
                _solicitacoes.value = solicitacoesCompativeis
            }

            override fun onFailure(message: String) {
                _failureList.value = message
            }

        })
    }

    fun obterSolicitacoesDoUsuario(aluno : Usuario){
        if(_solicitacoes.value != null){
            (_solicitacoes.value as MutableList<*>).clear()
        }
        SearchData.obterSolicitacoes(aluno.universidade!!, object : SolicitacaoCallback{
            override fun onSuccess(solicitacoes: List<Solicitacao>) {
                val solicitacoesDoUsuario = mutableListOf<Solicitacao>()
                for(solicitacao in solicitacoes){
                    if(solicitacao.aluno==aluno.documento && !solicitacao.resolvida){
                        solicitacoesDoUsuario.add(solicitacao)
                    }
                }
                _solicitacoes.value = solicitacoesDoUsuario
            }

            override fun onFailure(message: String) {
                _failureList.value = message
            }

        })
    }
}
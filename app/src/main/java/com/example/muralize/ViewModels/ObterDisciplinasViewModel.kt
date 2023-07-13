package com.example.muralize.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.muralize.Classes.Curso
import com.example.muralize.Classes.Disciplina
import com.example.muralize.Classes.Usuario
import com.example.muralize.Data.DisciplinasCallback
import com.example.muralize.Data.SearchData
import com.google.firebase.firestore.DocumentReference

/**
 * ViewModel responsável por obter as disciplinas de uma universidade ou de um aluno.
 *
 * @param application A instância da aplicação.
 */
class ObterDisciplinasViewModel(application : Application) : AndroidViewModel(application)  {
    private var _disciplinas = MutableLiveData<List<Disciplina>>()
    val disciplinas : MutableLiveData<List<Disciplina>>
        get() =_disciplinas
    private var _failureList = MutableLiveData<String>()
    val failureList: MutableLiveData<String>
        get() = _failureList

    /**
     * Obtém as disciplinas de uma universidade.
     *
     * @param universidade A referência para o documento da universidade no Firebase Firestore.
     */
    fun obterDisciplinas(universidade : DocumentReference){
        // Limpa a MutableLiveData caso ela já tenha algo antes da pesquisa
        if(_disciplinas.value != null){
            (_disciplinas.value as MutableList<*>).clear()
        }
        SearchData.obterDisciplinas(universidade, object : DisciplinasCallback {
            override fun onSuccess(disciplinas : List<Disciplina>) {
                _disciplinas.value = disciplinas
            }

            override fun onFailure(message: String) {
                _failureList.value = message
            }

        })
    }

    /**
     * Obtém as disciplinas cursadas por um aluno.
     *
     * @param aluno O objeto do aluno cujas disciplinas serão obtidas.
     */
    fun obterDisciplinasCursadas(aluno : Usuario){
        if(_disciplinas.value != null){
            (_disciplinas.value as MutableList<*>).clear()
        }
        SearchData.obterDisciplinasCursadas(aluno, object : DisciplinasCallback {
            override fun onSuccess(disciplinas: List<Disciplina>) {
                _disciplinas.value = disciplinas
            }

            override fun onFailure(message: String) {
                _failureList.value = message
            }

        })
    }

    /**
     * Obtém as disciplinas não cursadas por um aluno.
     *
     * @param aluno O objeto do aluno cujas disciplinas serão obtidas.
     */
    fun obterDisciplinasNaoCursadas(aluno : Usuario){
        // Limpa a MutableLiveData caso ela já tenha algo antes da pesquisa
        if(_disciplinas.value != null){
            (_disciplinas.value as MutableList<*>).clear()
        }
        SearchData.obterDisciplinasNaoCursadas(aluno, object : DisciplinasCallback {
            override fun onSuccess(disciplinas : List<Disciplina>) {
                _disciplinas.value = disciplinas
            }
            override fun onFailure(message: String) {
                _failureList.value = message
            }

        })
    }
}
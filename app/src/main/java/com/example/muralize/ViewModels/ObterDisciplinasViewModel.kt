package com.example.muralize.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.muralize.Classes.Curso
import com.example.muralize.Classes.Disciplina
import com.example.muralize.Data.DisciplinasCallback
import com.example.muralize.Data.SearchData
import com.google.firebase.firestore.DocumentReference

class ObterDisciplinasViewModel(application : Application) : AndroidViewModel(application)  {
    private var _disciplinas = MutableLiveData<List<Disciplina>>()
    val disciplinas : MutableLiveData<List<Disciplina>>
        get() =_disciplinas
    private var _failureList = MutableLiveData<String>()
    val failureList: MutableLiveData<String>
        get() = _failureList

    fun obterDisciplinas(universidade : DocumentReference){
        // Limpa a MutableLiveData caso ela já tenha algo antes da pesquisa
        if(_disciplinas.value != null){
            (_disciplinas.value as MutableList<*>).clear()
        }
        SearchData.obterUniversidades(universidade, object : DisciplinasCallback {
            override fun onSuccess(disciplinas : List<Disciplina>) {
                _disciplinas.value = disciplinas
            }

            override fun onFailure(message: String) {
                _failureList.value = message
            }

        })
    }
}
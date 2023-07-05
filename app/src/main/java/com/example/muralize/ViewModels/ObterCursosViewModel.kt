package com.example.muralize.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.muralize.Classes.Curso
import com.example.muralize.Data.CursosCallback
import com.example.muralize.Data.SearchData

class ObterCursosViewModel(application : Application) : AndroidViewModel(application)  {
    private var _cursos = MutableLiveData<List<Curso>>()
    val cursos : MutableLiveData<List<Curso>>
        get() =_cursos
    private var _failureList = MutableLiveData<String>()
    val failureList: MutableLiveData<String>
        get() = _failureList

    /**
     * Obtem os cursos de uma determinada universidade cadastrada no Firebase
     * @param nomeUniversidade : Nome da universidade cujo os cursos serão buscados
     */
    fun obterCursos(nomeUniversidade : String){
        // Limpa a MutableLiveData caso ela já tenha algo antes da pesquisa
        if(_cursos.value != null){
            (_cursos.value as MutableList<*>).clear()
        }
        SearchData.obterCursos(nomeUniversidade, object : CursosCallback {
            override fun onSuccess(cursos: List<Curso>) {
                _cursos.value = cursos
            }

            override fun onFailure(message: String) {
                _failureList.value = message
            }

        })
    }
}
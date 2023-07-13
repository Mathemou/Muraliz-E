package com.example.muralize.ViewModels

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.muralize.Classes.Universidade
import com.example.muralize.Classes.Usuario
import com.example.muralize.Data.SearchData
import com.example.muralize.Data.UniversidadeCallback
import com.example.muralize.Data.UsuarioCallback
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot

/**
 * ViewModel responsável por obter a lista de universidades cadastradas.
 *
 * @param application A instância da aplicação.
 */
@SuppressLint("StaticFieldLeak")
class ObterUniversidadesViewModel(application : Application) : AndroidViewModel(application) {
    private var _universidades = MutableLiveData<List<Universidade>>()
    val universidades : MutableLiveData<List<Universidade>>
        get() =_universidades

    private var _failureList = MutableLiveData<String>()
    val failureList: MutableLiveData<String>
        get() = _failureList

    /**
     * Obtem todas as universidades cadastradas no Firebase
     */
    fun obterUniversidades(){
        // Limpa a MutableLiveData caso ela já tenha algo antes da pesquisa
        if(_universidades.value != null){
            (_universidades.value as MutableList<*>).clear()
        }
        SearchData.obterUniversidades(object : UniversidadeCallback {
            override fun onSuccess(universidades: List<Universidade>) {
                _universidades.value = universidades
            }

            override fun onFailure(message: String) {
                _failureList.value = message
            }

        })
    }
}
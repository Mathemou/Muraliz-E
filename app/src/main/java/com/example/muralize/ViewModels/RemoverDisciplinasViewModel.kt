package com.example.muralize.ViewModels

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.muralize.Classes.Disciplina
import com.example.muralize.Data.CadastroDisciplinaCallback
import com.example.muralize.Data.RemoverDisciplinaCallback
import com.example.muralize.Data.SaveData
import com.example.muralize.Data.UpdateData
import com.google.firebase.firestore.FirebaseFirestore

class RemoverDisciplinasViewModel(application : Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private val context = application.applicationContext
    private val db = FirebaseFirestore.getInstance()

    // Variáveis de observação da viewModel
    private var _removerDisciplina = MutableLiveData<Disciplina>()
    val removerDisciplina: MutableLiveData<Disciplina>
        get() = _removerDisciplina

    private var _failureRemoverDisciplina = MutableLiveData<String>()
    val failureRemoverDisciplina: MutableLiveData<String>
        get() = _failureRemoverDisciplina

    fun remover(disciplina : Disciplina) {
        UpdateData.removerDisciplina(disciplina.documento!!, object : RemoverDisciplinaCallback {
            override fun onSuccess(result: Boolean) {
                if(result) {
                    _removerDisciplina.value = disciplina
                } else {
                    _failureRemoverDisciplina.value = "Falha ao remover disciplina"
                }
            }

            override fun onFailure(message: String) {
                _failureRemoverDisciplina.value = "Falha ao remover disciplina"
            }

        })
    }
}
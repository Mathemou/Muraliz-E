package com.example.muralize.ViewModels

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.muralize.Classes.Disciplina
import com.example.muralize.Data.CadastroAlunoCallback
import com.example.muralize.Data.CadastroDisciplinaCallback
import com.example.muralize.Data.SaveData
import com.example.muralize.R
import com.example.muralize.Utils.UtilMethods
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

/**
 * ViewModel responsável por adicionar disciplinas.
 *
 * @param application A instância da aplicação.
 */
class AdicionarDisciplinasViewModel(application : Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private val context = application.applicationContext
    private val db = FirebaseFirestore.getInstance()

    private var _cadastrarDisciplina = MutableLiveData<Disciplina>()
    val cadastrarDisciplina: MutableLiveData<Disciplina>
        get() = _cadastrarDisciplina

    private var _failureCadastrarDisciplina = MutableLiveData<String>()
    val failureCadastrarDisciplina: MutableLiveData<String>
        get() = _failureCadastrarDisciplina

    /**
     * Cadastra uma disciplina.
     *
     * @param disciplina A disciplina a ser cadastrada.
     */
    fun cadastrar(disciplina : Disciplina) {
        SaveData.registrarDisciplina(disciplina.documento!!, object : CadastroDisciplinaCallback {
            override fun onSuccess(result: Boolean) {
                if(result) {
                    _cadastrarDisciplina.value = disciplina
                } else {
                    _failureCadastrarDisciplina.value = "Falha ao cadastrar disciplina"
                }
            }

            override fun onFailure(message: String) {
                _failureCadastrarDisciplina.value = "Falha ao cadastrar disciplina"
            }

        })
    }
}
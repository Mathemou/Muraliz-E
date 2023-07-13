package com.example.muralize.ViewModels

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.muralize.Data.CadastroAlunoCallback
import com.example.muralize.Data.SaveData
import com.example.muralize.R
import com.example.muralize.Utils.UtilMethods
import com.google.firebase.firestore.FirebaseFirestore

/**
 * ViewModel responsável pelo cadastro de alunos.
 *
 * @param application A instância da aplicação.
 */
class CadastroAlunosViewModel(application : Application) : AndroidViewModel(application)  {
    @SuppressLint("StaticFieldLeak")
    private val context = application.applicationContext
    private val db = FirebaseFirestore.getInstance()

    private var _userRegistration = MutableLiveData<Boolean>()
    val userRegistration: MutableLiveData<Boolean>
        get() = _userRegistration

    private var _failureUserRegistration = MutableLiveData<String>()
    val failureUserRegistration: MutableLiveData<String>
        get() = _failureUserRegistration

    /**
     * Realiza o cadastro de um aluno.
     *
     * @param email O email do aluno.
     * @param password A senha do aluno.
     * @param data Os dados do aluno a serem cadastrados.
     */
    fun cadastrar(email: String, password: String, data: HashMap<String, Any>) {
        SaveData.registrarEmail(email, password, object : CadastroAlunoCallback {
            override fun onSuccess(result: Boolean) {
                if (result) {
                    val uid = UtilMethods.getUidUser()
                    SaveData.registrarAluno(context, data, uid, object : CadastroAlunoCallback {
                        override fun onSuccess(result: Boolean) {
                            _userRegistration.value = true
                        }
                        override fun onFailure(message: String) {
                            _failureUserRegistration.value = context.getString(R.string.falha_ao_cadastrar_aluno)
                        }

                    })
                } else {
                    _failureUserRegistration.value = context.getString(R.string.falha_ao_cadastrar_aluno)
                }
            }
            override fun onFailure(message: String) {
                Log.e("FailureSignUpUser", message)
                _failureUserRegistration.value = message
            }
        })
    }
}
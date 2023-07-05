package com.example.muralize.ViewModels

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.muralize.Data.LoginCallback
import com.example.muralize.R
import com.example.muralize.Utils.PopUpMethods
import com.example.muralize.Utils.UtilMethods
import com.example.muralize.Views.Login
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel(application : Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private val context: Context = application.baseContext

    private var _loginUser = MutableLiveData<Boolean>()
    val loginUser: MutableLiveData<Boolean>
        get() = _loginUser


    private var _recovery = MutableLiveData<Boolean>()
    val recovery: MutableLiveData<Boolean>
        get() = _recovery

    private var _failureLogin = MutableLiveData<String>()
    val failureLogin: MutableLiveData<String>
        get() = _failureLogin

    // cria a variável do authentication
    private val auth = Firebase.auth

    private fun login(email: String, password: String, callback: LoginCallback) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { result ->
            if (result.isSuccessful) {
                callback.onSuccess(result.isSuccessful)
            } else {
                callback.onFailure(result.exception.toString())
            }
        }
    }

    fun loginUser(email: String, password: String) {
        if (UtilMethods.statusInternet(context)) {
            login(email, password, object : LoginCallback{
                override fun onSuccess(resultado: Boolean) {
                    _loginUser.value = resultado
                }
                override fun onFailure(message: String) {
                    _failureLogin.value = returnMessageError(message)
                }
            })
        } else {
            PopUpMethods.toastLong(context, context.getString(R.string.falha_na_internet))
        }
    }

    private fun returnMessageError(message: String) : String {
        if (message.contains("There is no user record corresponding to this identifier.")) {
            return context.getString(R.string.falha_email_n_cadastrado)
        } else if (message.contains("The password is invalid or the user does not have a password.")) {
            return context.getString(R.string.falha_senha_invalida)
        }
        return context.getString(R.string.failure_login)
    }
}
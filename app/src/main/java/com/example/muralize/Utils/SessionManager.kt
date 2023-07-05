package com.example.muralize.Utils

import android.content.Context
import android.content.SharedPreferences
import com.example.muralize.R

class SessionManager (context: Context){
    private val prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
    companion object {
        const val NOME ="nome"
        const val EMAIL ="email"
        const val TELEFONE = "telefone"
    }
    fun salvarNomeUsuario(nome_usuario: String?) {
        val editor = prefs.edit()
        editor.putString(NOME, nome_usuario)
        editor.apply()
    }

    fun lerNome(): String? {
        return prefs.getString(NOME, null)
    }

    fun salvarEmailPerfil(email_usuario: String?) {
        val editor = prefs.edit()
        editor.putString(EMAIL, email_usuario)
        editor.apply()
    }

    fun lerEmail(): String? {
        return prefs.getString(EMAIL, null)
    }

    fun salvarTelefone(phone: String?) {
        val editor = prefs.edit()
        editor.putString(TELEFONE, phone)
        editor.apply()
    }

    fun lerTelefone() = prefs.getString(TELEFONE, null)
}
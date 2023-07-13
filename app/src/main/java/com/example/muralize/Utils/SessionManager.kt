package com.example.muralize.Utils

import android.content.Context
import android.content.SharedPreferences
import com.example.muralize.R

/**
 * Classe que gerencia a sessão do usuário, armazenando e lendo dados na SharedPreferences.
 *
 * @param context O contexto da aplicação.
 */
class SessionManager (context: Context){
    private val prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
    companion object {
        const val NOME ="nome"
        const val EMAIL ="email"
        const val TELEFONE = "telefone"
    }

    /**
     * Salva o nome do usuário na SharedPreferences.
     *
     * @param nome_usuario O nome do usuário a ser salvo.
     */
    fun salvarNomeUsuario(nome_usuario: String?) {
        val editor = prefs.edit()
        editor.putString(NOME, nome_usuario)
        editor.apply()
    }

    /**
     * Lê o nome do usuário salvo na SharedPreferences.
     *
     * @return O nome do usuário lido.
     */
    fun lerNome(): String? {
        return prefs.getString(NOME, null)
    }

    /**
     * Salva o email do usuário na SharedPreferences.
     *
     * @param email_usuario O email do usuário a ser salvo.
     */
    fun salvarEmailPerfil(email_usuario: String?) {
        val editor = prefs.edit()
        editor.putString(EMAIL, email_usuario)
        editor.apply()
    }

    /**
     * Lê o email do usuário salvo na SharedPreferences.
     *
     * @return O email do usuário lido.
     */
    fun lerEmail(): String? {
        return prefs.getString(EMAIL, null)
    }

    /**
     * Salva o telefone do usuário na SharedPreferences.
     *
     * @param phone O telefone do usuário a ser salvo.
     */
    fun salvarTelefone(phone: String?) {
        val editor = prefs.edit()
        editor.putString(TELEFONE, phone)
        editor.apply()
    }

    /**
     * Lê o telefone do usuário salvo na SharedPreferences.
     *
     * @return O telefone do usuário lido.
     */
    fun lerTelefone() = prefs.getString(TELEFONE, null)
}
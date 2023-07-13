package com.example.muralize.Data

import com.example.muralize.Classes.Curso

/**
 * Interface que define métodos de retorno para o processo de login.
 */
interface LoginCallback {
    /**
     * Função chamada quando o login é realizado com sucesso.
     *
     * @param resultado Booleano indicando se o login foi bem-sucedido ou não.
     */
    fun onSuccess(resultado : Boolean)

    /**
     * Função chamada quando ocorre uma falha no processo de login.
     *
     * @param message Mensagem de erro descrevendo o motivo da falha.
     */
    fun onFailure(message: String)
}
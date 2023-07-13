package com.example.muralize.Data

import com.example.muralize.Classes.Usuario

/**
 * Interface que define métodos de retorno para a obtenção de um usuário.
 */
interface UsuarioCallback {
    /**
     * Função chamada quando a obtenção do usuário é concluída com sucesso.
     *
     * @param usuario Objeto Usuario representando o usuário obtido.
     */
    fun onSuccess(usuario: Usuario)

    /**
     * Função chamada quando ocorre uma falha na obtenção do usuário.
     *
     * @param message Mensagem de erro descrevendo o motivo da falha.
     */
    fun onFailure(message: String)
}

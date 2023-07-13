package com.example.muralize.Data

/**
 * Interface que define métodos de retorno para o registro de uma solicitação.
 */
interface RegistrarSolicitacaoCallback {
    /**
     * Função chamada quando o registro da solicitação é concluído com sucesso.
     *
     * @param result Booleano indicando se o registro foi bem-sucedido ou não.
     */
    fun onSuccess(result: Boolean)

    /**
     * Função chamada quando ocorre uma falha no registro da solicitação.
     *
     * @param message Mensagem de erro descrevendo o motivo da falha.
     */
    fun onFailure(message: String)
}